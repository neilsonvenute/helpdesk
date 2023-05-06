package br.com.venutetech.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.venutetech.helpdesk.domain.Tecnico;
import br.com.venutetech.helpdesk.repositories.TecnicoRepository;
import br.com.venutetech.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id));
	}

	public List<Tecnico> finfAll() {
		return tecnicoRepository.findAll();
	}
}
