package com.tim.one.integradora

class DepositVoucher implements Voucher {

  String attachmentUrl

  Date dateCreated
  Date lastUpdated

  static constraints = {
    attachmentUrl size:1..255, nullable:true, blank: true
  }

}
