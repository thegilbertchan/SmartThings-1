/**
Virtual Switch to control thermostat
*
*
*/

// Automatically generated. Make future change here.
definition(
    name: "Thermostat Master Button",
    namespace: "",
    author: "Kevin Tierney",
    description: "Master ON/OFF Button for Thermostat",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience%402x.png"
)

preferences {
section("Select Virtual Switch"){
input "virtSwitch", "capability.switch"
}

section("Which thermostat should I control") {
		input "thermostat", "capability.thermostat"
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
    	thermostat.on()

}

def offHandler(evt) {
	log.debug "Received off from ${theSwitch}"
    	thermostat.off()
}

def initialize() {
	subscribe(virtSwitch, "switch.On", onHandler)
    subscribe(virtSwitch, "switch.Off", offHandler)
}
