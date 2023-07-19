package crypto.rsa.domain.service

import crypto.rsa.domain.model.Message
import crypto.rsa.domain.repository.AgentRepository
import crypto.rsa.domain.utils.KeyUtils.Companion.getPrivateKey
import crypto.rsa.domain.utils.RSAUtils.Companion.decode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReceiverService {

    @Autowired
    lateinit var agentRepository: AgentRepository

    @Autowired
    lateinit var messageService: MessageService

    fun requestPublicKey(m: Message): Message {
        val r = agentRepository.findById(m.receiver).orElseThrow()
        return messageService.createPublicKeyResponse(r.publicKey, m.receiver, m.sender)
    }

    fun receiveMessage(m: Message): Message {
        val r = agentRepository.findById(m.receiver).orElseThrow()

        return try {
            m.content?.let { decode(it, getPrivateKey(r.privateKey)) }
            messageService.createMessageReceivedConfirmation(r.publicKey, m.receiver, m.sender)

        } catch (e: Exception) {
            messageService.createDecryptionError(r.publicKey, m.receiver, m.sender)
        }
    }
}