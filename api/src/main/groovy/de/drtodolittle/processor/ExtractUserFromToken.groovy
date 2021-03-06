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
public class ExtractUserFromToken implements Processor {


	private String secret = "changeit"


	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */

	public void process(Exchange exchange) throws Exception {
		def authorization = exchange.in.headers.Authorization
		if (authorization != null) {
			def authorizationTokens = authorization.split(" ");
			if (authorizationTokens.size() == 2) {
				def token = authorizationTokens[1]
				Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.ES256.getJcaName())
				String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject()
				exchange.in.setHeader("username", user)
			}
			else {
				throw new IllegalArgumentException("No authorization token is set");
			}
		}
		else {
			throw new IllegalArgumentException("No authorization header is set");
		}
	}

	public void setSecret(String secret) {
		this.secret=secret
	}

}
