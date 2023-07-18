package crypto.rsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RsaApplication

fun main(args: Array<String>) {
	runApplication<RsaApplication>(*args)
}
