package com.tim.one.integradora

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

class SellOrderController {

  def index() { }

  @Transactional
  def save(SellOrderCommand command) {
    if (command.hasErrors() || command.details*.validate().find { !it }){
      render(contentType:"application/json", status:BAD_REQUEST) {
        command*.errors + command.details*.errors
      }
      return
    }

    def sellOrder = new SellOrder()
    sellOrder.placement = command.placement
    sellOrder.observations = command.observations
    sellOrder.comertialConditions = command.comertialConditions
    sellOrder.subTotal = command.subTotal
    sellOrder.total  = command.total
    sellOrder.paymentType = command.paymentType
    sellOrder.project = command.project
    sellOrder.integrated = User.get(command.userId)
    sellOrder.folio = 1L

    command.details.each { detail ->
      def sellOrderDetail = new SellOrderDetail()
      sellOrderDetail.properties = detail.properties
      sellOrder.addToDetails(sellOrderDetail)
    }
    sellOrder.save()

    render(contentType:"application/json", status:200) {
      sellOrder
    }
  }

}

class SellOrderCommand {
  String placement
  String observations
  String comertialConditions

  BigDecimal subTotal
  BigDecimal total

  PaymentType paymentType = PaymentType.CHECK
  Long userId

  Project project
  List<SellOrderDetailCommand> details

  static constraints = {
    total min:0.00, scale:2
    subTotal min:0.00, scale:2
    project nullable:true

    placement size:1..255
    observations size:1..255
    comertialConditions size:1..255
  }
}


@grails.validation.Validateable
class SellOrderDetailCommand {
  Product product
  Integer quantity
  String concept

  BigDecimal iva
  BigDecimal ieps
  BigDecimal price

  UnitMeasure unitMeasure = UnitMeasure.PIECE

  static constraints = {
    quantity min:0
    concept size:1..255

    iva min:0.00, scale:2
    ieps min:0.00, scale:2
    price min:0.00, scale:2
  }
}
