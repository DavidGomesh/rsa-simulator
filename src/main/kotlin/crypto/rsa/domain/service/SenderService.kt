package crypto.rsa.domain.service

import crypto.rsa.domain.model.AgentId
import crypto.rsa.domain.model.Message
import crypto.rsa.domain.model.Message.Companion.NO_CONTENT
import crypto.rsa.domain.model.MessageType.*
import crypto.rsa.domain.repository.AgentRepository
import crypto.rsa.domain.utils.KeyUtils.Companion.getPrivateKey
import crypto.rsa.domain.utils.RSAUtils.Companion.encode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SenderService {

    @Autowired
    lateinit var agentRepository: AgentRepository

    @Autowired
    lateinit var receiverService: ReceiverService

    @Autowired
    lateinit var messageService: MessageService

    fun requestPublicKey(sender: AgentId, receiver: AgentId): Message {
        val s = agentRepository.findById(sender).orElseThrow()
        val m = messageService.save(Message(PUBLIC_KEY_REQUEST, NO_CONTENT, s.publicKey, sender, receiver))
        return receiverService.requestPublicKey(m)
    }

//    fun sendMessage(sender: AgentId, receiver: AgentId, content: ByteArray): Message? {
//
//        val rpk = requestPublicKey(sender, receiver)
//
//        val s = agentRepository.findById(sender).orElseThrow()
//        val c = encode(content, getPrivateKey(s.privateKey))
//        val m = messageService.save(Message(SIMPLE_MESSAGE, c, s.publicKey, sender, receiver))
//    }


}

/*
*
* Compartilhamento de mensagens sem assinatura:
* 1 - O remetente solicita a chave pública do destinatário
* 2 - O destinatário envia sua chave pública ao remetente
* 3 - O remetente criptografa a mensagem com a chave pública do destinatário e a envia
* 4 - O destinatário descriptografa a mensagem com sua chave privada e envia uma resposta de confirmação
*
* Compartilhamento de mensagens com assinatura:
* 1 - O remetente solicita a chave pública do destinatário
* 2 - O destinatário envia sua chave pública ao remetente
* 3 - O remetente criptografa a mensagem com a chave pública do destinatário
* 4 - O remetente gera um hash da mensagem e criptografa com sua chave privada
* 5 - O remetente então envia a mensagem e a assinatura
* 6 - O destinatário descriptografa a mensagem com sua chave privada
* 7 - O destinatário descriptografa a assinatura com a chave pública do remetente
* 8 - O destinatário gera o hash da mensagem e verifica se bate com o hash enviado
* 9 - O destinatário envia uma resposta de confirmação
*
* */