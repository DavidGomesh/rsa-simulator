package crypto.rsa.domain.repository

import crypto.rsa.domain.model.Message
import crypto.rsa.domain.model.MessageId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: JpaRepository<Message, MessageId>