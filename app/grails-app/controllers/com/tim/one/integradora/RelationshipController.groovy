package com.tim.one.integradora

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

class RelationshipController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def relationshipService

  def index() {
    log.debug "Relationships"
    render status:OK
  }

  @Transactional
  def save(RelationshipCommand command) {
    if(command.hasErrors()) {
      respond command.errors
      return
    }

    def relationship = relationshipService.createRelatioshipForIntegatedAndPartner(params.userId, command)

    render(contentType:'application/json', status:CREATED) {
      relationship
    }
  }

}

class RelationshipCommand {
  String name
  String email
  String rfc
  String tradeName
  String corporateName
  String phone

  boolean enabled = false
  RelationshipType type

  static constraints = {
    name blank:false,size:1..255
    email email:true,unique:true,size:1..100
    rfc blank:false, size:12..13

    tradeName blank:false, size:1..255
    corporateName nullable:true, blank:true, size:1..255
    phone size:10..10
  }
}
