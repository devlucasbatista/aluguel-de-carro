package lcs.dev.aluguelDeCarro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marca esta classe como ponto de entrada da aplicação Spring Boot (habilita auto-configuração, component scan, etc.)
@SpringBootApplication
public class AluguelDeCarroApplication {

	// Método main: sobe o servidor embutido e inicializa o contexto do Spring
	public static void main(String[] args) {
		SpringApplication.run(AluguelDeCarroApplication.class, args);
	}

}
