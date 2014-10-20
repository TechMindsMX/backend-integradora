package com.tim.one.integradora

class User {

  String name
  String email
  String rfc

  boolean enabled = true

  Profile profile

  Date dateCreated
  Date lastUpdated

  static hasMany = [projects:Project, products:Product, relationships:Relationship, sellOrders:SellOrder]

  static constraints = {
    name blank:false,size:1..255
    email email:true,unique:true,size:1..100
    rfc blank:false, size:12..13
    profile nullable:true
  }

}
