describe("Users behavior", function() {

  it("Should get an empty user list", function() {
    var user = new User();
    var users = user.list();
    expect(users).toEqual([]);
  });

});