package com.tim.one.integradora

import grails.rest.*

class Product {

  String name
  String measure
  String currency
  String description

  ProjectStatus status = ProjectStatus.ENABLED

  BigDecimal price
  BigDecimal iva
  BigDecimal ieps

  Date dateCreated
  Date lastUpdated

  static belongsTo = [user:User]

  static constraints = {
    name blank:false,size:1..255
    price min:0.00,scale:2
    iva min:0.00,max:100.00,scale:2
    ieps min:0.00,max:100.00,scale:2
    description blank:true,nullable:true,size:1..255
    measure size:1..100, nullable:true
    currency size:1..3
  }

}
