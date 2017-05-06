/**
 * 
 */
package de.drtodolittle.firebase.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;

import de.drtodolittle.firebase.api.TokenService;

/**
 * @author Guenther_D
 *
 */

public class FirebaseTokenService implements TokenService {

	public static final String PID = "de.drtodolittle.firebase.firebasetokenservice";
	
	/* (non-Javadoc)
	 * @see de.drtodolittle.firebase.api.TokenService#verify(java.lang.String)
	 */
	public String verify(String token) {
		String email = null;
		Task<FirebaseToken> task = FirebaseAuth.getInstance().verifyIdToken(token);
		while (!task.isComplete()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (task.isSuccessful()) {
			email = task.getResult().getEmail();
		}
		else {
			task.getException().printStackTrace(); 
		}
		return email;
	}

}
