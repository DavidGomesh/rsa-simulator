package crypto.rsa.api.controller

import crypto.rsa.domain.model.AgentId
import crypto.rsa.domain.model.Message
import crypto.rsa.domain.service.SenderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/sender")
class SenderController {

    @Autowired
    lateinit var senderService: SenderService

    @GetMapping("request-public-key")
    fun requestPublicKey(@RequestParam sender: AgentId, @RequestParam receiver: AgentId): Message {
        return senderService.requestPublicKey(sender, receiver)
    }

    @PostMapping("send-message")
    fun sendMessage(
        @RequestParam sender: AgentId,
        @RequestParam receiver: AgentId,
        @RequestParam message: String): Message? {

        return senderService.sendMessage(sender, receiver, message.toByteArray())
    }
}