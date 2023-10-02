package br.com.matheugenioti.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.matheugenioti.services.PersonServices;
import br.com.matheugenioti.vo.v1.PersonVO;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
	return service.findAll();
		
	}
	
	@GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable(value = "id" )Long id) throws Exception {
		return service.findById(id);
		
	}
	
	@PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
			
	public PersonVO create(@RequestBody PersonVO PersonVO)   {
		return service.create(PersonVO);
		
	}
	
	@PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
			
	public PersonVO update(@RequestBody PersonVO PersonVO)   {
		return service.update(PersonVO);
		
	}
	
	@DeleteMapping(value = "/{id}")
            
	public ResponseEntity<?> delete(@PathVariable(value = "id" )Long id) {
		 service.delete(id);
		 return ResponseEntity.noContent().build();
		
	}
	
	
	
}
