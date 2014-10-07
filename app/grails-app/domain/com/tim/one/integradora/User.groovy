package com.tim.one.integradora

import grails.rest.*

class User {

  String name
  String email
  UserStatus status = UserStatus.ENABLED

  Date dateCreated
  Date lastUpdated

  static hasMany = [projects:Project, products:Product]

  static constraints = {
   name blank:false,size:1..255
   email email:true,unique:true,size:1..100
 }

}
