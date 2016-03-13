package de.drtodolittle.processor

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class GenerateCreateProcessUserRegistryJSONTest {

    @Test
    void generateCreateProcessUserRegistryJSON() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		
		def builder = new groovy.json.JsonBuilder()
		builder {
			email "test@test.com"
			firstname "firstname1"
			lastname "lastname2"
			password "password1"
		}

        exchange.in.body = builder.toString().getBytes()
		exchange.in.headers.username = "user1"
        def processor = new GenerateCreateProcessUserRegistryJSON()
		processor.process(exchange)
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(exchange.in.body)
		assertEquals "test@test.com", result.variables.email.value
		assertEquals "firstname1", result.variables.firstname.value
		assertEquals "lastname2", result.variables.lastname.value
		assertEquals "password1", result.variables.password.value
		assertEquals "user1", result.businessKey	
	}

    @Test
    void generateCreateProcessUserRegistryJSONWithWrongJSON() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		
		def builder = new groovy.json.JsonBuilder()
		builder {
			test {
				email "test@test.com"
				firstname "firstname1"
				lastname "lastname2"
				password "password1"
			}
		}

        exchange.in.body = builder.toString().getBytes()
		exchange.in.headers.username = "user1"
        def processor = new GenerateCreateProcessUserRegistryJSON()
		shouldFail IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}