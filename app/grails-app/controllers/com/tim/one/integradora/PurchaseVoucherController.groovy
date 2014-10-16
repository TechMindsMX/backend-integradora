package com.tim.one.integradora

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class PurchaseVoucherController {

  def voucherLinkService

  def index() {
    User user = User.findByIdAndEnabled(params.userId, true)

    def queryParams = [:]
    if(params.start && params.end) {
      queryParams.start = Date.parse('dd/MMM/yyyy', params.start)
      queryParams.end = Date.parse('dd/MMM/yyyy', params.end)
    }

    def vouchers = voucherLinkService.listVouchersAndDetailsForUserAndType(user.id, 'PurchaseVoucher', queryParams)

    render(contentType:'application/json', status:OK) {
      vouchers
    }
  }

  @Transactional
  def save(PurchaseVoucherCommand purchaseVoucherCommand) {
    if(purchaseVoucherCommand.hasErrors()) {
      render(contentType:'application/json', status:BAD_REQUEST) {
        purchaseVoucherCommand.errors
      }
      return
    }

    def results = voucherLinkService.saveVoucher(purchaseVoucherCommand);
    render(contentType:'application/json', status:CREATED) {
      results
    }
  }
}

class PurchaseVoucherCommand {
  String currency
  String observations
  BigDecimal totalAmount
  PaymentType paymentType = PaymentType.SPEI
  Long userId

  User provider
  String description
  BigDecimal ieps
  BigDecimal iva


  static constraints = {
    currency size:1..3
    iva min:0.00, scale:2
    ieps min:0.00, scale:2
    totalAmount min:0.00, scale:2
    observations size:1..255, nullable:true, blank:false
    description size:1..255, nullable:true, blank:false
  }

  def getInstance() {
    PurchaseVoucher purchaseVoucher = new PurchaseVoucher()
    purchaseVoucher.provider = this.provider
    purchaseVoucher.description = this.description
    purchaseVoucher.ieps = this.ieps
    purchaseVoucher.iva = this.iva
    purchaseVoucher
  }
}
