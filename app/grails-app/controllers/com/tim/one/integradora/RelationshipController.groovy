package com.tim.one.integradora

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

class RelationshipController {

  static allowedMethods = [index:'GET', show:'GET', save:'POST']

  def relationshipService

  def index() {
    def relationships = relationshipService.obtainRelationshipsForIntegrated(params.long("userId"))

    render(contentType:'application/json', status:OK) {
      relationships
    }
  }

  def save(RelationshipCommand command) {
    if(command.hasErrors()) {
      respond command.errors
      return
    }

    def result = relationshipService.createRelatioshipForIntegatedAndPartner(params.userId, command)

    render(contentType:'application/json', status:CREATED) {
      result
    }
  }

  def show() {
    def relationship = relationshipService.obtainRelationshipForIntegratedWithId(params.long("userId"), params.long('id'))

    render(contentType:'application/json', status:OK) {
      relationship
    }
  }

  @Transactional
  def delete(Long id) {
    User user = User.findByIdAndEnabled(params.userId, true)
    if (!user){
      render(status:404)
      return
    }
    Relationship relationship = Relationship.get(id)
    relationship.enabled = false
    relationship.save()

    render(status:202)
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
