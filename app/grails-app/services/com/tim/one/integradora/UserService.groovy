package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class UserService {

  def addRelationshipToIntegrated(integratedId, params) {
    def integrated = User.get(integratedId)

    def provider = User.findOrCreateByEmail(params.email)
    provider.properties = params

    def relationship = new Relationship(type:params.relationshipType)
    relationship.addToUsers(integrated)
    provider.addToRelationships(relationship)
    provider.save()

    integrated
  }
}
