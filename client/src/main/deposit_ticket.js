function DepositTicket(object) {

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
    var depositTicketData = { currency:'MX', totalAmount:1000, paymentType:'CHECK' }
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/depositTickets';
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.post(url, depositTicketData).
      done(function(data) {
        resolve(new DepositTicket(data));
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.list = function() {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/depositTickets';
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
