package farias.paulino.kauan.HelpDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import farias.paulino.kauan.HelpDesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
