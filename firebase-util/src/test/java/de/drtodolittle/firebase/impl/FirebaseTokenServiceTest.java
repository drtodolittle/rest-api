/**
 * 
 */
package de.drtodolittle.firebase.impl;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * @author Guenther_D
 *
 */
public class FirebaseTokenServiceTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws FileNotFoundException {
//		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setServiceAccount(new FileInputStream("DrToDoLittle-10b9f17b6eb3.json"))
//				.setDatabaseUrl("https://drtodolittle.firebaseio.com/").build();
//		FirebaseApp.initializeApp(options);
//		FirebaseTokenService tokenService = new FirebaseTokenService();
//		String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjU4N2YxMmMxYWQyNzJmY2Q4NTc1NDE4ZjQxNjAxODhhNWNkYmQ0NmYifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vZHJ0b2RvbGl0dGxlIiwiYXVkIjoiZHJ0b2RvbGl0dGxlIiwiYXV0aF90aW1lIjoxNDcxMTcwNjM2LCJ1c2VyX2lkIjoiSXkyeWhNNkdQY2FlM3RrZDBwek9qMGxvQWZEMyIsInN1YiI6Ikl5MnloTTZHUGNhZTN0a2QwcHpPajBsb0FmRDMiLCJpYXQiOjE0NzExNzA2MzcsImV4cCI6MTQ3MTE3NDIzNywiZW1haWwiOiJkLmd1ZW50aGVyQGNlbml0LmRlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbImQuZ3VlbnRoZXJAY2VuaXQuZGUiLCJkLmd1ZW50aGVyQGNlbml0LmRlIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.YGemx8UeJBOsiud7QFCtm4pHAANY476bR28sSTstwRyBlCCFN_EkghKob42yvZEPP9hpo_4nG8MQ5fhWcl_Sy4p9Ne2Xs5VCm4X0f0IeSJTkiz0uvOyLQquYZI_oyy9Vft8rNF0GEexWpxbm6UiHGig5rgK5qoh5x66BWb3g5qeN9QdIhGEsfrxJWdu61i5LVvXg2WHDOXS9NRY7Lm5DVMW5XKzLzGjuh7SHDFwCct2xi7unVlKFXZkUFVn7bewbcba_hN0eCBcpfIrjszxtxjbmD_W5QfxvlRJ5v-CNfe5YFDYSCkuBE4MDKXjy5qZmbaB6liWlerC2IZFSPM2DQg";
//		String email = tokenService.verify(token);
//		assertEquals("Email must be d.guenther@cenit.de", "d.guenther@cenit.de", email);
	}

}
