package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Project)
@Mock(User)
class ProjectSpec extends Specification {

    void "validating constraints"() {
      setup:
      def project = new Project(
        name : name,
        description : description,
        user: new User()
      )

      when:
        project.save()

      then:
        assert project.errors.allErrors*.code == expected

      where:
       name               | description   ||  expected
       null               | null          ||  ["nullable"] 
       "1" * 256          | "1" * 256     ||  ["size.toobig"] * 2
    }
}
