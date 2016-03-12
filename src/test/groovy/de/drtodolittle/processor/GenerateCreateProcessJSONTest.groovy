package de.drtodolittle.processor

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class GenerateCreateProcessJSONTest {

    @Test
    void generateCreateProcessJSON() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		
		def builder = new groovy.json.JsonBuilder()
		builder {
			topic "test1"
		}

        exchange.in.body = builder.toString().getBytes()
		exchange.in.headers.username = "user1"
        def processor = new GenerateCreateProcessJSON()
		processor.process(exchange)
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(exchange.in.body)
		assertEquals "test1", result.variables.topic.value
		assertEquals "user1", result.variables.startedby.value
		assertEquals "user1", result.businessKey	
	}

    @Test
    void generateCreateProcessJSONWithoutUsername() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		
		def builder = new groovy.json.JsonBuilder()
		builder {
			topic "test1"
		}

        exchange.in.body = builder.toString().getBytes()
        def processor = new GenerateCreateProcessJSON()
		shouldFail IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void generateCreateProcessJSONWithEmptyUsername() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		exchange.in.headers.username = ""
		def builder = new groovy.json.JsonBuilder()
		builder {
			topic "test1"
		}

        exchange.in.body = builder.toString().getBytes()
        def processor = new GenerateCreateProcessJSON()
		shouldFail IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}