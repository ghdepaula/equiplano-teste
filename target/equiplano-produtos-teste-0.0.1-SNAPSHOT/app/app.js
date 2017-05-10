
var app = angular.module('equiplanoApp', ['ngRoute','angular-loading-bar']);

app.directive('ngMask', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {	
			
			var options = {};
			var mask = attrs.ngMask;
			var maskReverse = (attrs.ngMaskReverse === 'true');
			
			if(maskReverse){
				options.reverse = attrs.ngMaskReverse; 	
			}
			
			element.mask(mask, options);
		}
	};
});

app.directive('ngDatePicker', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			
			var dateFormat = attrs.ngDatePicker;
			
			if(!dateFormat){
				dateFormat = "dd/mm/yyyy";
			}
			
			var options = {
				format: dateFormat,
			    language: "pt-BR",
			    autoclose: true,
			    todayHighlight: true
			};
			
			element.datepicker(options);
		}
	};
});

app.directive('ngMonthYearPicker', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			
			var options = {
				format: "MM/yyyy",
			    startView: 1,
			    minViewMode: 1,
			    maxViewMode: 2,
			    language: "pt-BR",
			    autoclose: true
			};
			
			element.datepicker(options);
		}
	};
});

app.directive("ngShowModal", function ($parse) {
    return {
        restrict: "A",
        link: function (scope, element, attrs) {

            //Hide or show the modal
            scope.showModal = function (visible, elem) {
                if (!elem)
                    elem = element;
                if (visible)
                    $(elem).modal("show");                     
                else
                    $(elem).modal("hide");
            }

            //Watch for changes to the modal-visible attribute
            scope.$watch(attrs.ngShowModal, function (newValue, oldValue) {
                scope.showModal(newValue, attrs.$$element);
            });

            //Update the visible value when the dialog is closed through UI actions (Ok, cancel, etc.)
            $(element).bind("hide.bs.modal", function () {
                $parse(attrs.ngShowModal).assign(scope, false);
                if (!scope.$$phase && !scope.$root.$$phase)
                    scope.$apply();
            });
        }

    };
});

app.config(function($routeProvider, cfpLoadingBarProvider) {
	  
	$routeProvider.when('/produtos', {
	    templateUrl : 'views/produtos/produtos.html',
	    controller  : 'produtoController'
	})
	
	$routeProvider.when('/categorias', {
	    templateUrl : 'views/categorias/categorias.html',
	    controller  : 'categoriaController'
	})
	
	$routeProvider.otherwise({redirectTo: '/'});
	
    cfpLoadingBarProvider.includeSpinner = true;

	
});