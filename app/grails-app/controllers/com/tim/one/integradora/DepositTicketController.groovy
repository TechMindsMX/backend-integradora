package com.tim.one.integradora

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class DepositTicketController {

  def depositTicketService

  def index() {
    User user = User.get(params.userId)
    def depositTickets = user.depositTickets
    render(contentType:'application/json', status:OK) {
      depositTickets
    }
  }

  @Transactional
  def save(DepositTicketCommand depositTicketCommand) {
    if(depositTicketCommand.hasErrors()) {
      render(contentType:'application/json', status:BAD_REQUEST) {
        depositTicketCommand.errors
      }
      return
    }

    def depositTicket = depositTicketService.createDepositTicketForUser(params.userId, depositTicketCommand)
    render(contentType:'application/json', status:CREATED) {
      depositTicket
    }

  }

}

class DepositTicketCommand {
  String currency
  String attachmentUrl
  String observations
  BigDecimal totalAmount
  PaymentType paymentType = PaymentType.SPEI
  User userId

  static constraints = {
    currency size:1..3
    attachmentUrl size:1..255, nullable:true, blank: true
    observations size:1..255, nullable:true, blank:false
    totalAmount min:0.00, scale:2
  }
}
