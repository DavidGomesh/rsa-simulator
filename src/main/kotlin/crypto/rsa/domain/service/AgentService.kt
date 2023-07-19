package crypto.rsa.domain.service

import crypto.rsa.domain.model.Agent
import crypto.rsa.domain.repository.AgentRepository
import crypto.rsa.domain.repository.PrivateKeyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AgentService {
    @Autowired
    lateinit var privateKeyService: PrivateKeyService

    @Autowired
    lateinit var agentRepository: AgentRepository

    fun save(a: Agent): Agent {
        privateKeyService.save(a.privateKey)
        return agentRepository.save(a)
    }
}