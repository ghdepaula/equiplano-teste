(function () {
	'use strict';

	angular.module('equiplanoApp').controller('categoriaController', categoriaController);

	categoriaController.$inject = ['categoriaService', 'alertsService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function categoriaController(categoriaService, alertsService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		$scope.categorias = [];
		$scope.categoria;
		$scope.hideBtnSalvar = false;
		$scope.showAlert = false;
		$scope.alertMessage;

		$scope.findAll = function () {
			findAll();
		}
		
		$scope.loadForm = function (idCategoria) {
			
			cfpLoadingBar.start();
			
			categoriaService.findById(idCategoria).success(function (data) {
				
				$scope.categoria = data;
				$scope.hideBtnSalvar = true;
				
			});
			
			cfpLoadingBar.complete();

		}
		
		$scope.insert = function (categoria) {
			
			cfpLoadingBar.start();
			
			var msg;
			
			categoriaService.insert(categoria).success(function (data) {
				
				msg = alertsService.alertSuccess("Categoria cadastrada com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível cadastrar a categoria", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}

		$scope.update = function (categoria) {
			
			cfpLoadingBar.start();
			
			var msg;

			categoriaService.update(categoria).success(function (data) {
				
				msg = alertsService.alertSuccess("Categoria atualizada com sucesso !");
				showAlert(msg);
				clearData();;
			
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível atualizar o categoria", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}

		function findAll() {
			categoriaService.findAll().success(function(result){
				$scope.categorias = result;
			});
		}
		
		
		function clearData() {
			$scope.categoria = null;
			$scope.hideBtnSalvar = false;

			resetCategoria();
			findAll();
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetCategoria() {
			$('#descrCategoria').val('')
		}
		
		function showAlert(alertMessage) {
			$scope.alertMessage = alertMessage;
			$scope.showAlert = true;
			$window.scrollTo(0, 0);
			
			$timeout(function(){
				$scope.showAlert = false;
				$scope.alertMessage = null;
			}, 5000);
			
		}
		
	    $scope.start = function() {
	       cfpLoadingBar.start();
	    };

	    $scope.complete = function () {
	       cfpLoadingBar.complete();
	    };


	      // fake the initial load so first time users can see the bar right away:
	    $scope.start();
	    $scope.loadIntro = true;
	    $timeout(function() {
		    $scope.complete();
		    $scope.loadIntro = false;
	    }, 1250);
		
	}

})();