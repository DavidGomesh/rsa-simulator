package crypto.rsa.api.controller

import crypto.rsa.domain.model.Agent
import crypto.rsa.domain.model.AgentId
import crypto.rsa.domain.repository.AgentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/agents")
class AgentController {

    @Autowired
    lateinit var agentRepository: AgentRepository

    @GetMapping
    fun index(pageable: Pageable): Page<Agent> {
        return agentRepository.findAll(pageable)
    }

    @PostMapping("{id}")
    fun create(@PathVariable id: AgentId): Agent {
        val agent = Agent(id)

        println("Private Key: ")
        println(agent.privateKey.hashCode())

        println("Public Key: ")
        println(agent.publicKey.hashCode())
        println("")
        println("")

        return agentRepository.save(agent)
//        return agent
    }

}