/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.processor

import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.apache.camel.Exchange
import org.apache.camel.processor.ErrorHandler

/**
 *
 * @author Guenther_D
 */
class DefaultErrorHandler implements ErrorHandler {
	
     public void process(Exchange exchange) {
		exchange.setProperty(Exchange.ROUTE_STOP, true)
		exchange.in.setHeader(Exchange.HTTP_RESPONSE_CODE, 500)
		exchange.in.body = "Internal server error"
	 }
}

