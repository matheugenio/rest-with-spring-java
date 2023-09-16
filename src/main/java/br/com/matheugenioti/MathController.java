package br.com.matheugenioti;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheugenioti.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	private static final String template = "Hello, %s! Você tem %d anos!";
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);

	}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return convertToDouble(numberOne) - convertToDouble(numberTwo);

	}
	
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return convertToDouble(numberOne) / convertToDouble(numberTwo);

	}


	@GetMapping("/mean/{numberOne}/{numberTwo}")
	public Double mean(
			@PathVariable(value = "numberOne" )
			String numberOne,
			@PathVariable(value = "numberTwo")
			String numberTwo
			) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return convertToDouble((numberOne) + convertToDouble(numberTwo)) / 2;

	}
	
	@GetMapping("/sqrt/{numberOne}")
	public Double sqrt(
			@PathVariable(value = "numberOne" )
			String numberOne
			
			) throws Exception {
		if(!isNumeric(numberOne)) {
			throw  new UnsupportedMathOperationException("Please set a numeric value!");
		}
		convertToDouble(numberOne);
		return Math.sqrt(convertToDouble(numberOne));

	}
	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if(isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
