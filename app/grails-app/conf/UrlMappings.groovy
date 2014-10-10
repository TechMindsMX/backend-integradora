class UrlMappings {

	static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
      }
    }

    "/users"(resources:"user"){
      "/projects"(resources:"project")
      "/products"(resources:"product")
      "/relationships"(resources:"relationship")
      "/depositTickets"(resources:"depositTicket")
    }


    "/"(view:"/index")
    "500"(view:'/error')
  }

}
