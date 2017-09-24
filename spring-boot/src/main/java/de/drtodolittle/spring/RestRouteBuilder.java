package de.drtodolittle.spring;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration("servlet").apiContextPath("/api")
			.enableCORS(true)
			.corsHeaderProperty("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		
	}

	

}