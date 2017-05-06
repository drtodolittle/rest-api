/**
 *
 */
package de.drtodolittle.processor;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * @author Guenther_D
 *
 */
public class JsonWebTokenTest {

	@Test
	public void testCreateToken() {
		Key key = new SecretKeySpec("dirk".getBytes(), SignatureAlgorithm.ES256.getJcaName());

		String s = Jwts.builder().setSubject("dirk").signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println(s);
	}

	@Test
	public void testVerifyToken() {
		Key key = new SecretKeySpec("dirk".getBytes(), SignatureAlgorithm.ES256.getJcaName());

		String s = Jwts.builder().setSubject("dirk").signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println(s);
		try {

		    Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(s);
		    Assert.assertEquals("dirk", parseClaimsJws.getBody().getSubject());

		    System.out.println("Alles gut");

		} catch (SignatureException e) {

		    System.out.println("Hier stimmt was nicht");
		}
	}
}
