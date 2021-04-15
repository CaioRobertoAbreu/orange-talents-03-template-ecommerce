package br.com.zup.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
@RestController
public class MercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@PostMapping("/index")
	public String indexTeste() {
		return "Funcionou porra!";
	}
}
