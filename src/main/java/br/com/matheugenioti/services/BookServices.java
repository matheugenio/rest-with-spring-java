package br.com.matheugenioti.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheugenioti.controllers.BookController;
import br.com.matheugenioti.controllers.PersonController;
import br.com.matheugenioti.exceptions.RequiredObjectIsNullException;
import br.com.matheugenioti.exceptions.ResourceNotFoundException;
import br.com.matheugenioti.mapper.DozerMapper;
import br.com.matheugenioti.model.Book;
import br.com.matheugenioti.repositories.BookRepository;
import br.com.matheugenioti.vo.v1.BookVO;

@Service
public class BookServices {
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public List<BookVO> findAll(){
		
		logger.info("Finding all books!");
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream().forEach(b -> {
			try {
				b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return books;
	}
	
	public BookVO findById(Long id) {
		logger.info("finding one BookVO!");
		
		Book entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records founds for this ID!!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one BookVO!");
		Book entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public BookVO update(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one BookVO!");
		Book entity = repository.findById(book.getKey()).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!!"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}
	
	public void delete(Long id) {
		logger.info("Deleting one Book!!");
		
		 Book entity = repository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
		
	}
	

}
