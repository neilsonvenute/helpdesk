package br.com.venutetech.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.venutetech.helpdesk.domain.Chamado;
import br.com.venutetech.helpdesk.domain.Cliente;
import br.com.venutetech.helpdesk.domain.Tecnico;
import br.com.venutetech.helpdesk.domain.enums.Perfil;
import br.com.venutetech.helpdesk.domain.enums.Prioridade;
import br.com.venutetech.helpdesk.domain.enums.Status;
import br.com.venutetech.helpdesk.repositories.ChamadoRepository;
import br.com.venutetech.helpdesk.repositories.ClienteRepository;
import br.com.venutetech.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "73773038089", "valdir@mail.com", "123");
		tec1.addPerfis(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "91335602003", "torvalds@mail.com", "123");
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(cha1));
	}
}
