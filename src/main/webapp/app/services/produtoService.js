(function () {
	'use strict';

	angular.module('equiplanoApp').factory('produtoService', produtoService);

	produtoService.$inject = ['$http'];

	function produtoService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:9090/equiplano/produtos');
		}
		
		var _insert = function (produto) {
			return $http.post('http://localhost:9090/equiplano/produtos', produto);	
		}
		
		var _update = function (produto) {
			return $http.put('http://localhost:9090/equiplano/produtos', produto);	
		}
		
		var _remove = function (idProduto) {
			return $http.delete('http://localhost:9090/equiplano/produtos/' + idProduto);	
		}

		var _findById = function (idProduto) {
			return $http.get('http://localhost:9090/equiplano/produtos/' + idProduto);	
		}
		
		var _findByDescricao = function (descrProduto) {
			return $http.get('http://localhost:9090/equiplano/produtos/descricao/' + descrProduto);	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			findByDescricao: _findByDescricao,
			insert : _insert,
			update : _update,
			remove : _remove
		}
	}

})();