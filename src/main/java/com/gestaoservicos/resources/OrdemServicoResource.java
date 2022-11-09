package com.gestaoservicos.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.gestaoservicos.domain.OrdemServico;
import com.gestaoservicos.domain.dtos.OrdemServicoDTO;
import com.gestaoservicos.services.OrdemServicoService;

@RestController
@RequestMapping(value = "/ordemservico")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id) {
		OrdemServico obj = service.findById(id);
		return ResponseEntity.ok().body(new OrdemServicoDTO(obj));

	}

	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServico> list = service.findAll();
		List<OrdemServicoDTO> listDTO = list.stream().map(obj -> new OrdemServicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO objDTO) {
		OrdemServico obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(@PathVariable Integer id, @Valid @RequestBody OrdemServicoDTO objDTO) {
		OrdemServico newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new OrdemServicoDTO(newObj));
	}

}
