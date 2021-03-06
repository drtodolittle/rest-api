/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.processor

import groovy.json.JsonSlurper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import groovy.json.JsonOutput

/**
 *
 * @author Guenther_D
 */
class ExtractCreatedTaskId implements Processor {
	
     public void process(Exchange exchange) {
        def input = exchange.in.body
        def jsonSlurper = new JsonSlurper()
        def task = jsonSlurper.parse(input)
		def todo = [:]
		todo.put("id", task.id)
		exchange.in.body = JsonOutput.toJson(todo)         
     }
}

