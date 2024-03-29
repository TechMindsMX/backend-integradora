package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class RelationshipService {

  def createRelatioshipForIntegatedAndPartner(integratedId, params) {
    def integrated = User.get(integratedId)
    def partner = findOrCreatePartnerWithProfile(params)

    def relationship = new Relationship(type:params.type)
    relationship.addToUsers(integrated)

    partner.addToRelationships(relationship)
    partner.save()

    def result = [ integrated: integrated, partner: partner, relationship: relationship ]
    result
  }

  def obtainRelationshipsForIntegrated(integratedId) {
    def user = User.findByIdAndEnabled(integratedId, true)
    def relationships = []

    user?.relationships?.each { relationship ->
      def data = relationshipResponseFormatter(relationship, integratedId)
      relationships << data
    }

    relationships
  }

  def obtainRelationshipForIntegratedWithId(integratedId, relationId) {
    def relationship = Relationship.get(relationId)
    def belongsToIntegrated = relationship.users.find { it.id == integratedId }

    if(!belongsToIntegrated) {
      return
    }

    def data = relationshipResponseFormatter(relationship, integratedId)
    data
  }

  private def relationshipResponseFormatter(relationship, integratedId) {
    def partner = relationship.users.find { it.id != integratedId }

    def data = [:]
    data.id = relationship.id
    data.type = relationship.type.toString()
    data.enabled = relationship.enabled

    data.rfc = partner.rfc
    data.email = partner.email
    data.name = partner.name
    data.tradeName = partner.profile.tradeName
    data.corporateName = partner.profile.corporateName
    data.phone = partner.profile.phone

    data
  }

  private def findOrCreatePartnerWithProfile(params) {
    def partner = User.findOrCreateByEmail(params.email)
    partner.properties = params

    if(!partner.profile) {
      def profile = new Profile()
      profile.properties = params
      profile.save()

      partner.profile = profile
    }

    partner
  }
}
