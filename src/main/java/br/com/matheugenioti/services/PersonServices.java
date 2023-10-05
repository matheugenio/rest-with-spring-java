package br.com.matheugenioti.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import br.com.matheugenioti.controllers.PersonController;
import br.com.matheugenioti.exceptions.RequiredObjectIsNullException;
import br.com.matheugenioti.exceptions.ResourceNotFoundException;
import br.com.matheugenioti.mapper.DozerMapper;
import br.com.matheugenioti.model.Person;
import br.com.matheugenioti.repositories.PersonRepository;
import br.com.matheugenioti.vo.v1.PersonVO;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {
		
		logger.info("Finding all people!");
	
		var persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return persons;
	
	}
	
	public PersonVO findById(Long id) throws Exception {
		logger.info("Finding one PersonVO!");

		Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		var vo =  DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;

	}
	
	public PersonVO create(PersonVO person) throws Exception   {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one PersonVO!");
		Person entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one PersonVO!");
		 Person entity = repository.findById(person.getKey())
				 .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		 	
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setAddress(person.getAddress());
			entity.setGender(person.getGender());
			
			var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
			vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
			return vo;
	}
	
	public void  delete(Long id) {
		logger.info("Deleting one person!");
		
		 Person entity = repository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
		
	}
	

}
