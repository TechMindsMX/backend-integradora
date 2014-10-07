package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

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
