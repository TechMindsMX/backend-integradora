function DepositVoucher(object) {

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
    var depositVoucherData = { currency:'MX', totalAmount:1000, paymentType:'CHECK' }
    var url = 'http://localhost:8080/tim-integradora/users/' + this.user.id + '/depositVouchers';
    var promise = new RSVP.Promise(function(resolve, reject) {
      $.post(url, depositVoucherData).
      done(function(data) {
        resolve(new DepositVoucher(data));
      }).fail(function(error){
        reject(error);
      })
    });

    return promise;
  };

  newObject.list = function() {
    var url = 'http://localhost:8080/tim-integradora/users/' + this.detail.integrated.id + '/depositVouchers';
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
