package com.tim.one.integradora

class User {

  String name
  String email

  String rfc
  String tradeName
  String corporateName
  String phone

  boolean enabled = true

  Date dateCreated
  Date lastUpdated

  static hasMany = [projects:Project, products:Product, relationships:Relationship]

  static constraints = {
   name blank:false,size:1..255
   email email:true,unique:true,size:1..100

   rfc blank:false, size:12..13
   tradeName blank:false, size:1..255
   corporateName nullable:true, blank:true, size:1..255
   phone size:10..10

 }

}
