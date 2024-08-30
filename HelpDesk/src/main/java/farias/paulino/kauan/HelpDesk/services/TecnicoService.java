package farias.paulino.kauan.HelpDesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farias.paulino.kauan.HelpDesk.domain.Tecnico;
import farias.paulino.kauan.HelpDesk.repository.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElse(null);
	}
}
