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

    user.relationships.each { relationship ->
      def data = [:]
      data.id = relationship.id
      data.type = relationship.type.toString()

      def partner = relationship.users.find { it.id != integratedId }
      data.rfc = partner.rfc
      data.email = partner.email
      data.enabled = partner.enabled
      data.name = partner.name
      data.tradeName = partner.profile.tradeName
      data.corporateName = partner.profile.corporateName
      data.phone = partner.profile.phone

      relationships << data
    }

    relationships
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
