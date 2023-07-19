package crypto.rsa.domain.repository

import crypto.rsa.domain.model.PrivateKey
import crypto.rsa.domain.model.PrivateKeyId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrivateKeyRepository: JpaRepository<PrivateKey, PrivateKeyId>