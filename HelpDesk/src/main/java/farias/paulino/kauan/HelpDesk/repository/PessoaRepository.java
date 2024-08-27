package farias.paulino.kauan.HelpDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import farias.paulino.kauan.HelpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
