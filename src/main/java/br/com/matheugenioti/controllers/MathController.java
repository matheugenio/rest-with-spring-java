package br.com.matheugenioti.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheugenioti.converters.ConvertNumbers;
import br.com.matheugenioti.exceptions.UnsupportedMathOperationException;
import br.com.matheugenioti.math.MathOperations;

@RestController
public class MathController {
	
	MathOperations math = new MathOperations();
	
	
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!ConvertNumbers.isNumeric(numberOne) || !ConvertNumbers.isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sum(ConvertNumbers.convertToDouble(numberOne) , ConvertNumbers.convertToDouble(numberTwo));

	}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!ConvertNumbers.isNumeric(numberOne) || !ConvertNumbers.isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sub(ConvertNumbers.convertToDouble(numberOne) ,ConvertNumbers.convertToDouble(numberTwo));

	}
	
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!ConvertNumbers.isNumeric(numberOne) || !ConvertNumbers.isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.div (ConvertNumbers.convertToDouble(numberOne), ConvertNumbers.convertToDouble(numberTwo));

	}


	@GetMapping("/mean/{numberOne}/{numberTwo}")
	public Double mean(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!ConvertNumbers.isNumeric(numberOne) || !ConvertNumbers.isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.mean (ConvertNumbers.convertToDouble(numberOne) , ConvertNumbers.convertToDouble(numberTwo));

	}
	
	@GetMapping("/sqrt/{numberOne}")
	public Double sqrt(
			@PathVariable(value = "numberOne" )
			String numberOne
			
			) throws Exception {
		if(!ConvertNumbers.isNumeric(numberOne)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.sqrt(ConvertNumbers.convertToDouble(numberOne));

	}
	
}
