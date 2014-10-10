package com.tim.one.integradora

import grails.rest.*

class Project {

  Project parent
  String name
  String description
  ProjectStatus status = ProjectStatus.ENABLED

  Date dateCreated
  Date lastUpdated

  static belongsTo = [user:User]
  static hasMany = [subProjects:Project]

  static constraints = {
    parent nullable:true
    name blank:false,size:1..255
    description blank:true,nullable:true,size:1..255
  }

}
