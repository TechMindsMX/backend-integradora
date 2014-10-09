package com.tim.one.integradora

import grails.converters.JSON
import grails.transaction.Transactional

class UserController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index() {
    render(contentType:"application/json", status:200) {
      [users: User.findAllByEnabled(true)]
    }
  }

  @Transactional
  def save(User user) {
    if(user.hasErrors()) {
      respond user.errors
      return
    }
    user.save()

    JSON.use('user') {
      render(contentType:"application/json", status:201) {
        user
      }
    }
  }

  @Transactional
  def delete(Long id) {
    User user = User.get(id)
    user.enabled = false
    user.save()
    render(status:202)
  }

  def show(Long id){
    render(contentType:"application/json", status:200) {
      User.findByIdAndEnabled(id, true)
    }
  }

}
