(function () {
	'use strict';

	angular.module('equiplanoApp').factory('alertsService', alertsService);

	function alertsService() {
		
		var alertMessage = {};
		
		var _alertInfo = function (msg) {
			alertMessage.alertIcon = 'fa fa-info-circle text-info';
			alertMessage.typeAlert = 'alert-info';
			alertMessage.typeMessage = 'INFO';
			alertMessage.typeDescription = 'INFORMAÇÃO:';
			alertMessage.closable = true;
			alertMessage.message = msg;
			
			return alertMessage;
		}
		
		var _alertSuccess = function (msg) {
			alertMessage.alertIcon = 'fa fa-check text-success';
			alertMessage.typeAlert = 'alert-success';
			alertMessage.typeMessage = 'SUCCESS';
			alertMessage.typeDescription = 'SUCESSO:';
			alertMessage.closable = true;
			alertMessage.message = msg;
			
			return alertMessage;
		}
		
		var _alertWarning = function (msg) {
			alertMessage.alertIcon = 'fa fa-exclamation-triangle text-warning';
			alertMessage.typeAlert = 'alert-warning';
			alertMessage.typeMessage = 'WARNING';
			alertMessage.typeDescription = 'AVISO:';
			alertMessage.closable = false;
			alertMessage.message = msg;
			
			return alertMessage;
		}
		
		var _alertError = function (msg, status) {
			alertMessage.status = status;
			alertMessage.alertIcon = 'fa fa-close text-danger';
			alertMessage.typeAlert = 'alert-danger';
			alertMessage.typeMessage = 'ERROR';
			alertMessage.typeDescription = 'ERRO:';
			alertMessage.closable = false;
			alertMessage.message = msg;
			
			return alertMessage;
		}

		return {
			alertInfo : _alertInfo,
			alertSuccess : _alertSuccess,
			alertError : _alertError,
			alertWarning :_alertWarning,
		}
	}

})();