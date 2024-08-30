package farias.paulino.kauan.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farias.paulino.kauan.HelpDesk.DTO.ClienteDTO;
import farias.paulino.kauan.HelpDesk.domain.Pessoa;
import farias.paulino.kauan.HelpDesk.domain.Cliente;
import farias.paulino.kauan.HelpDesk.repository.PessoaRepository;
import farias.paulino.kauan.HelpDesk.repository.ClienteRepository;
import farias.paulino.kauan.HelpDesk.services.exceptions.DataIntegrityViolationException;
import farias.paulino.kauan.HelpDesk.services.exceptions.NotFoundException;
import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		verificaCpfEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		Cliente oldCliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		verificaCpfEmail(clienteDTO);
		oldCliente = new Cliente(clienteDTO);
		return clienteRepository.save(oldCliente);
	}

	public void delete(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		if(cliente.getChamados().size()>0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado");
		}
		clienteRepository.delete(cliente);
	}

	private void verificaCpfEmail(ClienteDTO clienteDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf ja cadastrado");
		}

		pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado");
		}
	}

}
