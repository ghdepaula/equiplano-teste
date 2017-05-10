(function () {
	'use strict';

	angular.module('equiplanoApp').factory('categoriaService', categoriaService);

	categoriaService.$inject = ['$http'];

	function categoriaService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:9090/equiplano/categorias');
		}
		
		var _insert = function (categoria) {
			return $http.post('http://localhost:9090/equiplano/categorias', categoria);	
		}
		
		var _update = function (categoria) {
			return $http.put('http://localhost:9090/equiplano/categorias', categoria);	
		}

		var _findById = function (idCategoria) {
			return $http.get('http://localhost:9090/equiplano/categorias/' + idCategoria);	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			insert : _insert,
			update : _update
		}
	}

})();