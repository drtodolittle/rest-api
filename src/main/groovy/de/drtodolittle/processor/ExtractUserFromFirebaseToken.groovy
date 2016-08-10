/**
 *
 */
package de.drtodolittle.processor

import org.apache.camel.Exchange
import org.apache.camel.Processor
import de.drtodolittle.firebase.api.TokenService


/**
 * @author Guenther_D

 */
public class ExtractUserFromFirebaseToken implements Processor {


	private def service = null


	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */

	public void process(Exchange exchange) throws Exception {
		def authorization = exchange.in.headers.Authorization
		if (authorization != null) {
			def authorizationTokens = authorization.split(" ")
			if (authorizationTokens.size() == 2) {
				def token = authorizationTokens[1]
				String user = service.verify(token)
				if (user == null) {
					throw new IllegalArgumentException("Token is not valid")
				}
				exchange.in.setHeader("username", user)
			}
			else {
				throw new IllegalArgumentException("No authorization token is set")
			}
		}
		else {
			throw new IllegalArgumentException("No authorization header is set")
		}
	}

	public void setService(TokenService service) {
		this.service=service
	}

}
