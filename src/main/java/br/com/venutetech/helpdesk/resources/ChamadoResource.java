package br.com.venutetech.helpdesk.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.venutetech.helpdesk.domain.Chamado;
import br.com.venutetech.helpdesk.domain.dtos.ChamadoDTO;
import br.com.venutetech.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chamado")
public class ChamadoResource {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> list = chamadoService.findAll();
		List<ChamadoDTO> listDTO = list.stream().map(ChamadoDTO::new).toList();
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
		Chamado obj = chamadoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO){
		Chamado newObj = chamadoService.update(id, objDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(newObj));
	}

}
