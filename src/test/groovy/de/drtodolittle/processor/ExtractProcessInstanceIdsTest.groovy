package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ExtractProcessInstanceIdsTest {

    @Test
    void extractProcessInstanceIds() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = new ByteArrayInputStream('''
		[{"links":[],
		 "id":"anId1",
		 "definitionId":"aProcessDefinitionId",
		 "businessKey":"aKey",
		 "caseInstanceId":"aCaseInstanceId",
		 "ended":false,
		 "suspended":false},
		{"links":[],
		 "id":"anId2",
		 "definitionId":"aProcessDefinitionId",
		 "businessKey":"aKey",
		 "caseInstanceId":"aCaseInstanceId",
		 "ended":false,
		 "suspended":false}		 
		 ]
		'''.getBytes())
        def processor = new ExtractProcessInstanceIds()
		processor.process(exchange)
		assertEquals(["anId1", "anId2"], exchange.in.headers.processIds)
	}

    @Test
    void testWithEmptyBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = ""
        def processor = new ValidateBodyProcessor(new ExtractProcessInstanceIds())
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithNoUser() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ValidateBodyProcessor(new ExtractProcessInstanceIds())
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}