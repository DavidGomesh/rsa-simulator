package crypto.rsa.domain.service

import crypto.rsa.domain.model.AgentId
import crypto.rsa.domain.model.Message
import crypto.rsa.domain.model.Message.Companion.NO_CONTENT
import crypto.rsa.domain.model.MessageType.*
import crypto.rsa.domain.model.PublicKey
import crypto.rsa.domain.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService {

    @Autowired
    lateinit var messageRepository: MessageRepository

    fun createSimpleMessage(content: ByteArray?, publicKey: PublicKey, sender: AgentId, receiver: AgentId): Message {
        return save(
            Message(SIMPLE_MESSAGE, content, publicKey, sender, receiver)
        )
    }

    fun createMessageReceivedConfirmation(publicKey: PublicKey, sender: AgentId, receiver: AgentId): Message {
        return save(
            Message(MESSAGE_RECEIVED_CONFIRMATION, NO_CONTENT, publicKey, sender, receiver)
        )
    }

    fun createDecryptionError(publicKey: PublicKey, sender: AgentId, receiver: AgentId): Message {
        return save(
            Message(DECRYPTION_ERROR, NO_CONTENT, publicKey, sender, receiver)
        )
    }

    fun createPublicKeyRequest(publicKey: PublicKey, sender: AgentId, receiver: AgentId): Message {
        return save(
            Message(PUBLIC_KEY_REQUEST, NO_CONTENT, publicKey, sender, receiver)
        )
    }

    fun createPublicKeyResponse(publicKey: PublicKey, sender: AgentId, receiver: AgentId): Message {
        return save(
            Message(PUBLIC_KEY_RESPONSE, NO_CONTENT, publicKey, sender, receiver)
        )
    }

    fun save(m: Message): Message {
        return messageRepository.save(m)
    }
}