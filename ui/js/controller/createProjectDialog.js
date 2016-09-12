/**
 * Created by anczhang on 7/1/16.
 */
app.controller('createProjectDialog', function ($scope, $rootScope, $location, $state, $http, $parse, $mdDialog) {
    DialogService.call(this, $scope, $location, $mdDialog);

})