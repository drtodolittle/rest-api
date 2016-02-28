package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class ExtractTaskIdTest {

    @Test
    void extractTaskId() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractTaskId()
		exchange.in.body = new ByteArrayInputStream('''
		[{"id":"anId",
		 "name":"aName",
		 "assignee":"anAssignee",
		 "created":"2013-01-23T13:42:42",
		 "due":"2013-01-23T13:49:42",
		 "followUp:":"2013-01-23T13:44:42",
		 "delegationState":"RESOLVED",
		 "description":"aDescription",
		 "executionId":"anExecution",
		 "owner":"anOwner",
		 "parentTaskId":"aParentId",
		 "priority":42,
		 "processDefinitionId":"aProcDefId",
		 "processInstanceId":"aProcInstId",
		 "caseDefinitionId":"aCaseDefId",
		 "caseInstanceId":"aCaseInstId",
		 "caseExecutionId":"aCaseExecution",
		 "taskDefinitionKey":"aTaskDefinitionKey",
		 "formKey":"aFormKey"},
		{"id":"anId2",
		 "name":"aName",
		 "assignee":"anAssignee",
		 "created":"2013-01-23T13:42:42",
		 "due":"2013-01-23T13:49:42",
		 "followUp:":"2013-01-23T13:44:42",
		 "delegationState":"RESOLVED",
		 "description":"aDescription",
		 "executionId":"anExecution",
		 "owner":"anOwner",
		 "parentTaskId":"aParentId",
		 "priority":42,
		 "processDefinitionId":"aProcDefId",
		 "processInstanceId":"aProcInstId",
		 "caseDefinitionId":"aCaseDefId",
		 "caseInstanceId":"aCaseInstId",
		 "caseExecutionId":"aCaseExecution",
		 "taskDefinitionKey":"aTaskDefinitionKey",
		 "formKey":"aFormKey"}		 
		 ]
		'''.getBytes())
		processor.process(exchange)
		assertEquals("anId", exchange.in.headers.taskid)
	}

    @Test
    void testWithEmptyBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
		exchange.in.body = ""
        def processor = new ExtractTaskId()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}

    @Test
    void testWithNoBody() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)	
        def processor = new ExtractTaskId()
		shouldFail java.lang.IllegalArgumentException, {
			processor.process(exchange)
		}
	}
	
}