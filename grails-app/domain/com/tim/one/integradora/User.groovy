package com.tim.one.integradora

import grails.rest.*

@Resource(uri="/users")
class User {

  String name
  String email

  Date dateCreated
  Date lastUpdated

  static hasMany = [projects:Project]

  static constraints = {
   name blank:false,nullable:false,size:1..255
   email blank:false,nullable:false,email:true,unique:true,size:1..100	
 }

}
