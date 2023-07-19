package crypto.rsa.domain.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
class PrivateKey (@NotNull @Column(length = 512, name = "private_key") val key: Key) {
    @Id
    val privateKeyId: PrivateKeyId = UUID.randomUUID()
}

@Entity
class SharedPrivateKey (
    @NotNull
    val belongsToAgent: AgentId,

    @ManyToOne
    @JoinColumn(name = "private_key_fk")
    val privateKey: PrivateKey,

    @ManyToOne
    @JoinColumn(name = "shared_with_agent_fk")
    val sharedWithAgent: Agent) {

    @Id
    val sharedPrivateKeyId: SharedPrivateKeyId = UUID.randomUUID()
}