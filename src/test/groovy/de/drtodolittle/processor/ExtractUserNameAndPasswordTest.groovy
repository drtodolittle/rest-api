package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ExtractUserNameAndPasswordTest {

    @Test
    void extractUserNameAndPassword() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractUserNameAndPassword()
		exchange.in.body = new ByteArrayInputStream('{"email": "user1", "password": "password1"}'.getBytes()) 
		processor.process(exchange)
		assertEquals("user1", exchange.in.headers.username)
		assertEquals("username=user1&password=password1", (String)exchange.in.body)
	}

    @Test
    void testWithEmptyBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = ""
        def processor = new ExtractUserNameAndPassword()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithNoBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractUserNameAndPassword()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}