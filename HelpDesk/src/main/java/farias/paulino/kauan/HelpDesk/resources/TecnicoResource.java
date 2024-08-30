package farias.paulino.kauan.HelpDesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import farias.paulino.kauan.HelpDesk.DTO.TecnicoDTO;
import farias.paulino.kauan.HelpDesk.domain.Tecnico;
import farias.paulino.kauan.HelpDesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));

	}
}
