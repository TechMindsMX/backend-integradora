package com.tim.one.integradora

class User {

    String name
    String email

    Date dateCreated
    Date lastUpdated

    static constraints = {
	name blank:false,nullable:false,size:1..255
	email blank:false,nullable:false,email:true,size:1..100	
    }

}
