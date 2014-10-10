package com.tim.one.integradora

class Ticket {

  String currency
  String observations

  BigDecimal totalAmount

  PaymentType paymentType = PaymentType.CHECK
  TicketStatus status = TicketStatus.NEW

  Date paymentDate
  Date dateCreated
  Date lastUpdated

  static constraints = {
    currency size:1..3
    observations size:1..255, nullable:true, blank:false
    totalAmount min:0.00, scale:2
    paymentDate nullable:true
  }

}
