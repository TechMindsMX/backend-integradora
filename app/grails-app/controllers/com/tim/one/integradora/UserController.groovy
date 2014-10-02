package com.tim.one.integradora

import grails.transaction.Transactional
import grails.rest.*

class UserController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond User.findAllByStatus(UserStatus.ENABLED, params), model:[userCount: User.count()]
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

  @Transactional
  def delete(Long id) {
    User user = User.get(id)
    user.status = UserStatus.DISABLED
    user.save()
    render(status:202)
  }

}
