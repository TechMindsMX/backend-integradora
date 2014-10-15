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
      "/depositVouchers"(resources:"depositVoucher")
    }


    "/"(view:"/index")
    "500"(view:'/error')
  }

}
