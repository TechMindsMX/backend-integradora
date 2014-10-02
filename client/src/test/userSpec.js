describe("Users behavior", function() {

  describe("Saving an user", function() {
    var newUser = null;

    beforeEach(function(done) {
      var user = new User();
      user.save({name: 'usuario', email: 'usuario6@email.com'}).then(
        function(data) {
          newUser = data; // deserialize
          done();
        }
      );
    });

    it("Should create an user", function(done) {
      expect(newUser).not.toBe(null);
      done();
    });
  });

  describe("List behavior", function() {
    var list = [];

    beforeEach(function(done) {
      var user = new User();
      user.list().then(
        function(data) {
          list = data;
          done();
        }
        );
    });

    it("Should get a list of all users", function(done) {
      expect(list.length).toBeGreaterThan(0);
      done();
    });
  });

});