/**
 * Created by azhang on 19/09/2017.
 */
var app = angular.module('resourceAllocationApp', ['multiselectcomp', 'ngRoute']);

app.config(function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $routeProvider
        .when('/', {
            templateUrl:"overview",
            controller:'overviewController'
        })
        .when('/projects',{
            templateUrl:"projects",
            controller:'projectsController'
        })
        .when(
            '/resources',{
                templateUrl:"resources",
                controller:'resourcesController'
            }
        )
        .otherwise({redirectTo: '/'});
});