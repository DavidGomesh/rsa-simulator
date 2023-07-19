package crypto.rsa.domain.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

typealias MessageId = Int

@Entity
class Message (

    @NotNull
    @Enumerated(value = EnumType.STRING)
    val messageType: MessageType,

    val content: ByteArray?,

    @NotNull
    val publicKey: PublicKey,

    @NotNull
    val sender: AgentId,

    @NotNull
    val receiver: AgentId) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val messageId: MessageId? = null

    @NotNull
    val timestamp: LocalDateTime = LocalDateTime.now()

    companion object {
        val NO_CONTENT = null
    }
}

enum class MessageType {
    SIMPLE_MESSAGE,
    MESSAGE_RECEIVED_CONFIRMATION,

    DECRYPTION_ERROR,

    PUBLIC_KEY_REQUEST,
    PUBLIC_KEY_RESPONSE,
}