class UrlMappings {

	static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
      }
    }

    "/users"(resources:"user"){
      "/projects"(resources:"project")
      "/products"(resources:"product")
    }


    "/"(view:"/index")
    "500"(view:'/error')
  }

}
