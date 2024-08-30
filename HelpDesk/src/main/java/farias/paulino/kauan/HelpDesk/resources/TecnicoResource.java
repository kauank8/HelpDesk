package farias.paulino.kauan.HelpDesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import farias.paulino.kauan.HelpDesk.DTO.TecnicoDTO;
import farias.paulino.kauan.HelpDesk.domain.Tecnico;
import farias.paulino.kauan.HelpDesk.services.TecnicoService;
import jakarta.validation.Valid;

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
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> tecnicos = tecnicoService.findAll();
		List<TecnicoDTO> tecnicosDTO = tecnicos.stream().map(obj-> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(tecnicosDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
		Tecnico tecnico = tecnicoService.create(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
