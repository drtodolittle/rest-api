package de.drtodolittle.processor

import groovy.json.JsonSlurper
import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class MappingPasswordJSONTest {

    @Test
    void mappingPassword() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new MappingPasswordJSON()
		exchange.in.body = new ByteArrayInputStream('''
		{
		  "newPassword" : "password1234",
		  "oldPassword" : "passwordold"
		}
		'''.getBytes())
		processor.process(exchange)
        def jsonSlurper = new JsonSlurper()
		def passwords = jsonSlurper.parseText(exchange.in.body)
		assertEquals("password1234", passwords.password)
		assertEquals("passwordold", passwords.authenticatedUserPassword)
	}
	
}