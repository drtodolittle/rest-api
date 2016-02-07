/**
 *
 */
package de.drtodolittle.processor

import java.security.Key

import javax.crypto.spec.SecretKeySpec

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

/**
 * @author Guenther_D

 */
public class ExtractEmailAndProcessId implements Processor {


	private String secret = "changeit"


	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */

	public void process(Exchange exchange) throws Exception {
		String[] input = exchange.in.body.split(";")
		if (input.length != 4) {
			throw new IllegalArgumentException("The body must be a string with four tokens divided by a ;. But the value is " + exchange.in.body);
		}
		def email = input[0]
		def processId = input[1]
		def firstname = input[2]
		def lastname = input[3]
		
		Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.ES256.getJcaName())
		String token = Jwts.builder().setSubject(email).claim("processId", processId).claim("firstname", firstname).claim("lastname", lastname).signWith(SignatureAlgorithm.HS512, key).compact()
		exchange.in.setHeader("email", email)
		exchange.in.setHeader("processId", processId)
		exchange.in.setHeader("firstname", firstname)
		exchange.in.setHeader("lastname", lastname)
		exchange.in.setHeader("token", token)
	}

	public void setSecret(String secret) {
		this.secret=secret
	}
	
}
