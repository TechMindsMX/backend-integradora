describe("Users behavior", function() {
  var newUser = null;

  describe("Saving an user", function() {
    beforeEach(function(done) {
      var useremail = new Date().getTime() + '@email.com'
      var user = new User({
        name: 'usuario',
        email: useremail,
        rfc: "0123456789012"
      });

      user.save().then(
        function(data) {
          newUser = data; // deserialize
          done();
        }
      );
    });

    it("Should create an user", function(done) {
      expect(newUser).not.toBe(null);
      expect(Object.keys(newUser).length).toEqual(8);
      expect(newUser.hasOwnProperty('id')).toBe(true);
      expect(newUser.hasOwnProperty('email')).toBe(true);
      expect(newUser.hasOwnProperty('enabled')).toBe(true);
      expect(newUser.hasOwnProperty('name')).toBe(true);
      expect(newUser.hasOwnProperty('rfc')).toBe(true);
      expect(newUser.hasOwnProperty('id')).toBe(true);
      done();
    });


    describe("Project behavior", function() {
      var newProject = {};

      beforeEach(function(done) {
        var project = new Project({
          name: "project name",
          user: newUser
        });

        project.save().then(
          function(data) {
            console.log("new project");
            newProject = data;
            done();
          }
        );
      });

      it("Should create a project for a user", function(done) {
        expect(newProject).not.toBe(null);
        done();
      });
    });
  });

  describe("List behavior", function() {
    var list = [];

    beforeEach(function(done) {
      var user = new User();
      user.list().then(
        function(data) {
          list = data.users;
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
              newList = data.users;
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
