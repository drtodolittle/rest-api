/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.processor

import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.apache.camel.Exchange
import org.apache.camel.Processor

/**
 *
 * @author Guenther_D
 */
class GenerateToDoJSON implements Processor {
	
     public void process(Exchange exchange) {
        def input = exchange.in.body
        def jsonSlurper = new JsonSlurper()
        def vars = jsonSlurper.parse(input)
        
        def todo = [:]
        
        todo.put("id", exchange.in.headers.processIds[exchange.properties.CamelSplitIndex])
        
        vars.each({
                if (it.name.equals("topic")) {
                    todo.put("topic", new String(it.value))
                }
                else if (it.name.equals("done")) {
                    todo.put("done", new Boolean(it.value))
                }
            })
        exchange.in.body = JsonOutput.toJson(todo)
     }
}

