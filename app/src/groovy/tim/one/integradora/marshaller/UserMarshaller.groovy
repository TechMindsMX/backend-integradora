package com.mymapmanager.marshaller

import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller
import com.tim.one.integradora.User

class UserMarshaller implements ObjectMarshaller {

  boolean supports(Object object) {
    object instanceof User
  }

  void marshalObject(object, json) {

    User userRaw = (User) object

    def userVal = [:]
    userVal.id = userRaw.id
    userVal.name = userRaw.name
    userVal.email = userRaw.email
    userVal.rfc = userRaw.rfc
    userVal.enabled = userRaw.enabled

    json.convertAnother(userVal)
  }
}
