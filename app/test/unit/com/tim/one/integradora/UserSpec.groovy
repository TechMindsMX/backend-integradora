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
        email: email,
        rfc: rfc
      )
    when:
      user.save()

    then:
      user.id
      user.name == name
      user.email == email
      user.rfc == rfc
      user.enabled

    where:
      name    | email            | rfc
      "name1" | "name@email.com" | "123456789012"
  }

  void "validating constraints"() {
    setup:
    def user = new User(
      name : name,
      email : email,
      rfc: rfc
    )

    when:
      user.save()

    then:
      assert user.errors.allErrors*.code == expected

     where:
     name      | email                    | rfc      || expected
     null      | "josdem@trama.mx"        | null     ||  ["nullable"] * 2
     "1" * 256 | "1" * 100 + "@trama.mx"  | "1" * 14 ||  ["size.toobig"] * 3
  }

}
