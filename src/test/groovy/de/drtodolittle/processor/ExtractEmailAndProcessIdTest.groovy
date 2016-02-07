package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*


class ExtractEmailAndProcessIdTest {

    @Test
    void extractEmailAndProcessId() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = "test@test.com;1234;firsttest;lasttest"
        def processor = new ExtractEmailAndProcessId()
		processor.process(exchange)
		assertEquals("test@test.com", exchange.in.getHeader("email"))
		assertEquals("1234", exchange.in.getHeader("processId"))
		assertEquals("firsttest", exchange.in.getHeader("firstname"))
		assertEquals("lasttest", exchange.in.getHeader("lastname"))
		
		println("Token: " + exchange.in.getHeader("token"))
	}
}