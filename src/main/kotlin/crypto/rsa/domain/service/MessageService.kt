package crypto.rsa.domain.service

import crypto.rsa.domain.model.Message
import crypto.rsa.domain.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService {

    @Autowired
    lateinit var messageRepository: MessageRepository

    fun save(m: Message): Message {
        return messageRepository.save(m)
    }
}