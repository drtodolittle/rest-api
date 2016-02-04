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
public class CreateJsonWebToken implements Processor {


	private String secret = "changeit"


	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */

	public void process(Exchange exchange) throws Exception {
		String user = (String) exchange.getIn().getHeader("username")
		Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.ES256.getJcaName())
		String token = Jwts.builder().setSubject(user).signWith(SignatureAlgorithm.HS512, key).compact()
		exchange.getIn().setBody('{"token": "' + token + '"}')
	}

	public void setSecret(String secret) {
		this.secret=secret
	}
	
}
