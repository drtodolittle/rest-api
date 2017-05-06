package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class CreateJsonWebTokenTest {

    @Test
    void createJsonWebToken() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.headers.username = "test@test.com"
        def processor = new CreateJsonWebToken()
		processor.process(exchange)
		assertEquals('{"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.isHgo0yw4yGpAkoqpPU_TLT5iKesQuR8799IANBGpl9j9TVGNGg61aFGLKAUFV1ifT4v-OPq0EDlTSqmfFXxaw"}', exchange.in.body)
	}

    @Test
    void testWithEmptyUser() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.headers.username = ""
        def processor = new CreateJsonWebToken()
		shouldFail java.lang.IllegalStateException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithNoUser() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new CreateJsonWebToken()
		shouldFail java.lang.IllegalStateException, {
			processor.process(exchange)
		}
	}
	
}