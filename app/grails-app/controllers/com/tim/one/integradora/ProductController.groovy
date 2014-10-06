package com.tim.one.integradora

import grails.transaction.Transactional

class ProductController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index() {
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    def products = Product.findAllByUserAndStatus(user, ProjectStatus.ENABLED)

    if (params.name){
      if (params.name.size() < 3){
        returnNotFound(400, [text:"Minimum length violation"])
        return
      }
      products = Product.findAllByUserAndStatusAndNameLike(user, ProjectStatus.ENABLED, "%${params.name}%")
    }

    render(contentType:"application/json", status:200) {
      products
    }
  }

  def returnNotFound(status, errors){
    render(contentType:"application/json", status:400) {
      errors
    }
  }

  @Transactional
  def save(Product product) {
    User user = User.get(params.userId)
    product.user = user
    product.validate()

    if(product.hasErrors()) {
      returnNotFound(400, product.errors)
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
