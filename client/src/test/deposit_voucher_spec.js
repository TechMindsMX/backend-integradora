describe("Deposit tickets behavior", function() {
  var depositVoucher = null;

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
          var newDepositVoucher = new DepositVoucher({
            currency:'MX',
            totalAmount:1000,
            paymentType:'CHECK',
            user: newUser
          });

          newDepositVoucher.save().then(
            function(data) {
              depositVoucher = data;
              done();
            }
          );
        }
      );
    });

    it("Should create a deposit ticket for a user", function(done) {
      expect(depositVoucher).not.toBe(null);
      done();
    });
  });

  describe("Relation list behavior", function(done) {
    var depositVouchers = [];
    beforeEach(function(done) {
      depositVoucher.list().then(
        function(data) {
          depositVouchers = data;
          done();
        }
      );
    });

    afterEach(function(done) {
      var newUser = new User(depositVoucher.detail.integrated);
      newUser.delete().then(function() {
        done();
      });
    });

    it("Should list all deposit voucher for user", function(done) {
      expect(depositVouchers.length).toBeGreaterThan(0);
      done();
    });
  });

});
