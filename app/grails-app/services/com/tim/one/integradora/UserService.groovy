package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class UserService {

  def addRelationshipToIntegrated(integratedId, params) {
    def integrated = User.get(integratedId)
    def provider = findOrCreateProviderWithProfile(params)

    def relationship = new Relationship(type:params.type)
    relationship.addToUsers(integrated)
    provider.addToRelationships(relationship)
    provider.save()

    integrated
  }

  def findOrCreateProviderWithProfile(params) {
    def provider = User.findOrCreateByEmail(params.email)
    provider.properties = params

    if(!provider.profile) {
      def profile = new Profile()
      profile.properties = params
      profile.save()

      provider.profile = profile
    }

    provider
  }
}
