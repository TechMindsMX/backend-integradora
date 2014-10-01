function User() {

  return {
    list: function() {
      var promise = new RSVP.Promise(function(resolve, reject) {
        $.getJSON('http://localhost:8090/tim-integradora/users.json').
        done(function(data) {
          resolve([1,2,3,4]);
        }).fail(function(error){
          reject(error);
        })
      });

      return promise;
    }
  }
}

