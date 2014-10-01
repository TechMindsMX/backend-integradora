function User() {

  var list = null;

  console.log("before");
  $.getJSON('http://localhost:8090/tim-integradora/users.json', function(data) {
    console.log("response");
    list = data;
  });
  console.log("after");

  return {
    list: function() {
      console.log("current");
      return list;
    }
  }
}

