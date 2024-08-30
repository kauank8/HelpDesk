package farias.paulino.kauan.HelpDesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farias.paulino.kauan.HelpDesk.DTO.ChamadoDTO;
import farias.paulino.kauan.HelpDesk.domain.Chamado;
import farias.paulino.kauan.HelpDesk.domain.Cliente;
import farias.paulino.kauan.HelpDesk.domain.Tecnico;
import farias.paulino.kauan.HelpDesk.domain.enums.Prioridade;
import farias.paulino.kauan.HelpDesk.domain.enums.Status;
import farias.paulino.kauan.HelpDesk.repository.ChamadoRepository;
import farias.paulino.kauan.HelpDesk.services.exceptions.NotFoundException;
import jakarta.validation.Valid;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new NotFoundException("Chamado não encontrado"));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(newChamado(chamadoDTO));
	}

	public Chamado update(@Valid Integer id, ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		Chamado oldChamado = findById(id);
		oldChamado = newChamado(chamadoDTO);
		return chamadoRepository.save(oldChamado);
	}

	private Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		if(chamadoDTO.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		return chamado;
	}

}
