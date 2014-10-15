package com.tim.one.integradora

class PurchaseTicket {

  Long folio

  Ticket ticket
  User provider
  User integrated

  String descripcion
  BigDecimal ieps
  BigDecimal iva

  static hasMany = [products:Product, projects:Project]

  static constraints = {
  }
}
