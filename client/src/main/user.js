function User() {

  return {
    list: function() {
      var promise = new RSVP.Promise(function(resolve, reject) {
        $.getJSON('http://localhost:8080/tim-integradora/users').
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
        $.post('http://localhost:8080/tim-integradora/users', user).
        done(function(data) {
          resolve(data);
        }).fail(function(error){
          reject(error);
        })
      });

      return promise;
    },
    delete: function(user) {
      var url = "http://localhost:8080/tim-integradora/users/" + user.id
      var promise = new RSVP.Promise(function(resolve, reject) {
        $.ajax({
          method: "DELETE",
          url:url
        }).done(function(response, status) {
          resolve(status);
        }).fail(function(error){
          reject(error);
        })
      });

      return promise;
    }
  }
}

