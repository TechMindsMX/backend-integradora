describe("Relationships behavior", function() {
  var partnership = null;

  describe("Adding new provider", function() {
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
          var newPartnership = new Relationship({
            tradeName:'MakingDevs',
            rfc:'makingdevs012',
            phone:'1234567890',
            type:'PROVIDER',
            name:'gamaliel',
            email:'gamaliel@makingdevs.com',
            user: newUser
          });

          newPartnership.save().then(
            function(data) {
              partnership = data;
              done();
            }
          );
        }
      );
    });

    it("Should create a product for a user", function(done) {
      expect(partnership).not.toBe(null);
      done();
    });
  });

  describe("Relation list behavior", function(done) {
    var partnerships = [];
    beforeEach(function(done) {
      partnership.list().then(
        function(data) {
          partnerships = data;
          done();
        }
      );
    });

    it("Should list all partnerships for user", function(done) {
      expect(partnerships.length).toBeGreaterThan(0);
      done();
    });
  });

  describe("Disabled behavior", function() {
    var partnerships = [];

    beforeEach(function(done) {
      partnership.delete().then(
        function() {
          partnership.list().then(
            function(data) {
              partnerships = data;
              done();
            }
          );
        }
      );
    });

    afterEach(function(done) {
      var newUser = new User(partnership.integrated);
      newUser.delete().then(function() {
        done();
      });
    });

    it("Should disable a product", function(done) {
      expect(partnerships.length).toBeGreaterThan(0);
      done();
    });
  });

});
