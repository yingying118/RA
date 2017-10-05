(function () {
    'use strict';

    angular
        .module('resourceAllocationApp')
        .controller('projectsController', projectsController);

    projectsController.$inject = ['projectsService', 'resourcesService'];

    function projectsController(projectsService, resourcesService) {
        var self = this;

        self.projects = [];
        self.resources = [];

        self.newProject = null;
        self.selectedProject = null;

        self.resourcesSelectionTemplate = {
            searchPlaceHolder: 'Typing Resources Name to filter.',
            labelAll: 'All Resources',
            labelSelected: 'Selected Resources',
            labelShow: 'name',
            orderProperty: 'name',
            items: [],
            selectedItems: []
        };
        self.newProjectResourcesSelection = null;
        self.selectedProjectResourceSelection = null;

        getAllResources();
        getAllProjects();

        /**
         * Public functions
         */
        this.saveNewProject = createNewProject;
        this.resetNewProject = resetNewProject;
        this.selectProject = selectProject;
        this.deleteProject = deleteProject;

        function createNewProject() {
            self.newProject.resources = self.newProjectResourcesSelection.selectedItems;
            projectsService.createProject(self.newProject).then(
                function (response) {
                    resetNewProject();
                    getAllProjects();
                },
                function (errResponse) {
                    console.error('Error while creating project: ' + errResponse.data.message);
                }
            );
        };

        function resetNewProject() {
            self.newProject = {};
            self.newProjectResourcesSelection = angular.copy(self.resourcesSelectionTemplate);
            self.newProjectResourcesSelection.items = angular.copy(self.resources);
            self.newProjectResourcesSelection.selectedItems = [];
        }

        function selectProject(project) {
            self.selectedProject = project;
            self.selectedProjectResourceSelection = angular.copy(self.resourcesSelectionTemplate);
            self.selectedProjectResourceSelection.items =
                self.resources.filter(function (resource) {
                    var find = project.resources.findIndex(function(r) { return r.id === resource.id; });
                    return find == -1;
                });
            self.selectedProjectResourceSelection.selectedItems = angular.copy(project.resources);
        }

        function deleteProject() {
            projectsService.deleteProject(self.selectedProject).then(
                function (response) {
                    getAllProjects();
                },
                function (errResponse) {
                    console.error('Error while deleting : ' + errResponse.toString());
                }
            )
        };

        /**
         * Private functions
         */
        function getAllResources() {
            resourcesService.getResources().then(function (response) {
                self.resources = response.data;
                resetNewProject();
            }, function (errResponse) {
                console.error('Error while get all resources : ' + errResponse.toString());
            });
        }

        function getAllProjects() {
            projectsService.getProjects().then(function (response) {
                    self.projects = response.data;
                }, function (errResponse) {
                    console.error('Error while get all projects : ' + errResponse.data.message);
                }
            )
        }
    }
})();
