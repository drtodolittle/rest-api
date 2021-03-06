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
class GenerateCreateProcessJSON implements Processor{

    public void process(Exchange exchange) {
		def input = exchange.in.body
		def username = exchange.in.headers.username
		if (username == null || username.length() == 0) {
			throw new IllegalArgumentException("Username should not be null")
		}
		def jsonSlurper = new JsonSlurper()
		def vars = jsonSlurper.parse(input)

		def builder = new groovy.json.JsonBuilder()
		builder {
			variables {
				topic {
					value vars.topic
					type 'String'
				}
				done {
					value false
					type 'Boolean'
				}
				delete {
					value false
					type 'Boolean'
				}
				startedby {
					value username
					type 'String'
				}
			}
			businessKey username
		}

        exchange.in.body = builder.toString()
     }






}
