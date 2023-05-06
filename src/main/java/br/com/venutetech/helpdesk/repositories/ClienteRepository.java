package br.com.venutetech.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.venutetech.helpdesk.domain.Pessoa;

public interface ClienteRepository extends JpaRepository<Pessoa, Integer> {

}
