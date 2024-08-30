package farias.paulino.kauan.HelpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farias.paulino.kauan.HelpDesk.domain.Chamado;
import farias.paulino.kauan.HelpDesk.domain.Cliente;
import farias.paulino.kauan.HelpDesk.domain.Tecnico;
import farias.paulino.kauan.HelpDesk.domain.enums.Prioridade;
import farias.paulino.kauan.HelpDesk.domain.enums.Status;
import farias.paulino.kauan.HelpDesk.repository.ChamadoRepository;
import farias.paulino.kauan.HelpDesk.repository.ClienteRepository;
import farias.paulino.kauan.HelpDesk.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		// Criação de Clientes
		Cliente cliente1 = new Cliente(null, "João Silva", "123.456.789-00", "joao.silva@example.com", "senha123");
		Cliente cliente2 = new Cliente(null, "Maria Oliveira", "987.654.321-00", "maria.oliveira@example.com", "senha123");
		Cliente cliente3 = new Cliente(null, "Carlos Pereira", "555.444.333-22", "carlos.pereira@example.com", "senha123");

		// Criação de Técnicos
		Tecnico tecnico1 = new Tecnico(null, "Lucas Souza", "111.222.333-44", "lucas.souza@example.com", "senha123");
		Tecnico tecnico2 = new Tecnico(null, "Ana Costa", "222.333.444-55", "ana.costa@example.com", "senha123");
		Tecnico tecnico3 = new Tecnico(null, "Paulo Lima", "333.444.555-66", "paulo.lima@example.com", "senha123");

		// Criação de Chamados
		Chamado chamado1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Erro no sistema", "Sistema não inicializa", tecnico1, cliente1);
		Chamado chamado2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Problema de login", "Usuário não consegue logar", tecnico2, cliente2);
		Chamado chamado3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Dúvida sobre funcionalidade", "Cliente tem dúvidas sobre uso", tecnico3, cliente3);
		
		tecnicoRepository.saveAll(Arrays.asList(tecnico1,tecnico2,tecnico3));
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3));
		chamadoRepository.saveAll(Arrays.asList(chamado1,chamado2,chamado3));
		
	}
	
	
}
