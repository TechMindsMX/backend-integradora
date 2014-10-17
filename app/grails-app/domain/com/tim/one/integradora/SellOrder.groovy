package com.tim.one.integradora

class SellOrder {

  User integrated
  Project project

  PaymentType paymentType = PaymentType.CHECK
  Long folio
  String observations

  String comertialConditions
  String placement
  BigDecimal subTotal
  BigDecimal total

  static hasMany = [details:SellOrderDetail]

  static constraints = {
    project nullable:true
    placement size:1..255
    observations size:1..255, nullable:true, blank:false
    comertialConditions size:1..255, nullable:true, blank:false
    subTotal min:0.00, scale:2
    total min:0.00, scale:2
  }
}
