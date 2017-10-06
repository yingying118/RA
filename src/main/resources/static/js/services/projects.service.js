(function() {
    'use strict';

    angular
        .module('resourceAllocationApp')
        .factory('projectsService', projectsService);

    projectsService.$inject = ['$http'];

    function projectsService($http) {
        var urlBase = "/api";
        var services = {
            getProjects: getProjects,
            createProject: createProject,
            deleteProject: deleteProject
        };

        return services;

        function getProjects() {
            console.log('get projects');
            var url = urlBase + '/getallprojects';
            return $http.get(url);
        }

        function createProject(project) {
            console.log('create project');
            var url = urlBase + '/createproject';
            var config = {
                headers: {
                    'Content-Type': 'application/json;'
                }
            };
            return $http.post(url, project, config);
        }

        function deleteProject(project) {
            console.log("delete project " + project.projectId);
            var url = urlBase + '/removeproject/' + project.projectId;

            return $http.delete(url);
        }
    }
})();