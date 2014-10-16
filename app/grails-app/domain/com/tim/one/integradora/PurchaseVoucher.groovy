package com.tim.one.integradora

class PurchaseVoucher implements Voucher {

  User provider

  String description
  BigDecimal ieps
  BigDecimal iva

  static hasMany = [products:Product, projects:Project]

  static constraints = {
    description size:1..255, nullable:true, blank:false
    ieps min:0.00, scale:2
    iva min:0.00, scale:2
  }

}
