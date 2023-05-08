package br.com.venutetech.helpdesk.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.venutetech.helpdesk.domain.Cliente;
import br.com.venutetech.helpdesk.domain.Pessoa;
import br.com.venutetech.helpdesk.domain.dtos.ClienteDTO;
import br.com.venutetech.helpdesk.repositories.ClienteRepository;
import br.com.venutetech.helpdesk.repositories.PessoaRepository;
import br.com.venutetech.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.venutetech.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id));
	}

	public List<Cliente> finfAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCPFEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return clienteRepository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCPFEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		
		return clienteRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(!obj.getChamados().isEmpty()) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		
		clienteRepository.deleteById(id);
	}

	private void validaPorCPFEEmail(ClienteDTO objDTO) {
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
