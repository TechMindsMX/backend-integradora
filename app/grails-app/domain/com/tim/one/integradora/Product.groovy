package com.tim.one.integradora

import grails.rest.*

@Resource(uri="/products")
class Product {

  String name
  String measure
  BigDecimal price
  BigDecimal iva
  BigDecimal ieps
  String currency
  ProjectStatus status
  String description

  Date dateCreated    
  Date lastUpdated

  static belongsTo = [user:User]

  static constraints = {
    name blank:false,size:1..255
    price min:0.00,scale:2
    iva min:0.00,max:100.00,scale:2
    ieps min:0.00,max:100.00,scale:2
    description blank:true,nullable:true,size:1..255
  }

}
