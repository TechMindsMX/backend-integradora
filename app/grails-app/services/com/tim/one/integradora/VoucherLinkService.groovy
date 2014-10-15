package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class VoucherLinkService {

  def saveVoucher(command) {
    User integrated = User.get(command.userId)

    def instance = command.instance
    if(!Voucher.class.isAssignableFrom(instance.class)) {
      throw new Exception("Class not implements Voucher")
    }

    instance.save()

    def voucherLink = new VoucherLink(voucherRef: instance.id, type:instance.class.simpleName)
    def voucherDetail = new VoucherDetail(command.properties + [integrated:integrated])
    voucherDetail.folio = 1
    voucherLink.voucherDetail = voucherDetail
    voucherLink.save()

    [(instance.class.simpleName.toLowerCase()):instance, detail:voucherDetail]
  }

}
