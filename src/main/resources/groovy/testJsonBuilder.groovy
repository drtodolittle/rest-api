

def builder = new groovy.json.JsonBuilder()
       def root = builder {
           variables {
               topic {
						value 'Mein erster automatisch erstellter'
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
println builder.toString()

