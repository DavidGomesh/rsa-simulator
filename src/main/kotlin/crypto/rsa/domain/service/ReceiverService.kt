package crypto.rsa.domain.service

import crypto.rsa.domain.model.Message
import crypto.rsa.domain.model.Message.Companion.NO_CONTENT
import crypto.rsa.domain.model.MessageType.*
import crypto.rsa.domain.repository.AgentRepository
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
        return messageService.save(
            Message(PUBLIC_KEY_RESPONSE, NO_CONTENT, r.publicKey, m.receiver, m.sender)
        )
    }
}