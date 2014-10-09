package com.tim.one.integradora

class Relationship {

  RelationshipType type = RelationshipType.PROVIDER
  boolean enabled = true

  static belongsTo = User
  static hasMany = [users:User]

  static constraints = {
  }

}
