package com.tim.one.integradora

class SellOrder {

  User integrated
  PaymentType paymentType = PaymentType.CHECK
  Long folio
  String observations

  String comertialConditions
  String placement = "Mexico, DF"
  BigDecimal subTotal
  BigDecimal total

  static hasMany = [projects:Project, details:SellOrderDetail]

  static constraints = {
    placement size:1..255
    observations size:1..255, nullable:true, blank:false
    comertialConditions size:1..255, nullable:true, blank:false
    subTotal min:0.00, scale:2
    total min:0.00, scale:2
  }
}
