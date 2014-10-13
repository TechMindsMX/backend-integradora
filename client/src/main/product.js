function Product(object) {

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
    var productData = { currency: this.currency, ieps: this.ieps, iva: this.iva, name: this.name, price: this.price }
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/products';
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.post(url, productData).
      done(function(data) {
        resolve(new Product(data));
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.list = function() {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/products';
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

  newObject.delete = function() {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/products/' + this.id;
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
