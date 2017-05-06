package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


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
		assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicHJvY2Vzc0lkIjoiMTIzNCIsImZpcnN0bmFtZSI6ImZpcnN0dGVzdCIsImxhc3RuYW1lIjoibGFzdHRlc3QifQ.izoHafVk9lONcigE3SbGNXkveC6Xj2uJkOaZ8TmgtS-a3db5L2usLOoo3wm4s_PUKR_x0rlgEscMHs9V0vwG6A", exchange.in.getHeader("token"))
	}

    @Test
    void testWithoutEmail() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = "1234;firsttest;lasttest"
        def processor = new ExtractEmailAndProcessId()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}