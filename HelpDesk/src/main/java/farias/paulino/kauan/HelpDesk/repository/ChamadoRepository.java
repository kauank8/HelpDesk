package farias.paulino.kauan.HelpDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import farias.paulino.kauan.HelpDesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
