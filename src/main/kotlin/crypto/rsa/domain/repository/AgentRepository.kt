package crypto.rsa.domain.repository

import crypto.rsa.domain.model.Agent
import crypto.rsa.domain.model.AgentId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgentRepository: JpaRepository<Agent, AgentId>