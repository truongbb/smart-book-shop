(function () {
    'use strict';
    angular.module('smart_shop').config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'smart_shop',
            url: 'home',
            data: {},
            views: {
                'content@': {
                    templateUrl: 'app/components/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                // lazy load, i18n
            }
        });
        //   .state('student-add', {
        //     parent: 'student',
        //     url: '/add',
        //     data: {},
        //     views: {
        //         'content@': {
        //             templateUrl: 'app/components/student/student-form.html',
        //             controller: 'StudentFormController',
        //             controllerAs: 'vm'
        //         }
        //     },
        //     resolve: {
				// // lazy load, i18n
        //     }
        // }).state('student-update', {
        //     parent: 'student',
        //     url: 'update/:id',
        //     data: {},
        //     views: {
        //         'content@': {
        //             templateUrl: 'app/components/student/student-form.html',
        //             controller: 'StudentFormController',
        //             controllerAs: 'vm'
        //         }
        //     },
        //     resolve: {
				// // lazy load, i18n
        //     }
        // });
    }
})();