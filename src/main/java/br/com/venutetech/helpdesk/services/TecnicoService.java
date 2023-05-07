package br.com.venutetech.helpdesk.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.venutetech.helpdesk.domain.Pessoa;
import br.com.venutetech.helpdesk.domain.Tecnico;
import br.com.venutetech.helpdesk.domain.dtos.TecnicoDTO;
import br.com.venutetech.helpdesk.repositories.PessoaRepository;
import br.com.venutetech.helpdesk.repositories.TecnicoRepository;
import br.com.venutetech.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.venutetech.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id));
	}

	public List<Tecnico> finfAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCPFEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCPFEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		
		return tecnicoRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		
		tecnicoRepository.deleteById(id);
	}

	private void validaPorCPFEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())) {
			throw new DataIntegrityViolationException("CPF já cadastro no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())) {
			throw new DataIntegrityViolationException("E-mail já cadastro no sistema");
		}
	}
}
