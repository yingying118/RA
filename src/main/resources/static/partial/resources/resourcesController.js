/**
 * Created by azhang on 19/09/2017.
 */
app.controller('resourcesController', ['$scope', '$http', function($scope, $http){

    $scope.newResource;
    $scope.selectedResource;
    $scope.saveResource = function(){
        var resource = $scope.newResource;
        var url = "/api/createresource";
        console.log('creating new resource');
        var config = {
            headers: {
                'Content-Type':'application/json;'
            }
        };
        $http.post(url, resource, config).then(
            function (response) {
                console.log(resource);
                $scope.newResource={};
                $scope.getAllResources();
            },
            function (errResponse) {
                console.error('Error while creating resource: ' + errResponse.data.message);
            }
        );
    };

    $scope.getAllResources = function () {
        var url = "/api/getallresources";

        $http.get(url).then(function (response) {
            $scope.lstResources = response.data;
            console.log('getAllResources:' + $scope.lstResources);
            }, function (errResponse) {
                console.error('Error while get all resources : ' + errResponse.toString());
            });
    };
    $scope.getAllResources();

    $scope.selectResource=function(resource){
        console.log(resource);
        $scope.selectedResource = angular.copy(resource);
    };

    $scope.removeResource=function(){
        console.log("selected id:" +  $scope.selectedResource.id);
        var url = "/api/removeresource/"+ $scope.selectedResource.id;
        console.log('remove selected resource');

        $http.delete(url).then(
            function(response){
                $scope.getAllResources();
            },
            function(errResponse){
                console.error('Error while deleting : ' + errResponse.toString());
            }
        )
    };
    $scope.updateResource=function(resource){
        console.log("selected id:" +  $scope.selectedResource.id);
        var url = "/api/update/"+ $scope.selectedResource.id;
        console.log('update selected resource');

        $http.put(url, resource).then(
            function(response){
                $scope.getAllResources();
            },
            function(errResponse){
                console.error('Error while updating user');
            }
        )

    }
}]);