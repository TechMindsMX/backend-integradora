function Project(object) {

  var newObject = {};

  for (var prop in object) {
    if (object.hasOwnProperty(prop)) {
      newObject[prop] = object[prop];
    }
  }

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
    var projectData = { name: this.name }
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/projects';
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.post(url, projectData).
      done(function(data) {
        resolve(new Project(data));
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.list = function() {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/projects';
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.getJSON(url).
      done(function(data) {
        resolve(data);
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.delete = function(user) {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/projects/' + this.id;
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.ajax({
        method: "DELETE",
        url:url
      }).done(function(response, status) {
        console.log(response);
        resolve(status);
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  return newObject;
}
