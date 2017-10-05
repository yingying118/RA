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
        .when('/projects',{
            templateUrl:"./js/projects/projects.html",
            controller:'projectsController as projectsCtrl'
        })
        .when(
            '/resources',{
                templateUrl:"./js/resources/resources.html",
                controller:'resourcesController'
            }
        )
        .otherwise({redirectTo: '/'});
});