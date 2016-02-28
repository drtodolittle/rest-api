package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ExtractTopicTest {

    @Test
    void extractTopic() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractTopic()
		exchange.in.body = new ByteArrayInputStream('{ "topic":"test1"}'.getBytes()) 
		processor.process(exchange)
		assertEquals('{"value" : "test1", "type": "String"}', exchange.in.headers.updatetopic)
	}

    @Test
    void testWithEmptyBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = ""
        def processor = new ExtractTopic()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithNoBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractTopic()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}