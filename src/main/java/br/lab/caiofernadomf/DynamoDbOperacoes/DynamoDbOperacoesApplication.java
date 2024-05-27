package br.lab.caiofernadomf.DynamoDbOperacoes;

import br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations.CheckTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamoDbOperacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamoDbOperacoesApplication.class, args);
	}

}
