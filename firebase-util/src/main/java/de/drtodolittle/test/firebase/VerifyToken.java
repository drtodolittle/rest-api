/**
 * 
 */
package de.drtodolittle.test.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.OnSuccessListener;

/**
 * @author Guenther_D
 *
 */
public class VerifyToken {

	
	public VerifyToken() throws FileNotFoundException {
		
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setServiceAccount(new FileInputStream("DrToDoLittle-10b9f17b6eb3.json"))
				  .setDatabaseUrl("https://drtodolittle.firebaseio.com/")
				  .build();
				FirebaseApp.initializeApp(options);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VerifyToken verifyToken = new VerifyToken();
			String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzZGEzZjUzNDFiNzg4OGMwYmMwYTg0MjY0MTI1Nzc3ZWMwNDlmMGYifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vZHJ0b2RvbGl0dGxlIiwibmFtZSI6IkRpcmsgR8O8bnRoZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1xQW51ZWVqQ3RuVS9BQUFBQUFBQUFBSS9BQUFBQUFBQVBDVS9pRGVSNDZCekxycy9zOTYtYy9waG90by5qcGciLCJhdWQiOiJkcnRvZG9saXR0bGUiLCJhdXRoX3RpbWUiOjE0NzA1MTk1MTYsInVzZXJfaWQiOiI2c3M5Q2liWWk5UjVCTExmNWpzT0NmNWlVUFYyIiwic3ViIjoiNnNzOUNpYllpOVI1QkxMZjVqc09DZjVpVVBWMiIsImlhdCI6MTQ3MDUxOTUxNiwiZXhwIjoxNDcwNTIzMTE2LCJlbWFpbCI6ImRpcmNoa2luZ0Bnb29nbGVtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA4NDkxMjQyNjU0NTQ4ODA4MTE0Il0sImVtYWlsIjpbImRpcmNoa2luZ0Bnb29nbGVtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.FA8jQ_b_4KGTa8GbBbBgTxEii0PkbTRhbkBHhDMoPesfBhOhgRt8JblPCeo27HAIKOZD55gfKPGZmqbEyi5uB3aHHF_AvydnyRq1O1RzlSw3MGj5hphhVCxFm9nDPWr_Caue7T2HKvZszeG4T2JfchCBwVBm6ayh60bMaqabYlInQeQAr4NAA92jWRLLwmAe2q5yWCC7usN-18Ta1emZEG0llVxEpAGyKkUehxC_yt3l6F3tD3DEFXIidQbx53K3aBg6IIMn5--q7tkxpw6KOjFVnvUh73k8Nfp9-gbxsDRrj_bw9Ftku7tY3SbtsNnmmyd83KmK3xIauM7ml7S9Eg";
			verifyToken.verify(token);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void verify(String token) {
		FirebaseAuth.getInstance().verifyIdToken(token)
	    .addOnSuccessListener(new OnSuccessListener<Object>() {

			public void onSuccess(Object result) {
				FirebaseToken token = (FirebaseToken) result;
				System.out.println("Email: " + token.getEmail()); 
			}
	}).addOnFailureListener(new OnFailureListener() {
		
		public void onFailure(Exception e) {
			System.out.println(e.toString());
			
		}
	});
	}

}
