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
		[{
      "id":"1",
      "name":"Execute Todo",
      "assignee":"test1",
      "created":"2016-09-16T18:22:23",
      "due":null,
      "followUp":null,
      "delegationState":null,
      "description":null,
      "executionId":"executionID1",
      "owner":null,
      "parentTaskId":null,
      "priority":50,
      "processDefinitionId":"processDefinitionId1",
      "processInstanceId":"anId1",
      "taskDefinitionKey":"UserTask",
      "caseExecutionId":null,
      "caseInstanceId":null,
      "caseDefinitionId":null,
      "suspended":false,
      "formKey":null
     },
     {
      "id":"2",
      "name":"Execute Todo",
      "assignee":"test2",
      "created":"2016-09-16T18:18:42",
      "due":null,
      "followUp":null,
      "delegationState":null,
      "description":null,
      "executionId":"executionId2",
      "owner":null,
      "parentTaskId":null,
      "priority":50,
      "processDefinitionId":"processDefinitionId1",
      "processInstanceId":"anId2",
      "taskDefinitionKey":"UserTask",
      "caseExecutionId":null,
      "caseInstanceId":null,
      "caseDefinitionId":null,
      "suspended":false,
      "formKey":null
     }
		 ]
		'''.getBytes())
        def processor = new ExtractProcessInstanceIds()
		processor.process(exchange)
		assertEquals(["anId1", "anId2"], exchange.in.headers.processIds)
	}

}
