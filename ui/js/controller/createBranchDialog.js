/**
 * Created by anczhang on 8/18/16.
 */
app.controller('createBranchDialog', function ($scope, $rootScope, $location, $mdDialog, versionTypes) {
    DialogService.call(this, $scope, $location, $mdDialog);

    $scope.versionTypes = versionTypes;

    $scope.version = {major: 1, minor: 0, revision: 0, type: $scope.versionTypes[2]};

    $scope.generateVersionName = function () {
        $scope.version.name = $scope.version.major + "." + $scope.version.minor + "." + $scope.version.revision + " " + $scope.version.type.name;
    }

    $scope.answer = function () {
        $mdDialog.hide($scope.version);
    }
})