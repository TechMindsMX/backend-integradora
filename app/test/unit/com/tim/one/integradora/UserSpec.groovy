package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(User)
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

}
