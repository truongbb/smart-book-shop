(function() {
	'use strict';

	angular.module('smart_shop').factory('HomeService', HomeService);

  HomeService.$inject = [ '$resource' ];

	function HomeService($resource) {
		var resourceUrl = '/home';
		return $resource(resourceUrl, {}, {
			// 'searchStudent': {
			// 	method: 'POST',
			// 	url: resourceUrl + '/search',
			// 	isArray: true
			// },
			// 'add': {
			// 	method: 'POST',
			// 	url: resourceUrl + '/add',
			// 	transformRequest: function (data) {
			// 		return angular.toJson(data);
			// 	}
			// },
			// 'delete': {
			// 	method: 'POST',
			// 	url: resourceUrl + '/delete',
			// 	transformRequest: function (data) {
			// 		return angular.toJson(data);
			// 	}
			// }
		});
	}
})();