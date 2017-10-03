/**
 * Created by azhang on 19/09/2017.
 */
app.controller('projectsController', ['$scope', '$http', function ($scope, $http) {
    var self = this;
    $scope.newProject;

    $scope.select;
    $scope.getAllResources = function(){
        var url = "/api/getallresources";
        console.log('get resources list');

        $http.get(url).then(function (response) {
            self.options.items = response.data;
            console.log(self.options.items)
        }, function (errResponse) {
            console.error('Error while get all resources : ' + errResponse.toString());
        });
    };

    $scope.showSelect = function(){

        console.log('check click function');
        $scope.select = self.options.selectedItems;
        console.log($scope.select);
    };

    $scope.getAllResources();
    $scope.selectedProject;
    self.options = {
        searchPlaceHolder: 'Typing Resources Name to filter.',
        labelAll: 'All Resources',
        labelSelected: 'Selected Resources',
        labelShow:'name',
        orderProperty: 'name',
        items:[],
        selectedItems: []
    };
    $scope.selectProject=function(project){
        console.log(project);
        $scope.selectedProject = angular.copy(project);

    };
    $scope.saveProject = function () {
        $scope.newProject.resources = self.options.selectedItems;
        console.log('resources: ' +  $scope.newProject.resources);
        var project = $scope.newProject;
        console.log('project');
        var url = "/api/createproject";
        var config = {
            headers: {
                'Content-Type': 'application/json;'
            }
        };
        $http.post(url, project, config).then(
            function (response) {
                console.log('upload project: ' + JSON.stringify(project));
                $scope.newProject={};
                self.options.selectedItems=[];
                $scope.getAllResources();
                $scope.getAllProjects();
            },
            function (errResponse) {
                console.error('Error while creating project: ' + errResponse.data.message);
            }
        );

    };

    $scope.getAllProjects = function () {
        var url = "/api/getallprojects";
        console.log('get projects list');
        $http.get(url).then(function (response) {
                $scope.lstProjects = response.data;
                console.log('project list:' + JSON.stringify($scope.lstProjects));
            }, function (errResponse) {
                console.error('Error while get all projects : ' + errResponse.data.message);
            }
        )
    };
    $scope.getAllProjects();
    $scope.removeProject=function(){
        console.log("selected id:" +  $scope.selectedProject.projectId);
        var url = "/api/removeproject/"+ $scope.selectedProject.projectId;
        console.log('remove selected project');

        $http.delete(url).then(
            function(response){
                $scope.getAllProjects();
            },
            function(errResponse){
                console.error('Error while deleting : ' + errResponse.toString());
            }
        )


    };

}]);