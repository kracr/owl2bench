package owl.cs.myfirst.owlapi;

//import com.beust.jcommander.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@SpringBootApplication
@RequestMapping("/api")
public class Demo2 {
	public static void main(String[] args) {
		SpringApplication.run(Demo2.class, args);
	}
	
	@RequestMapping(value="/send", method = RequestMethod.POST)
	//public void frontendValue(@RequestBody UserData params) {
	public void frontendValue(@RequestBody com.fasterxml.jackson.databind.JsonNode params ) throws OWLOntologyStorageException, OWLOntologyCreationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException {
		JsonNode arrNode = params.get("userValues");
		int[] userval = new int[arrNode.size()];
		int i = 0;
		if (arrNode.isArray()) {
		    for ( JsonNode objNode : arrNode) {
		    	userval[i] = objNode.asInt();
		    	i++;  
		    }
		}
		String format = params.get("format").asText();
		boolean extraAxioms = params.get("extra").asBoolean();
		System.out.println("                      STARTING                      "+format+" || "+extraAxioms+" || "+userval.length);
		app App = new app();
		App.generate(userval,format,extraAxioms);
	}
	
//	static MainCLIParameters mainArgs = new MainCLIParameters();
//	
//	public static void main(String[] args) {
//		handleInputArgs(args);
//	}
//	
//	public static void handleInputArgs(String[] args) {
//		JCommander jCommander = new JCommander(mainArgs);
//	}
}