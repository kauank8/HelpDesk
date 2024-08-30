package farias.paulino.kauan.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farias.paulino.kauan.HelpDesk.DTO.TecnicoDTO;
import farias.paulino.kauan.HelpDesk.domain.Pessoa;
import farias.paulino.kauan.HelpDesk.domain.Tecnico;
import farias.paulino.kauan.HelpDesk.repository.PessoaRepository;
import farias.paulino.kauan.HelpDesk.repository.TecnicoRepository;
import farias.paulino.kauan.HelpDesk.services.exceptions.DataIntegrityViolationException;
import farias.paulino.kauan.HelpDesk.services.exceptions.NotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(()-> new NotFoundException("Tecnico não encontrado"));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		verificaCpfEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}

	private void verificaCpfEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if(pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf ja cadastrado");
		}
		
		pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if(pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado");
		}
	}
}
