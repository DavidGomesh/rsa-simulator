package crypto.rsa.runner

import crypto.rsa.domain.model.Agent
import crypto.rsa.domain.service.AgentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class PopulateDatabase: CommandLineRunner {

    @Autowired
    lateinit var agentService: AgentService

    override fun run(vararg args: String?) {
        agentService.save(Agent("agent01"))
        agentService.save(Agent("agent02"))
    }
}