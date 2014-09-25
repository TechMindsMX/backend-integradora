package com.tim.one.integradora

class Project {

    Integer integradoId
    Integer parentId
    Integer status
    String name
    String description

    Date dateCreated    
    Date lastUpdated

    static constraints = {
	integradoId min:0,nullable:false
	parentId min:0,nullable:false
	status min:0,max:1,nullable:false
        name blank:false,nullable:false,size:1..255
	description blank:true,nullable:true,size:1..255
    }

}
