describe("Deposit tickets behavior", function() {
  var depositTicket = null;

  describe("Adding new deposit ticket", function() {
    beforeEach(function(done) {
      var useremail = new Date().getTime() + '@email.com'
      var user = new User({
        name: 'usuario',
        email: useremail,
        rfc: "0123456789012"
      });

      user.save().then(
        function(data) {
          var newUser = data;
          var newDepositTicket = new DepositTicket({
            currency:'MX',
            totalAmount:1000,
            paymentType:'CHECK',
            user: newUser
          });

          newDepositTicket.save().then(
            function(data) {
              depositTicket = data;
              done();
            }
          );
        }
      );
    });

    it("Should create a deposit ticket for a user", function(done) {
      expect(depositTicket).not.toBe(null);
      done();
    });
  });

  describe("Relation list behavior", function(done) {
    var depositTickets = [];
    beforeEach(function(done) {
      depositTicket.list().then(
        function(data) {
          depositTickets = data;
          done();
        }
      );
    });

    afterEach(function(done) {
      var newUser = new User(depositTicket.user);
      newUser.delete().then(function() {
        done();
      });
    });

    it("Should list all depositTickets for user", function(done) {
      expect(depositTickets.length).toBeGreaterThan(0);
      done();
    });
  });

});
