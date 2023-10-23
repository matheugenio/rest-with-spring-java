package br.com.matheugenioti.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.matheugenioti.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.matheugenioti.services.PersonServices;
import br.com.matheugenioti.vo.v1.PersonVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People",
    	tags = {"People"},
    	responses =  {
    		@ApiResponse(description = "Success", responseCode ="200",
    				content = {
    						@Content(
    								mediaType = "application/json",
    								array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
    								)
    						}),
    				
    		@ApiResponse(description = "Bad Request", responseCode ="400", content = @Content),
    		@ApiResponse(description = "Unauthorized", responseCode ="401", content = @Content),
    		@ApiResponse(description = "Not Found", responseCode ="404", content = @Content),
    		@ApiResponse(description = "Internal Error", responseCode ="500", content = @Content),


    	}
            
  )
	public List<PersonVO> findAll() {
	return service.findAll();
		
	}
	@GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    
	@Operation(summary = "Finds a Person", description = "Finds a Person",
		tags = {"People"},
		responses =  {
			@ApiResponse(description = "Success", responseCode ="200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			
			@ApiResponse(description = "No Content", responseCode ="204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode ="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode ="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode ="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode ="500", content = @Content)
		}
	)
	
	public PersonVO findById(@PathVariable(value = "id" )Long id) throws Exception {
		return service.findById(id);
		
	}
	@PostMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	
	@Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON, XML or YML representation of the person",
		tags = {"People"},
		responses =  {
			@ApiResponse(description = "Success", responseCode ="200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			
			@ApiResponse(description = "Bad Request", responseCode ="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode ="401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode ="500", content = @Content),
	
	
		}
	    
	)
	
	public PersonVO create(@RequestBody PersonVO PersonVO) throws Exception   {
		return service.create(PersonVO);
		
	}
	
	@PutMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
			

	@Operation(summary = "Updates a  Person", description = "Updates a person by passing in a JSON, XML or YML representation of the person",
		tags = {"People"},
		responses =  {
			@ApiResponse(description = "Updated", responseCode ="200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			
			@ApiResponse(description = "Bad Request", responseCode ="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode ="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode ="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode ="500", content = @Content),
	
	
		}
	    
	)
	public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception   {
		return service.update(PersonVO);
		
	}
	
	@DeleteMapping(value = "/{id}")
	
	@Operation(summary = "Deletes a  Person", description = "Deletes a person by passing in a JSON, XML or YML representation of the person",
		tags = {"People"},
		responses =  {
			@ApiResponse(description = "No Content", responseCode ="204", content = @Content
			),
			
			@ApiResponse(description = "Bad Request", responseCode ="400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode ="401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode ="404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode ="500", content = @Content),
	
	
		}
	    
	)
            
	public ResponseEntity<?> delete(@PathVariable(value = "id" )Long id) {
		 service.delete(id);
		 return ResponseEntity.noContent().build();
		
	}
	
	
	
}
