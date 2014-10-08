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

    relationship
  }

  def findOrCreatePartnerWithProfile(params) {
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
