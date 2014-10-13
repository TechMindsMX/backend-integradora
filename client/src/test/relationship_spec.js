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

});
