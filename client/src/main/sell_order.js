function SellOrder(object) {

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
    var url = 'http://localhost:8080/tim-integradora/users/' + this.userId + '/sellOrders';
    var data = serialize(this);
    console.log(data);
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.ajax({
        type:'POST',
        contentType:'application/json',
        url:url,
        data:JSON.stringify(data)
      }).done(function(response) {
        console.log(response);
        resolve(new SellOrder(response));
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.list = function() {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.detail.integrated.id + '/sellOrders';
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

  return newObject;
}
