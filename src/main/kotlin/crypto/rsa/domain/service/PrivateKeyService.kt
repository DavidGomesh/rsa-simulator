package crypto.rsa.domain.service

import crypto.rsa.domain.model.PrivateKey
import crypto.rsa.domain.repository.PrivateKeyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrivateKeyService {
    @Autowired
    lateinit var privateKeyRepository: PrivateKeyRepository

    fun save(pk: PrivateKey): PrivateKey {
        return privateKeyRepository.save(pk)
    }
}