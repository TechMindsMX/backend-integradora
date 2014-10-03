package com.tim.one.integradora

import grails.transaction.Transactional

class UserController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index() {
    render(contentType:"application/json", status:200) {
      [users: User.findAllByStatus(UserStatus.ENABLED)]
    }
  }

  @Transactional
  def save(User user) {
    if(user.hasErrors()) {
      respond user.errors
      return
    }
    user.save()
    render(contentType:"application/json", status:201) {
      user
    }
  }

  @Transactional
  def delete(Long id) {
    User user = User.get(id)
    user.status = UserStatus.DISABLED
    user.save()
    render(status:202)
  }

  def show(Long id){
    render(contentType:"application/json", status:200) {
      User.findById(id)
    }   
  }

}
