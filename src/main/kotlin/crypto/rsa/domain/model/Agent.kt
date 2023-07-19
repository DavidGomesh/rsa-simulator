package crypto.rsa.domain.model

import crypto.rsa.domain.utils.KeyUtils.Companion.generateKeyPair
import crypto.rsa.domain.utils.KeyUtils.Companion.getPrivateKey
import crypto.rsa.domain.utils.KeyUtils.Companion.getPublicKey
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.UUID

typealias AgentId = String
typealias PrivateKeyId = UUID
typealias SharedPrivateKeyId = UUID
typealias PublicKey = String
typealias Key = String

@Entity
class Agent(@Id val agentId: AgentId) {

    @NotNull
    @Column(length = 512)
    val publicKey: PublicKey

    @OneToOne
    @JoinColumn(name = "private_key_fk")
    val privateKey: PrivateKey

    @OneToMany(mappedBy = "sharedWithAgent")
    val sharedPrivateKeys: List<SharedPrivateKey>

    init {
        val keyPair = generateKeyPair()
        this.publicKey = getPublicKey(keyPair)
        this.privateKey = getPrivateKey(keyPair)
        this.sharedPrivateKeys = mutableListOf()
    }
}