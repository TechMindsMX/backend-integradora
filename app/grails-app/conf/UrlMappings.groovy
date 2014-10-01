class UrlMappings {

	static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
      }
    }

    "/users"(resources:"user"){
      "/projects"(resources:"project")
    }


    "/"(view:"/index")
    "500"(view:'/error')
  }
  
}
