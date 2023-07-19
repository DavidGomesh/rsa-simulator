package crypto.rsa.domain.utils

import java.security.Key
import javax.crypto.Cipher

class RSAUtils {
    companion object {
        private const val ALGORITHM = "RSA"

        fun encode(content: ByteArray, key: Key): ByteArray {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            return cipher.doFinal(content)
        }

        fun decode(content: ByteArray, key: Key): ByteArray {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, key)
            return cipher.doFinal(content)
        }
    }
}