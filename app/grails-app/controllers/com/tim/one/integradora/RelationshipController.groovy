package com.tim.one.integradora

import static org.springframework.http.HttpStatus.*

class RelationshipController {

  def index() {
    log.debug "Relationships"
    render status:OK
  }

}
