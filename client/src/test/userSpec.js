describe("Users behavior", function() {
  var newUser = null;

  describe("Saving an user", function() {
    beforeEach(function(done) {
      var user = new User();
      var useremail = new Date().getTime() + '@email.com'
      user.save({name: 'usuario', email: useremail}).then(
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

  describe("Disable behavior", function() {
    var newList = [new User()];

    beforeEach(function(done) {
      var user = new User();
      user.delete(newUser).then(
        function() {
          user.list().then(
            function(data) {
              newList = data;
              done();
            }
          );
        }
      );

    });

    it("Should disable an user", function(done) {
      expect(newList).toEqual([]);
      done();
    });
  });

});