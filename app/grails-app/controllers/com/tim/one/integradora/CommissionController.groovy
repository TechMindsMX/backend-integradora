package com.tim.one.integradora

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

class CommissionController {

  def index() { }

  @Transactional
  def save(Commission commission) {
    if(commission.hasErrors()) {
      log.error commission.errors
      render(status:BAD_REQUEST)
      return
    }

    commission.save()

    render(contentType:"application/json", status:CREATED) {
      commission
    }
  }

}
