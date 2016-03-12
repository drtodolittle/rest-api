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
class ValidateBodyProcessor implements Processor {
	
	Processor processor;
	
	public ValidateBodyProcessor(Processor processor) {
		if (processor == null) {
			throw new IllegalArgumentException("Processor should not be null")
		}
		this.processor = processor
	}
	
	public void process(Exchange exchange) {
		if (!exchange.in.body) {
			throw new IllegalArgumentException("Body should not be null")
		}
		processor.process(exchange)
	}
}

