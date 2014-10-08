package com.tim.one.integradora

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Product)
@Mock(User)
class ProductSpec extends Specification {

    void "validating constraints"() {
      setup:
      def product = new Product(
        name : name,
        price : price,
        iva : iva,
        ieps : ieps,
        description : description,
        measure : measure,
        currency : currency,
        user: new User()
      )

      when:
        product.save()

      then:
        assert product.errors.allErrors*.code == expected

      where:
       name          | price    | iva   | ieps | description  | measure   | currency ||  expected
       null          | null     | null  | null | null         | null      | null     ||  ["nullable"] * 5
       "1" * 256     | 100.00   | 16.00 | 3.00 | "1" * 256    | "1" * 101 | "1" * 4  ||  ["size.toobig"] * 4
    }
}