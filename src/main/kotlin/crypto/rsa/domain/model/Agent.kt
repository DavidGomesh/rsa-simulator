package crypto.rsa.domain.model

import crypto.rsa.domain.utils.KeyUtils.Companion.generateKeyPair
import crypto.rsa.domain.utils.KeyUtils.Companion.getPrivateKey
import crypto.rsa.domain.utils.KeyUtils.Companion.getPublicKey
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.UUID

typealias AgentId = String
typealias PrivateKeyId = UUID
typealias PrivateKey = String
typealias PublicKey = String

@Entity
class Agent(@Id val agentId: AgentId) {

    @NotNull
    @Column(length = 512)
    val publicKey: PublicKey

    @NotNull
    @Column(length = 512)
    val privateKey: PrivateKey

    init {
        val keyPair = generateKeyPair()
        this.publicKey = getPublicKey(keyPair)
        this.privateKey = getPrivateKey(keyPair)
    }
}