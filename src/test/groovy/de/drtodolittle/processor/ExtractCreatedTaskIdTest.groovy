package de.drtodolittle.processor

import groovy.json.JsonSlurper
import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ExtractCreatedTaskIdTest {

    @Test
    void extractTaskId() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractCreatedTaskId()
		exchange.in.body = new ByteArrayInputStream('''
		{
		  "links" : [ {
			"method" : "GET",
			"href" : "http://localhost:10080/engine-rest/process-instance/7f46a8e1-08c7-11e6-8838-51881ae629a3",
			"rel" : "self"
		  } ],
		  "id" : "anId",
		  "definitionId" : "DrToDoLittle:3:372690b4-cde4-11e5-ac3d-bfada6080fb0",
		  "businessKey" : "dirk",
		  "caseInstanceId" : null,
		  "ended" : false,
		  "suspended" : false
		}
		'''.getBytes())
		processor.process(exchange)
        def jsonSlurper = new JsonSlurper()
		def todo = jsonSlurper.parseText(exchange.in.body)
		assertEquals("anId", todo.id)
	}
	
}