package farias.paulino.kauan.HelpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder encode;
	
	public void instanciaDB() {
		// Criação de Clientes
		Cliente cliente1 = new Cliente(null, "João Silva", "55747466038", "joao.silva@example.com", encode.encode("senha123"));
		Cliente cliente2 = new Cliente(null, "Maria Oliveira", "30666430004", "maria.oliveira@example.com",  encode.encode("senha123"));
		Cliente cliente3 = new Cliente(null, "Carlos Pereira", "34828051058", "carlos.pereira@example.com",  encode.encode("senha123"));

		// Criação de Técnicos
		Tecnico tecnico1 = new Tecnico(null, "Lucas Souza", "79331248016", "lucas.souza@example.com",  encode.encode("senha123"));
		Tecnico tecnico2 = new Tecnico(null, "Ana Costa", "72088794000", "ana.costa@example.com",  encode.encode("senha123"));
		Tecnico tecnico3 = new Tecnico(null, "Paulo Lima", "05299917058", "paulo.lima@example.com",  encode.encode("senha123"));

		// Criação de Chamados
		Chamado chamado1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Erro no sistema", "Sistema não inicializa", tecnico1, cliente1);
		Chamado chamado2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Problema de login", "Usuário não consegue logar", tecnico2, cliente2);
		Chamado chamado3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Dúvida sobre funcionalidade", "Cliente tem dúvidas sobre uso", tecnico3, cliente3);
		
		tecnicoRepository.saveAll(Arrays.asList(tecnico1,tecnico2,tecnico3));
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3));
		chamadoRepository.saveAll(Arrays.asList(chamado1,chamado2,chamado3));
		
	}
	
	
}
