describe("Project behavior", function() {
  var project = null;

  describe("Adding new project", function() {
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
          var newProject = new Project({
            name: "project name",
            user: newUser
          });

          newProject.save().then(
            function(data) {
              project = data;
              done();
            }
          );
        }
      );
    });
    it("Should create a project for a user", function(done) {
      expect(project).not.toBe(null);
      done();
    });
  });

  describe("Project list behavior", function(done) {
    var projects = [];
    beforeEach(function(done) {
      project.list().then(
        function(data) {
          projects = data;
          done();
        }
      );
    });

    it("Should list all projects for user", function(done) {
      expect(projects.length).toBeGreaterThan(0);
      done();
    });
  });

  describe("Disabled behavior", function() {
    var projects = [new Project()];

    beforeEach(function(done) {
      project.delete().then(
        function() {
          project.list().then(
            function(data) {
              projects = data;
              done();
            }
          );
        }
      );
    });

    afterEach(function(done) {
      var newUser = new User(project.user);
      newUser.delete().then(function() {
        done();
      });
    });

    it("Should disable a project", function(done) {
      expect(projects).toEqual([]);
      done();
    });
  });

});
