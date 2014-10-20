package com.tim.one.integradora

import static org.springframework.http.HttpStatus.*

class SellOrderController {

  def sellOrderService

  def index() {
    User user = User.get(params.userId)
    if(!user) {
      render(status:BAD_REQUEST)
      return
    }

    render(contentType:"application/json", status:OK) {
      user.sellOrders
    }
  }

  def save(SellOrderCommand command) {
    if (command.hasErrors() || command.details*.validate().find { !it }){
      render(contentType:"application/json", status:BAD_REQUEST) {
        command*.errors + command.details*.errors
      }
      return
    }

    def sellOrder = sellOrderService.saveOrderForIntegratedWithData(command.userId, command)

    render(contentType:"application/json", status:CREATED) {
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
