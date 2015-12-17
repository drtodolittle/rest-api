import groovy.json.JsonSlurper

//def input = request.getBody()
def input = new File("../../../../sampledata/getProcessInstances.json").getText()
def jsonSlurper = new JsonSlurper()
def tasks = jsonSlurper.parseText(input)
def processIds = []

tasks.each({ 
	processIds.add(it.id)
println it.id })

processIds
