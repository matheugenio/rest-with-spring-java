package br.com.matheugenioti.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheugenioti.exceptions.ResourceNotFoundException;
import br.com.matheugenioti.mapper.DozerMapper;
import br.com.matheugenioti.mapper.custom.PersonMapper;
import br.com.matheugenioti.model.Person;
import br.com.matheugenioti.repositories.PersonRepository;
import br.com.matheugenioti.vo.v1.PersonVO;
import br.com.matheugenioti.vo.v2.PersonVOV2;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonVO> findAll() {
		
		logger.info("Finding all people!");
	
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	
	}
	
	public PersonVO findById(Long id) {
		logger.info("Finding one PersonVO!");

		Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);

	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one PersonVO!");
		Person entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one PersonVOV2!");
		Person entity = mapper.convertVoToEntity(person);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one PersonVO!");
		 Person entity = repository.findById(person.getId())
				 .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		 	
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setAddress(person.getAddress());
			entity.setGender(person.getGender());
			
			var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
			return vo;	
	}
	
	public void  delete(Long id) {
		logger.info("Deleting one person!");
		
		 Person entity = repository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
		
	}

	
	

}
