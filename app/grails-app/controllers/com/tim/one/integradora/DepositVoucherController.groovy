package com.tim.one.integradora

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class DepositVoucherController {

  def voucherLinkService

  def index() {
    User user = User.findByIdAndEnabled(params.userId, true)
    def vouchers = voucherLinkService.listVouchersAndDetailsForUserAndType(user.id, 'DepositVoucher')
    render(contentType:'application/json', status:OK) {
      vouchers
    }
  }

  @Transactional
  def save(DepositVoucherCommand depositVoucherCommand) {
    if(depositVoucherCommand.hasErrors()) {
      render(contentType:'application/json', status:BAD_REQUEST) {
        depositVoucherCommand.errors
      }
      return
    }

    def results = voucherLinkService.saveVoucher(depositVoucherCommand);
    render(contentType:'application/json', status:CREATED) {
      results
    }
  }
}

class DepositVoucherCommand {
  String currency
  String attachmentUrl
  String observations
  BigDecimal totalAmount
  PaymentType paymentType = PaymentType.SPEI
  Long userId

  static constraints = {
    currency size:1..3
    attachmentUrl size:1..255, nullable:true, blank: true
    observations size:1..255, nullable:true, blank:false
    totalAmount min:0.00, scale:2
  }

  def getInstance() {
    DepositVoucher depositVoucher = new DepositVoucher()
    depositVoucher.attachmentUrl = this.attachmentUrl
    depositVoucher
  }
}
