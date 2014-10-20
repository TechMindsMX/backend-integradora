package com.tim.one.integradora

class Commission {

  String description

  CommissionType type = CommissionType.TRANSACTIONAL

  FrequencyTime time = FrequencyTime.WEEKLY

  boolean enabled = true

  static constraints = {
    description size:1..255
  }

}
