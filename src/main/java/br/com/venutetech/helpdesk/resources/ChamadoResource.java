package br.com.venutetech.helpdesk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.venutetech.helpdesk.domain.Chamado;
import br.com.venutetech.helpdesk.domain.dtos.ChamadoDTO;
import br.com.venutetech.helpdesk.services.ChamadoService;

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
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> list = chamadoService.findAll();
		List<ChamadoDTO> listDTO = list.stream().map(ChamadoDTO::new).toList();
		return ResponseEntity.ok().body(listDTO);
	}
	
}
