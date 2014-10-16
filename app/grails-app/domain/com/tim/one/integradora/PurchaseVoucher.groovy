package com.tim.one.integradora

class PurchaseVoucher {

  User provider
  User integrated

  String descripcion
  BigDecimal ieps
  BigDecimal iva

  static hasMany = [products:Product, projects:Project]

  static constraints = {
  }

}
