package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ExtractUserFromTokenTest {

    @Test
    void extractUserFromToken() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.headers.Authorization = "bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicHJvY2Vzc0lkIjoiMTIzNCIsImZpcnN0bmFtZSI6ImZpcnN0dGVzdCIsImxhc3RuYW1lIjoibGFzdHRlc3QifQ.izoHafVk9lONcigE3SbGNXkveC6Xj2uJkOaZ8TmgtS-a3db5L2usLOoo3wm4s_PUKR_x0rlgEscMHs9V0vwG6A"
        def processor = new ExtractUserFromToken()
		processor.process(exchange)
		assertEquals "test@test.com", exchange.in.headers.username
	}

    @Test
    void testWithoutAuthorizationHeader() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractUserFromToken()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithoutAuthorizationToken() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.headers.Authorization = "bearer "
        def processor = new ExtractUserFromToken()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithWrongToken() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.headers.Authorization = "bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicHJvY2Vzc0lkIjoiMTIzNCIsImZpcnN0bmFtZSI6ImZpcnN0dGVzdCIsImxhc4RuYW1lIjoibGFzdHRlc3QifQ.izoHafVk9lONcigE3SbGNXkveC6Xj2uJkOaZ8TmgtS-a3db5L2usLOoo3wm4s_PUKR_x0rlgEscMHs9V0vwG6A"
        def processor = new ExtractUserFromToken()
		shouldFail io.jsonwebtoken.SignatureException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithCorruptToken() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.headers.Authorization = "bearer eyJhbGciOzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicHJvY2Vzc0lkIjoiMTIzNCIsImZpcnN0bmFtZSI6ImZpcnN0dGVzdCIsImxhc4RuYW1lIjoibGFzdHRlc3QifQ.izoHafVk9lONcigE3SbGNXkveC6Xj2uJkOaZ8TmgtS-a3db5L2usLOoo3wm4s_PUKR_x0rlgEscMHs9V0vwG6A"
        def processor = new ExtractUserFromToken()
		shouldFail io.jsonwebtoken.MalformedJwtException, {
			processor.process(exchange)
		}
	}
	
}