package com.tim.one.integradora

class DepositTicket {

  Ticket ticket

  String attachmentUrl
  Long orderNumber // auto-increment per integrated

  static belongsTo = [user:User]

  static constraints = {
    attachmentUrl size:1..255, nullable:true, blank: true
  }
}
