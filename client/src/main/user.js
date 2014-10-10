function User(object) {

  var newObject = {};

  for (var prop in object) {
    if (object.hasOwnProperty(prop)) {
      newObject[prop] = object[prop];
    }
  }

  newObject.list = function() {
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.getJSON('http://localhost:8080/tim-integradora/users').
      done(function(data) {
        resolve(data);
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  var serialize = function(object) {
    var data = {}
    var _self = object;
    Object.keys(_self).forEach(function(key) {
      if(typeof _self[key] !== 'function') {
        data[key] = _self[key];
      }
    });
    return data;
  };

  newObject.save = function() {
    var serializeObject = serialize(this);
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.post('http://localhost:8080/tim-integradora/users', serializeObject).
      done(function(data) {
        resolve(new User(data));
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.delete = function() {
    var url = "http://localhost:8080/tim-integradora/users/" + this.id;
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
  };

  return newObject;
}

