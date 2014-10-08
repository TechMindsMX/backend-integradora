package com.tim.one.integradora

class Profile {

  String tradeName
  String corporateName
  String phone

  static constraints = {
    tradeName blank:false, size:1..255
    corporateName nullable:true, blank:true, size:1..255
    phone size:10..10
  }
}
