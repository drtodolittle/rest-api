/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.drtodolittle.aggregate

import org.apache.camel.Exchange
import org.apache.camel.processor.aggregate.AggregationStrategy


/**
 *
 * @author Guenther_D
 */
class ProcessInstanceVariables implements AggregationStrategy {
	
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            newExchange.in.body = newExchange.in.body.getText()
            return newExchange
        }
        oldExchange.in.body = oldExchange.in.body + newExchange.in.body.getText()
        return oldExchange
    }
}

