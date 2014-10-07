package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(User)
@Mock(Relationship)
class UserSpec extends Specification {

  def "user creation"() {
    given: "A new user"
      def user = new User(
        name: name,
        email: email
      )
    when:
      user.save()

    then:
      user.id
      user.name == name
      user.email == email
      user.status == UserStatus.ENABLED

    where:
      name    | email
      "name1" | "name@email.com"
  }

  def "Adding a provider to an existen user"() {
    given: "An existing user"
      def user = new User(name: "name", email: "email@user.com")
      user.save()

    and: "Add an provider"
      def provider = new User(name:providerName, email:providerEmail)
      provider.save()

      def relationship = new Relationship(type:type, user:provider)
      user.addToRelationships(relationship)

    when:
      def userUpdated = user.save()

    then:
      userUpdated.id
      userUpdated.relationships.size() > 0
      userUpdated.relationships[0].id

    where:
      providerName  | providerEmail        | type
      "provider"    | "email@provider.com" | RelationshipType.PROVIDER

  }

}
