package crypto.rsa.domain.utils

import crypto.rsa.domain.model.PrivateKey
import crypto.rsa.domain.model.PublicKey
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAKeyGenParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import java.security.PrivateKey as SecurityPrivateKey
import java.security.PublicKey as SecurityPublicKey

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
            return Base64.getEncoder().encodeToString(keyPair.private.encoded)
        }

        fun getPublicKey(publicKey: PublicKey): SecurityPublicKey {
            val keySpec = X509EncodedKeySpec(Base64.getDecoder().decode(publicKey))
            val keyFactory = KeyFactory.getInstance(ALGORITHM)
            return keyFactory.generatePublic(keySpec)
        }

        fun getPrivateKey(privateKey: PrivateKey): SecurityPrivateKey {
            val keySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey))
            val keyFactory = KeyFactory.getInstance(ALGORITHM)
            return keyFactory.generatePrivate(keySpec)
        }
    }
}