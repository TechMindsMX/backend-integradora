package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(User)
@Mock(Relationship)
class UserSpec extends Specification {

<<<<<<< HEAD
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

  def "Adding a relation to an existing user"() {
    given: "An existing user"
      def user = new User(name: "name", email: "email@user.com")
      user.save()

    and: "Add an provider"
      def provider = new User(name:providerName, email:providerEmail)
      provider.status = UserStatus.DISABLED
      provider.save()
      println provider

      def relationship = new Relationship(user:provider)
      relationship.type = relationshipType
      relationship.user = provider
      user.addToRelationships(relationship)

    when:
      def userUpdated = user.save()

    then:
      userUpdated.id
      userUpdated.relationships.size() > 0
      userUpdated.relationships[0].id
      userUpdated.relationships[0].type == relationshipType
      userUpdated.relationships[0].user.status == UserStatus.DISABLED

    where:
      providerName  | providerEmail        | relationshipType
      "provider"    | "email@provider.com" | RelationshipType.PROVIDER
      "provider"    | "email@provider.com" | RelationshipType.CLIENT
  }

  void "validating constraints"() {
    setup:
    def user = new User(
      name : name,
      email : email
    )

    when:
      user.save()

    then:
      assert user.errors.allErrors*.code == expected

     where:
     name      | email                    ||  expected
     null      | "josdem@trama.mx"        ||  ["nullable"]
     "1" * 256 | "1" * 100 + "@trama.mx"  ||  ["size.toobig"] * 2
  }

}
