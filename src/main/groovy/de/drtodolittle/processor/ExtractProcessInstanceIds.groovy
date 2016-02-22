/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.processor

import groovy.json.JsonSlurper
import org.apache.camel.Exchange
import org.apache.camel.Processor

/**
 *
 * @author Guenther_D
 */
class ExtractProcessInstanceIds implements Processor {
	
     public void process(Exchange exchange) {
		if (!exchange.in.body) {
			throw new IllegalArgumentException("Body should not be null")
		}
        def input = exchange.getIn().getBody()
        def jsonSlurper = new JsonSlurper()
        def tasks = jsonSlurper.parse(input)
        def processIds = []

        tasks.each({processIds.add(it.id)})

        exchange.getIn().setHeader("processIds", processIds)
         
     }
}
