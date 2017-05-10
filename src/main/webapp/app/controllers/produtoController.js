(function () {
	'use strict';

	angular.module('equiplanoApp').controller('produtoController', produtoController);

	produtoController.$inject = ['produtoService', 'categoriaService', 'alertsService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function produtoController(produtoService, categoriaService, alertsService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		$scope.produtos = [];
		$scope.produto;
		$scope.categorias = [];
		$scope.filterProdutos = null;
		$scope.hideBtnSalvar = false;
		$scope.showAlert = false;
		$scope.alertMessage;

		$scope.findAll = function () {
			findAll();
		}
		
		function findAll() {
			produtoService.findAll().success(function(result){
				$scope.produtos = result;
			});
		}
		
		$scope.findAnexos = function(){
			findAnexos();
		}
		
		$scope.findCategorias = function(){
			findCategorias();
		}
		
		function findCategorias() {
			
			var msg;
			
			categoriaService.findAll().success(function(result){
				
				if(result.length > 0){
					$scope.categorias = result;
				}else{
					msg = alertsService.alertInfo("Nenhuma categoria cadastrada, realize o cadastro de uma categoria para cadastrar um novo produto.");
					showInfo(msg);
				}

			}).error(function(){
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível pesquisar as categorias", status);
				showAlert(msg);
			});
		}
		
		$scope.loadForm = function (idProduto) {
			
			cfpLoadingBar.start();
			
			produtoService.findById(idProduto).success(function (data) {
				
				$scope.produto = data;
				$scope.hideBtnSalvar = true;
				
			});
			
			cfpLoadingBar.complete();

		}
		
		$scope.onChangeFilterProdutos = function(){
			
			cfpLoadingBar.start();
			
			var descricao = $scope.filterProdutos;
			
			if(descricao){
				produtoService.findByDescricao(descricao).success(function (result) {
					$scope.produtos = result;
				});
			}else{
				findAll();
			}
			
			cfpLoadingBar.complete();
		}
		
		$scope.insert = function (produto) {
			
			cfpLoadingBar.start();
			
			var msg;
			
			produtoService.insert(produto).success(function (data) {
				
				msg = alertsService.alertSuccess("Categoria cadastrada com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível cadastrar a produto", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}

		$scope.update = function (produto) {
			
			cfpLoadingBar.start();
			
			var msg;

			produtoService.update(produto).success(function (data) {
				
				msg = alertsService.alertSuccess("Produto atualizado com sucesso !");
				showAlert(msg);
				clearData();;
			
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível atualizar o produto", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}
		
		$scope.remove = function (idProduto) {
			
			cfpLoadingBar.start();
			
			var msg;

			produtoService.remove(idProduto).success(function (data) {
				
				msg = alertsService.alertWarning("Produto foi excluído com sucesso !");
				showAlert(msg);
				clearData();
			
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível excluir o produto", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}
		
		function clearData() {
			$scope.produto = null;
			$scope.hideBtnSalvar = false;

			resetProduto();
			findAll();
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetProduto() {
			$('#descrProduto').val('');
			$('#qtdProduto').val('');
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
		
		function showInfo(infoMessage) {
			$scope.alertMessage = infoMessage;
			$scope.showAlert = true;
			$window.scrollTo(0, 0);
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