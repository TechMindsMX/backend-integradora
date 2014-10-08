package com.tim.one.integradora

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

class ProductController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index(IndexValidationCommand validationCommand) {
    User user = User.findByIdAndEnabled(params.userId, true)
    def products = Product.findAllByUserAndStatus(user, ProjectStatus.ENABLED)

    if (validationCommand.hasErrors()){
      renderCodeErrorWithMessage(BAD_REQUEST, validationCommand.errors)
      return
    }

    if(validationCommand.name) {
      products = Product.findAllByUserAndEnabledAndNameLike(user, true, "%${params.name}%")
    }

    render(contentType:"application/json", status:OK) {
      products
    }
  }

  def renderCodeErrorWithMessage(status, errors){
    render(contentType:"application/json", status:BAD_REQUEST) {
      errors
    }
  }

  @Transactional
  //TODO: Crear command para el product sin el user
  def save(Product product) {
    User user = User.get(params.userId)
    product.user = user
    product.validate()

    if(product.hasErrors()) {
      renderCodeErrorWithMessage(BAD_REQUEST, product.errors)
    }

    product.save()
    render(contentType:"application/json", status:CREATED) {
      product
    }
  }

  @Transactional
  def delete(Long id) {
    User user = User.findByIdAndEnabled(params.userId, true)
    if (!user){
      renderCodeErrorWithMessage(BAD_REQUEST, [errors:[message:'URL Malformed']])
      return
    }
    Product product = Product.findByIdAndUser(id, user)
    product.status = ProjectStatus.DISABLED
    product.save()
    render(status:ACCEPTED)
  }

  def show(Long id){
    User user = User.findByIdAndEnabled(params.userId, true)
    render(contentType:"application/json", status:OK) {
      Product.findByIdAndUserAndStatus(id, user, ProjectStatus.ENABLED)
    }
  }
}

class IndexValidationCommand {
  String name

  static constraints = {
    name nullable:true, size:3..255
  }
}
