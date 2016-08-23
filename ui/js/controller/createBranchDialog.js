/**
 * Created by anczhang on 8/18/16.
 */
app.controller('createBranchDialog', function ($scope, $rootScope, $location, $mdDialog, $state, $http) {
    DialogService.call(this, $scope, $location, $mdDialog);

    $scope.major = 1;
    $scope.minor = 0;
    $scope.fix = 0;

    $scope.answer = function () {
        $mdDialog.hide($scope.major + "." + $scope.minor + "." + $scope.fix);
    }
})