package crypto.rsa.domain.utils

import crypto.rsa.domain.model.PrivateKey
import crypto.rsa.domain.model.PublicKey
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.RSAKeyGenParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.security.PrivateKey as SecurityPrivateKey
import java.security.PublicKey as SecurityPublicKey
import java.util.*

class KeyUtils {
    companion object {
        private const val ALGORITHM = "RSA"

        fun generateKeyPair(): KeyPair {
            val kpg = KeyPairGenerator.getInstance(ALGORITHM)
            kpg.initialize(RSAKeyGenParameterSpec(512, RSAKeyGenParameterSpec.F0))
            return kpg.genKeyPair()
        }

        fun getPublicKey(keyPair: KeyPair): PublicKey {
            return Base64.getEncoder().encodeToString(keyPair.public.encoded)
        }

        fun getPrivateKey(keyPair: KeyPair): PrivateKey {
            val key = Base64.getEncoder().encodeToString(keyPair.private.encoded)
            return PrivateKey(key)
        }

        fun getPublicKey(publicKey: PublicKey): SecurityPublicKey {
            val keySpec = getKeySpec(publicKey)
            val keyFactory = KeyFactory.getInstance(ALGORITHM)
            return keyFactory.generatePublic(keySpec)
        }

        fun getPrivateKey(privateKey: PrivateKey): SecurityPrivateKey {
            val keySpec = getKeySpec(privateKey.key)
            val keyFactory = KeyFactory.getInstance(ALGORITHM)
            return keyFactory.generatePrivate(keySpec)
        }

        private fun getKeySpec(key: String): X509EncodedKeySpec {
            val keyBytes = Base64.getDecoder().decode(key)
            return X509EncodedKeySpec(keyBytes)
        }
    }
}