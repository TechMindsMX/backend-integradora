package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(RelationshipService)
@Mock([User, Relationship, Profile])
class RelationshipServiceSpec extends Specification {

  def "Adding a relation to an existing user"() {
    given: "An existing user"
      def integrated = new User(
        name: "name",
        email: "email@user.com",
        rfc: "123456789012",
      )
      integrated.save()

    and: "given params"
      def params = [
        name: providerName,
        email: providerEmail,
        rfc: providerRfc,
        tradeName: providerTradeName,
        corporateName: providerCorporateName,
        phone: providerPhone,
        enabled: false,
        type: relationshipType
      ]

    when:
      def relationship = service.createRelatioshipForIntegatedAndPartner(integrated.id, params)

    then:
      relationship.id
      relationship.type == relationshipType
      relationship.users.size() == 2
      relationship.users*.enabled == [true, false]
      relationship.users.find { !it.enabled }.profile.id
      relationship.users.find { !it.enabled }.profile.tradeName == providerTradeName

    where:
      providerName  | providerEmail        | providerRfc | providerTradeName | providerCorporateName | providerPhone | relationshipType
      "provider"    | "email@provider.com" | "1" *12     | "tradeName"       | "corporateName"       | "1" * 10      | RelationshipType.PROVIDER
      "client"      | "email@client.com"   | "1" *12     | "tradeName"       | "corporateName"       | "1" * 10      | RelationshipType.CLIENT
  }

  def "Adding a relation between an integrated and a disabled user"() {
    given: "An existing user"
      def integrated = new User(
        name: "name",
        email: "email@user.com",
        rfc: "123456789012"
      )
      integrated.save()

    and: "Client/provider"
      def profile = new Profile(
        tradeName: providerTradeName,
        corporateName: providerCorporateName,
        phone: providerPhone,
      )
      profile.save()
      def provider = new User(
        name: providerName,
        email: providerEmail,
        rfc: providerRfc,
        enabled: false,
        profile:profile,
        type: relationshipType
      )
      provider.save()

    and: "given params"
      def params = [
        email: providerEmail,
        type: relationshipType
      ]

    when:
      def relationship = service.createRelatioshipForIntegatedAndPartner(integrated.id, params)

    then:
      relationship.id
      relationship.type == relationshipType
      relationship.users.size() == 2
      relationship.users*.enabled == [true, false]

      provider.relationships.size() == 1
      provider.relationships[0].id == relationship.id
      provider.profile == profile

    where:
      providerName  | providerEmail        | providerRfc | providerTradeName | providerCorporateName | providerPhone | relationshipType
      "provider"    | "email@provider.com" | "1" *12     | "tradeName"       | "corporateName"       | "1" * 10      | RelationshipType.PROVIDER
      "client"      | "email@client.com"   | "1" *12     | "tradeName"       | "corporateName"       | "1" * 10      | RelationshipType.CLIENT
  }

  def "Adding a new relationship to a user with other provider"() {
    given: "An existing user"
      def integrated = new User(
        name: "name",
        email: "email@user.com",
        rfc: "123456789012",
        tradeName: "tradeName",
        corporateName: "corporateName",
        phone: "1234567890",
      )
      integrated.save()

    and: "an existing client/provider disabled"
      def provider = new User(
        name: "oldprovider",
        email: "oldprovider@mail.com",
        enabled:false
      )

    and: "with his relationships"
      def relationship = new Relationship(type:RelationshipType.PROVIDER)
      relationship.addToUsers(integrated)
      provider.addToRelationships(relationship)
      provider.save()

    and: "given params"
      def params = [
        name: providerName,
        email: providerEmail,
        rfc: providerRfc,
        tradeName: providerTradeName,
        corporateName: providerCorporateName,
        phone: providerPhone,
        enabled: false,
        type: relationshipType
      ]

    when:
      def newRelationship = service.createRelatioshipForIntegatedAndPartner(integrated.id, params)

    then:
      newRelationship.id
      newRelationship.users.size() == 2

      provider.relationships.size() == 1

    where:
      providerName  | providerEmail        | providerRfc | providerTradeName | providerCorporateName | providerPhone | relationshipType
      "provider"    | "email@provider.com" | "1" *12     | "tradeName"       | "corporateName"       | "1" * 10      | RelationshipType.PROVIDER
      "client"      | "email@client.com"   | "1" *12     | "tradeName"       | "corporateName"       | "1" * 10      | RelationshipType.CLIENT
  }
}
