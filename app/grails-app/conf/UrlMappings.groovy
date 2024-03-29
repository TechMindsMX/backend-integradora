class UrlMappings {

	static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
      }
    }

    "/commissions"(resources:"commission")

    "/users"(resources:"user"){
      "/projects"(resources:"project")
      "/products"(resources:"product")
      "/relationships"(resources:"relationship")
      "/depositVouchers"(resources:"depositVoucher")
      "/purchaseVouchers"(resources:"purchaseVoucher")
      "/sellOrders"(resources:"sellOrder")
    }


    "/"(view:"/index")
    "500"(view:'/error')
  }

}
