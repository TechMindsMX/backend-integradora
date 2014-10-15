package com.tim.one.integradora

class VoucherLink {

  Long voucherRef
  String type

  static hasOne = [voucherDetail:VoucherDetail]

  static constraints = {
    voucherRef min:0L
    type blank:false
  }

}
