package com.tim.one.integradora

import grails.transaction.Transactional
import grails.rest.*

class UserController {

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond User.list(params), model:[userCount: User.count()]
  }

  @Transactional
  def save(User user) {
    if(user.hasErrors()) {
      respond user.errors
      return
    }
    user.save()
    respond user
  }

}
