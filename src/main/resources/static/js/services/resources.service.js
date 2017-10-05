(function() {
    'use strict';

    angular
        .module('resourceAllocationApp')
        .factory('resourcesService', resourcesService);

    resourcesService.$inject = ['$http'];

    function resourcesService($http) {
        var urlBase = "/api";
        var services = {
            getResources: getResources
        };

        return services;

        function getResources() {
            console.log('get resources list');
            var url = urlBase + '/getallresources';
            return $http.get(url);
        }
    }
})();