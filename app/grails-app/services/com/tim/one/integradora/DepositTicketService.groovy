package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class DepositTicketService {

  def createDepositTicketForUser(userId, commandData) {
    User user = User.get(userId)
    Ticket ticket = new Ticket(commandData.properties)
    ticket.integrated = user
    ticket.save()

    DepositTicket depositTicket = new DepositTicket(commandData.properties)
    depositTicket.ticket = ticket
    depositTicket.orderNumber = calculateOrderNumberForUser(user)
    depositTicket.save()
    depositTicket
  }

  private def calculateOrderNumberForUser(user) {
    def userTickets = Ticket.findAllByIntegrated(user)
    def depositTicketsForUser = DepositTicket.findAllByTicketInList(userTickets)
    def orderNumberMax = depositTicketsForUser.max { it.orderNumber }?.orderNumber ?: 0
    orderNumberMax + 1
  }
}
