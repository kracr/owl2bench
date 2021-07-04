package owl.cs.myfirst.owlapi;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

//<dependency>
//<groupId>org.slf4j</groupId>
//<artifactId>slf4j-log4j12</artifactId>
//<version>1.7.5</version>
//	    <exclusions>
// <exclusion>
//     <groupId>org.slf4j</groupId>
//     <artifactId>slf4j-log4j12</artifactId>
// </exclusion>
//</exclusions>
//</dependency>

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@SpringBootApplication
@RequestMapping("/api")
public class Demo2 {
	public static void main(String[] args) {
		SpringApplication.run(Demo2.class, args);
	}
	
	@RequestMapping(value="/send", method = RequestMethod.POST)
	//@PostMapping(path = "/send")
	//public void frontendValue(@RequestBody UserData params) {
	public void frontendValue(@RequestBody com.fasterxml.jackson.databind.JsonNode params ) throws OWLOntologyStorageException, OWLOntologyCreationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException {
		//List len = params.getValues();
		//System.out.println();
		JsonNode arrNode = params.get("userValues");
		int[] userval = new int[arrNode.size()];
		int i = 0;
		if (arrNode.isArray()) {
		    for ( JsonNode objNode : arrNode) {
		    	userval[i] = objNode.asInt();
		    	i++;
//		    	if( objNode.asInt() != 0 ) {
//		    		System.out.println(objNode.asInt());
//		    	}   
		    }
		}
		//System.out.println("len "+userval.length);
		app App = new app();
		App.generate(userval);
	}
}