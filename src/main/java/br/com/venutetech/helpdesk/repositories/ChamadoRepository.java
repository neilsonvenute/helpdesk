package br.com.venutetech.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.venutetech.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
