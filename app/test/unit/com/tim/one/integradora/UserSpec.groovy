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
        rfc: rfc,
        tradeName: tradeName,
        corporateName: corporateName,
        phone: phone
      )
    when:
      user.save()

    then:
      user.id
      user.name == name
      user.email == email
      user.rfc == rfc
      user.tradeName == tradeName
      user.corporateName == corporateName
      user.phone == phone
      user.enabled

    where:
      name    | email            | rfc            | tradeName | corporateName | phone
      "name1" | "name@email.com" | "123456789012" | "razon"   | "corportate"  | "1234567890"
  }

  void "validating constraints"() {
    setup:
    def user = new User(
      name : name,
      email : email,
      rfc: rfc,
      tradeName: tradeName,
      corporateName: corporateName,
      phone: phone
    )

    when:
      user.save()

    then:
      assert user.errors.allErrors*.code == expected

     where:
     name      | email                    | rfc            | tradeName | corporateName | phone    || expected
     null      | "josdem@trama.mx"        | null           | null      | null          | null     ||  ["nullable"] * 4
     "1" * 256 | "1" * 100 + "@trama.mx"  | "1" * 14       | "1" * 256 | "1" * 256     | "1" * 11 ||  ["size.toobig"] * 6
  }

}
