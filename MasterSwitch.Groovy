/**
*One Button to rule them all
*Turns selected lights on/off 
*Listens to Virtual Switch
*
*
*/
preferences {
section("Select Virtual Switch"){
input "virtSwitch", "capability.switch"
}

section("Which switches should I control") {
		input "switches", "capability.switch", multiple: true
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
    initialize()
}

def updated(settings) {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
    initialize()
}


def onHandler(evt) {
	log.debug "Received on from ${virtSwitch}"
    switches?.on()

}

def offHandler(evt) {
	log.debug "Received off from ${theSwitch}"
    switches?.off()
}

def initialize() {
	subscribe(virtSwitch, "switch.On", onHandler)
    subscribe(virtSwitch, "switch.Off", offHandler)
}
