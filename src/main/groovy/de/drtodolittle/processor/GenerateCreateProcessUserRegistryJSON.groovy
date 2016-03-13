/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.processor

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.apache.camel.Exchange
import org.apache.camel.Processor

/**
 *
 * @author Guenther_D
 */
class GenerateCreateProcessUserRegistryJSON implements Processor{

    public void process(Exchange exchange) {
		def input = exchange.in.body
		def username = exchange.in.headers.username
		def jsonSlurper = new JsonSlurper()
		def vars = jsonSlurper.parse(input)

		if (vars.email == null || vars.firstname == null || vars.lastname == null || vars.password == null || username == null) {
			throw new IllegalArgumentException("One of the parameters are null")
		}
		
        def builder = new groovy.json.JsonBuilder()
        builder {
            variables {
                email {
                    value vars.email
                    type 'String'
				}
				firstname {
                    value vars.firstname
                    type 'String'
				}
				lastname {
                    value vars.lastname
                    type 'String'
				}
				password {
                    value vars.password
                    type 'String'
				}
            }
            businessKey username
		}
        exchange.in.body = builder.toString()
	}
}
