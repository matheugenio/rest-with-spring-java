package br.com.matheugenioti.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.matheugenioti.exceptions.RequiredObjectIsNullException;
import br.com.matheugenioti.model.Book;
import br.com.matheugenioti.repositories.BookRepository;
import br.com.matheugenioti.services.BookServices;
import br.com.matheugenioti.unittests.mapper.mocks.MockBook;
import br.com.matheugenioti.vo.v1.BookVO;
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {
	
	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repositoy;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();
		
		when(repositoy.findAll()).thenReturn(list);
		
		var books = service.findAll();
		
		assertNotNull(books);
		assertEquals(14, books.size());
		
		var bookOne = books.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne.getLaunchDate());
		assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1",bookOne.getAuthor());
		assertEquals(120D, bookOne.getPrice());
		assertEquals("Title Test1",bookOne.getTitle());
		
		
		var bookFour = books.get(4);
		
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour);
		assertNotNull(bookFour.getLinks());
		assertNotNull(bookFour.getLaunchDate());
		assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
		assertEquals("Author Test4",bookFour.getAuthor());
		assertEquals(120D, bookFour.getPrice());
		assertEquals("Title Test4",bookFour.getTitle());
		
		var bookSeven = books.get(7);
		
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getLinks());
		assertNotNull(bookSeven.getLaunchDate());
		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Author Test7",bookSeven.getAuthor());
		assertEquals(120D, bookSeven.getPrice());
		assertEquals("Title Test7",bookSeven.getTitle());
	}

	@Test
	void testFindById() throws Exception {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repositoy.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLaunchDate());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1",result.getAuthor());
		assertEquals(120D, result.getPrice());
		assertEquals("Title Test1",result.getTitle());

		
		
	}

	@Test
	void testCreate() throws Exception {
		Book entity = input.mockEntity(1);
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repositoy.save(entity)).thenReturn((persisted));
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLaunchDate());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1",result.getAuthor());
		assertEquals(120D, result.getPrice());
		assertEquals("Title Test1",result.getTitle());


		
		
	}
	@Test
	void testCreateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.create(null);});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
		
	}

	@Test
	void testUpdate() throws Exception {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repositoy.findById(1L)).thenReturn(Optional.of(entity));
		when(repositoy.save(entity)).thenReturn((persisted));
		
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLaunchDate());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1",result.getAuthor());
		assertEquals(120D, result.getPrice());
		assertEquals("Title Test1",result.getTitle());


		
	}
	
	@Test
	void testUpdateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.update(null);});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
		
	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repositoy.findById(1L)).thenReturn(Optional.of(entity));
		
		 service.delete(1L);
	}

}
