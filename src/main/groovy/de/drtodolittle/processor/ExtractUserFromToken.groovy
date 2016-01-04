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


	private String secret = "camundaunddrtodolittlesecret"


	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */

	public void process(Exchange exchange) throws Exception {
		String token = ((String) exchange.getIn().getHeader("Authorization")).split(" ")[1]
		Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.ES256.getJcaName())
    String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject()
    exchange.getIn().setHeader("username", user)
	}

}
