describe("Sell order behavior", function() {
  var sellOrder = null;

  describe("Adding new sell order", function() {
    beforeEach(function(done) {
      var useremail = new Date().getTime() + '@email.com'
      var user = new User({
        name: 'usuario',
        email: useremail,
        rfc: "0123456789012"
      });

      user.save().then(
        function(data) {
          var newUser = new User(data);
          var newProduct = new Product({
            name: "product name",
            price: 100.00,
            iva: 16,
            ieps: 10,
            currency: "NMX",
            user: newUser
          });

          newProduct.save().then(
            function(response) {
              var product = new Product(response);
              var newSellOrder = new SellOrder({
                total:100,
                subTotal: 100,
                placement: 'Mexico, DF',
                observations: 'retfyghjukl',
                comertialConditions: 'asdfjaskñldfjlaksjdflkajsdf',
                userId: newUser.id,
                details: [{
                  product: product.id,
                  quantity: 1,
                  concept: 'Viaticos',
                  measure: 'PIECE',
                  price: 100,
                  iva: 0,
                  ieps: 0
                }]
              });

              newSellOrder.save().then(
                function(orderResponse) {
                  console.log('sellOrder');
                  console.log(sellOrder);
                  sellOrder = new SellOrder(orderResponse);
                  done();
                }
              );
            }
          )
        }
      );
    });

    afterEach(function(done) {
      var newUser = new User(sellOrder.integrated);
      newUser.delete().then(function() {
        done();
      });
    });

    it("Should create a sell order for an integrated", function(done) {
      expect(sellOrder).not.toBe(null);
      done();
    });
  });

});
