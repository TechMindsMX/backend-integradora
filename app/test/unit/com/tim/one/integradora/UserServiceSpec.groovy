package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserService)
@Mock([User, Relationship])
class UserServiceSpec extends Specification {

  def "Adding a relation to an existing user"() {
    given: "An existing user"
      def integrated = new User(
        name: "name",
        email: "email@user.com"
      )
      integrated.save()

    and: "given params"
      def params = [
        name: providerName,
        email: providerEmail,
        enabled: false,
        relationshipType: relationshipType
      ]

    when:
      def user = service.addRelationshipToIntegrated(integrated.id, params)

    then:
      user.id
      user.relationships.size() == 1
      user.relationships[0].type == relationshipType
      user.relationships[0].users.size() == 2
      user.relationships[0].users*.enabled == [true, false]

    where:
      providerName  | providerEmail        | relationshipType
      "provider"    | "email@provider.com" | RelationshipType.PROVIDER
      "client"      | "email@client.com"   | RelationshipType.CLIENT
  }

  def "Adding a relation between an integrated and a disabled user"() {
    given: "An existing user"
      def integrated = new User(
        name: "name",
        email: "email@user.com",
        enabled:true
      )
      integrated.save()

    and: "Client/provider"
      def provider = new User(
        name: providerName,
        email: providerEmail,
        enabled:false
      )
      provider.save()

    and: "given params"
      def params = [
        email: providerEmail,
        relationshipType: relationshipType
      ]

    when:
      def user = service.addRelationshipToIntegrated(integrated.id, params)

    then:
      user.id
      user.relationships.size() == 1
      user.relationships[0].type == relationshipType
      user.relationships[0].users.size() == 2
      user.relationships[0].users*.enabled == [true, false]

      provider.relationships.size() == 1
      provider.relationships[0].id == user.relationships[0].id

    where:
      providerName  | providerEmail        | relationshipType
      "provider"    | "email@provider.com" | RelationshipType.PROVIDER
      "client"      | "email@client.com"   | RelationshipType.CLIENT
  }

  def "Adding a new relationship to a user with other provider"() {
    given: "An existing user"
      def integrated = new User(
        name: "name",
        email: "email@user.com",
        enabled:true
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
        enabled: false,
        relationshipType: relationshipType
      ]

    when:
      def user = service.addRelationshipToIntegrated(integrated.id, params)

    then:
      user.id
      user.relationships.size() == 2

      provider.relationships.size() == 1

    where:
      providerName  | providerEmail        | relationshipType
      "provider"    | "email@provider.com" | RelationshipType.PROVIDER
      "client"      | "email@client.com"   | RelationshipType.CLIENT
  }


}
