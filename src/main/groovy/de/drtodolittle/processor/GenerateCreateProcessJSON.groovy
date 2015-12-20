/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.processor

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.apache.camel.Exchange
import org.apache.camel.Processor

/**
 *
 * @author Guenther_D
 */
class GenerateCreateProcessJSON implements Processor{

    
    
    public void process(Exchange exchange) {
        def input = "test1"
        def builder = new groovy.json.JsonBuilder()
        builder {
            variables {
                topic {
                    value input
                    type 'String'
		}
		done {
                    value false
                    type 'Boolean'
		}
		startedby {
                    value 'dirk'
                    type 'String'
		}
            }
            businessKey 'dirk'
	}

        exchange.in.body = builder.toString()
     }
    
    
    
    
    
    
}

