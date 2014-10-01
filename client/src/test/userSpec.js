describe("Users behavior", function() {

  it("Should get an empty user list", function() {
    var user = new User();
    user.list().then(
      function(data) {
        console.log(data.length);
        expect(10).toEqual(0);
      }
    );
  });

});