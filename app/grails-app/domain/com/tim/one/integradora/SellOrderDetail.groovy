package com.tim.one.integradora

class SellOrderDetail {
  Product product
  Integer quantity
  String concept

  UnitMeasure unitMeasure = UnitMeasure.PIECE

  BigDecimal price
  BigDecimal iva
  BigDecimal ieps

  static constraints = {
    quantity min:0
    concept size:1..255
    iva min:0.00, scale:2
    ieps min:0.00, scale:2
    price min:0.00, scale:2
  }
}
