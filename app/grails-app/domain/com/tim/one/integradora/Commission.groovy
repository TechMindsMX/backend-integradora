package com.tim.one.integradora

class Commission {

  String description

  CommissionType type = CommissionType.TRANSACTIONAL

  FrequencyTime frequency = FrequencyTime.WEEKLY

  boolean enabled = true

  static constraints = {
    description size:1..255
  }

}
