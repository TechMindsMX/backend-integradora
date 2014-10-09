package com.tim.one.integradora

class Ticket {

  String currency
  String observations
  String attachmentUrl
  BigInteger orderNumber // auto-increment per integrated
                         //esto no esta en la orden de compra asi que se debe quitar para usar esta tabla como base para las ordenes

  User integrated

  BigDecimal totalAmount

  PaymentType paymentType = PaymentType.CASH
  TicketStatus status = TicketStatus.NEW

  Date paymentDate
  Date dateCreated
  Date lastUpdated

  static constraints = {
    currency size:1..3
    observations size:1..255, nullable:true, blank:false
    attachmentUrl size:1..255, nullable:true, blank: true
    payment min:0.00, scale:2
    totalAmount min:0.00, scale:2
  }

}
