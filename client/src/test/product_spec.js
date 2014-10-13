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

    it("Should create a product for a user", function(done) {
      expect(product).not.toBe(null);
      done();
    });
  });

  describe("Product list behavior", function(done) {
    var products = [];
    beforeEach(function(done) {
      product.list().then(
        function(data) {
          products = data;
          done();
        }
      );
    });

    it("Should list all products for user", function(done) {
      expect(products.length).toBeGreaterThan(0);
      done();
    });
  });

  describe("Disabled behavior", function() {
    var products = [new Product()];

    beforeEach(function(done) {
      product.delete().then(
        function() {
          product.list().then(
            function(data) {
              products = data;
              done();
            }
          );
        }
      );
    });

    afterEach(function(done) {
      var newUser = new User(product.user);
      newUser.delete().then(function() {
        done();
      });
    });

    it("Should disable a product", function(done) {
      expect(products).toEqual([]);
      done();
    });
  });
});
