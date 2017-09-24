package de.drtodolittle.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

import de.drtodolittle.processor.*;
import de.drtodolittle.aggregate.*;
import de.drtodolittle.firebase.impl.FirebaseTokenService;
import de.drtodolittle.firebase.api.TokenService;


@SpringBootApplication
public class DrToDoLittleSpringApplication {
    
    
    /**
     * A main method to start this application.
    */
    public static void main(String[] args) {
    	SpringApplication.run(DrToDoLittleSpringApplication.class, args);
    }
    
   
	@Bean
	public ValidateBodyProcessor extractTaskIdProcessor() {
		return new ValidateBodyProcessor(new ExtractTaskId());
	}
	
	@Bean
	public ValidateBodyProcessor extractCreatedTaskId() {
		return new ValidateBodyProcessor(new ExtractCreatedTaskId());
	}
	@Bean
	public ValidateBodyProcessor generateToDoJSONProcessor() {
		return new ValidateBodyProcessor(new GenerateToDoJSON());
	}
	@Bean
	public ValidateBodyProcessor generateCreateProcessJSONProcessor() {
		return new ValidateBodyProcessor(new GenerateCreateProcessJSON());
	}
	@Bean
	public ValidateBodyProcessor generateCreateProcessUserRegistryJSON() {
		return new ValidateBodyProcessor(new GenerateCreateProcessUserRegistryJSON());
	}
	@Bean
	public ValidateBodyProcessor extracttopic() {
		return new ValidateBodyProcessor(new ExtractTopic());
	}
	
	@Bean
	public ValidateBodyProcessor extractProcessIdsProcessor() {
		return new ValidateBodyProcessor(new ExtractProcessInstanceIds());
	}
	
	@Bean
	public ProcessInstanceVariables processInstanceVariablesAggregationStrategy() {
		return new ProcessInstanceVariables();
	}
	
	@Bean
	public ExtractUserFromFirebaseToken extractuserfromtoken() {
		try {
		ExtractUserFromFirebaseToken token = new ExtractUserFromFirebaseToken();
		token.setService(tokenService());
		return token;
		}
		catch (Exception e) {
			throw new RuntimeException("Fehler", e);		
		}
	}

		@Value("${firebase.private.key}") String firebasePrivateKey;
		@Value("${firebase.database.uri}") String databaseUri;

   
  	public TokenService tokenService() throws Exception {
		return new FirebaseTokenService(firebasePrivateKey, databaseUri);	
	}   
 
}                    
