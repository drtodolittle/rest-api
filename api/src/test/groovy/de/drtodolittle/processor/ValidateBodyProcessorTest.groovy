package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ValidateBodyProcessorTest {

    @Test
    void processWithBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		exchange.in.body="test"
        def processor = new ValidateBodyProcessor(new Processor() {
			public void process(Exchange exchange1) {
				exchange1.in.headers.process = "ok"
			}	
		});
		processor.process(exchange)
		assertEquals "ok", exchange.in.headers.process
	}
	
	
    @Test
    void processWithEmptyBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		exchange.in.body=""
        def processor = new ValidateBodyProcessor(new Processor() {
			public void process(Exchange exchange1) {
			}	
		});
		shouldFail IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void processWithNoBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
        def processor = new ValidateBodyProcessor(new Processor() {
			public void process(Exchange exchange1) {
			}	
		});
		shouldFail IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}