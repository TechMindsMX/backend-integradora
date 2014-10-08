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
      user.enabled

    where:
      name    | email
      "name1" | "name@email.com"
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
