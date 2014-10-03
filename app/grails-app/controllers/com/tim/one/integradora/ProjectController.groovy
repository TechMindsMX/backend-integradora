package com.tim.one.integradora

import grails.transaction.Transactional

class ProjectController {

  static allowedMethods = [index:'GET', save:'POST', delete:'DELETE']

  def index() { 
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    render(contentType:"application/json", status:200) {
      Project.findAllByUserAndStatus(user, ProjectStatus.ENABLED)
    }
  }

  @Transactional
  def save(Project project) {
    User user = User.get(params.userId)
    project.user = user
    project.validate()

    if(project.hasErrors()) {
      render(contentType:"application/json", status:400) {
        project.errors
      }
      return
    }

    project.save()
    render(contentType:"application/json", status:201) {
      project
    }
  }

  @Transactional
  def delete(Long id) {
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    if (!user){
      render(status:404)
      return
    }
    Project project = Project.findByIdAndUser(id, user)
    project.status = ProjectStatus.DISABLED
    project.save()
    render(status:202)
  }

  def show(Long id){
    User user = User.findByIdAndStatus(params.userId, UserStatus.ENABLED)
    render(contentType:"application/json", status:200) {
      Project.findByIdAndUserAndStatus(id, user, ProjectStatus.ENABLED)
    }
  }
}
