(function () {
    'use strict';

    angular
        .module('smart_shop')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('smart_shop', {
                url: '/',
                views: {
                    'content@': {
                        templateUrl: 'app/components/home/home.html',
                        controller: 'HomeController',
                        controllerAs: 'vm'
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }
})();