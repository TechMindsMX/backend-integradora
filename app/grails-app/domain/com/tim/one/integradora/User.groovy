package com.tim.one.integradora

class User {

  String name
  String email
  //TODO: cambiar por enabled
  UserStatus status = UserStatus.ENABLED

  Date dateCreated
  Date lastUpdated

  static hasMany = [projects:Project, products:Product, relationships:Relationship]

  static constraints = {
   name blank:false,size:1..255
   email email:true,unique:true,size:1..100
 }

}
