package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class DepositTicketService {

  def createDepositTicketForUser(userId, commandData) {
    User user = User.get(userId)
    Ticket ticket = new Ticket(commandData.properties)
    ticket.save()

    DepositTicket depositTicket = new DepositTicket(commandData.properties)
    depositTicket.ticket = ticket
    depositTicket.user = user
    depositTicket.orderNumber = calculateOrderNumberForUser(user)
    depositTicket.save()

    // user.addToDepositTickets(depositTicket)
    // user.save()

    depositTicket
  }

  private def calculateOrderNumberForUser(user) {
    def depositTicketsForUser = DepositTicket.findAllByUser(user)
    def orderNumberMax = depositTicketsForUser.max { it.orderNumber }?.orderNumber ?: 0
    orderNumberMax + 1
  }
}
