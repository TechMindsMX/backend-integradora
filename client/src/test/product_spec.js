describe("Product behavior", function() {
  var product = null;

  describe("Adding new product", function() {
    beforeEach(function(done) {
      var useremail = new Date().getTime() + '@email.com'
      var user = new User({
        name: 'usuario',
        email: useremail,
        rfc: "0123456789012"
      });

      user.save().then(
        function(data) {
          newUser = data;
          var newProduct = new Product({
            name: "product name",
            price: 100.00,
            iva: 16,
            ieps: 10,
            currency: "NMX",
            user: newUser
          });

          newProduct.save().then(
            function(data) {
              product = data;
              done();
            }
          );
        }
      );
    });

    it("Should create a project for a user", function(done) {
      expect(product).not.toBe(null);
      done();
    });
  });
});
