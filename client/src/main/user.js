function User() {

  return {
    list: function() {
      var promise = new RSVP.Promise(function(resolve, reject) {
        $.getJSON('http://localhost:8080/tim-integradora/users.json').
        done(function(data) {
          resolve(data);
        }).fail(function(error){
          reject(error);
        })
      });

      return promise;
    },
    save: function(user) {
      var promise = new RSVP.Promise(function(resolve, reject) {
        $.post('http://localhost:8080/tim-integradora/users?format=json', user).
        done(function(data) {
          resolve(data);
        }).fail(function(error){
          reject(error);
        })
      });

      return promise;
    }
  }
}

