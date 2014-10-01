package com.tim.one.integradora

import grails.rest.*

@Resource(uri="/projects")
class Project {

  Project parent
  ProjectStatus status
  String name
  String description

  Date dateCreated    
  Date lastUpdated

  static belongsTo = [user:User]
  static hasMany = [subProjects:Project]

  static constraints = {
    parent nullable:true
    name blank:false,nullable:false,size:1..255
    description blank:true,nullable:true,size:1..255
  }

}
