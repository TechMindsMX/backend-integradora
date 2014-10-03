package com.tim.one.integradora

import grails.transaction.Transactional

class ProductController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index() { 
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    render(contentType:"application/json", status:200) {
      Product.findAllByUserAndStatus(user, ProjectStatus.ENABLED)
    }
  }

  @Transactional
  def save(Product product) {
    User user = User.get(params.userId)
    product.user = user
    product.validate()

    if(product.hasErrors()) {
      render(contentType:"application/json", status:400) {
        product.errors
      }
      return
    }

    product.save()
    render(contentType:"application/json", status:201) {
      product
    }
  }

  @Transactional
  def delete(Long id) {
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    if (!user){
      render(status:404)
      return
    }
    Product product = Product.findByIdAndUser(id, user)
    product.status = ProjectStatus.DISABLED
    product.save()
    render(status:202)
  }

  def show(Long id){
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    render(contentType:"application/json", status:200) {
      Product.findByIdAndUserAndStatus(id, user, ProjectStatus.ENABLED)
    }
  }

}
