/**
 * 
 */
package de.drtodolittle.firebase.impl;

import java.io.FileInputStream;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import de.drtodolittle.firebase.api.TokenService;

/**
 * @author Guenther_D
 *
 */
public class Activator implements BundleActivator {

	private ServiceRegistration<TokenService> service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setServiceAccount(new FileInputStream("DrToDoLittle-10b9f17b6eb3.json"))
					.setDatabaseUrl("https://drtodolittle.firebaseio.com/").build();
			FirebaseApp.initializeApp(options);
		}
		catch (IllegalStateException e) {
		}
		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put(Constants.SERVICE_PID, FirebaseTokenService.PID);
		service = context.registerService(TokenService.class, new FirebaseTokenService(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		service.unregister();
	}

}
