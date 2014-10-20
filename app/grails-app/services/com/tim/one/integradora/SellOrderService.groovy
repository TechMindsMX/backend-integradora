package com.tim.one.integradora

import grails.transaction.Transactional

@Transactional
class SellOrderService {

  def saveOrderForIntegratedWithData(integratedId, command) {

    def sellOrder = new SellOrder()
    sellOrder.placement = command.placement
    sellOrder.observations = command.observations
    sellOrder.comertialConditions = command.comertialConditions
    sellOrder.subTotal = command.subTotal
    sellOrder.total  = command.total
    sellOrder.paymentType = command.paymentType
    sellOrder.project = command.project
    sellOrder.integrated = User.get(integratedId)
    sellOrder.folio = calculateSellOrderFolioForIntegrated(integratedId)

    command.details.each { detail ->
      def sellOrderDetail = new SellOrderDetail()
      sellOrderDetail.properties = detail.properties
      sellOrder.addToDetails(sellOrderDetail)
    }

    sellOrder.save()
  }

  private calculateSellOrderFolioForIntegrated(integratedId) {
    User integrated = User.get(integratedId)

    def sellOrderFolio = (integrated*.sellOrders*.folio.flatten().max() ?: 0) + 1
    sellOrderFolio
  }
}
