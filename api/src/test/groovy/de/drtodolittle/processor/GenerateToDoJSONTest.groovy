package de.drtodolittle.processor

import org.junit.Test
import org.apache.camel.*
import org.apache.camel.impl.*
import static org.junit.Assert.*
import static groovy.test.GroovyAssert.shouldFail


class GenerateToDoJSONTest {

    @Test
    void generateToDoJSON() {
		def ctx = new DefaultCamelContext()
		def exchange = new DefaultExchange(ctx)
		exchange.in.body='''
[
  {
    "id": "someId",
    "name": "topic",
    "type": "String",
    "variableType": "string",
    "value": "test1",
    "processInstanceId": "aProcessInstanceId",
    "executionId": "b68b71c9-e310-11e2-beb0-f0def1557726",
    "taskId": null,
    "activityInstanceId": "Task_1:b68b71ca-e310-11e2-beb0-f0def1557726",
    "caseExecutionId": null,
    "caseInstanceId": null,
    "serializationConfig": null
  },
  {
    "id": "someOtherId",
    "name": "done",
    "type": "Boolean",
    "variableType": "bolean",
    "value": false,
    "processInstanceId": "aProcessInstanceId",
    "executionId": "68b71c9-e310-11e2-beb0-f0def1557726",
    "taskId": null,
    "activityInstanceId": "Task_1:b68b71ca-e310-11e2-beb0-f0def1557726",
    "caseExecutionId": null,
    "caseInstanceId": null,
    "serializationConfig": null
  },
  {
    "id": "someOtherId1",
    "name": "delete",
    "type": "Boolean",
    "variableType": "bolean",
    "value": false,
    "processInstanceId": "aProcessInstanceId",
    "executionId": "68b71c9-e310-11e2-beb0-f0def1557726",
    "taskId": null,
    "activityInstanceId": "Task_1:b68b71ca-e310-11e2-beb0-f0def1557726",
    "caseExecutionId": null,
    "caseInstanceId": null,
    "serializationConfig": null
  },
  {
    "id": "yetAnotherId",
    "name": "amount",
    "type": "Integer",
    "variableType": "integer",
    "value": 150,
    "processInstanceId": "anotherProcessInstanceId",
    "executionId": "68b71c9-e310-11e2-beb0-f0def1557726",
    "taskId": null,
    "activityInstanceId": "Task_2:b68b71ca-e310-11e2-beb0-f0def1557726",
    "caseExecutionId": null,
    "caseInstanceId": null,
    "serializationConfig": null
  }
]
'''.getBytes()
		exchange.in.headers.processInstanceIdIn = "1"
		exchange.properties.CamelSplitIndex = 0
    def processor = new GenerateToDoJSON()
		processor.process(exchange)
		assertEquals '{"id":"1","topic":"test1","done":false}', exchange.in.body
	}

}
