package br.com.venutetech.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.venutetech.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
