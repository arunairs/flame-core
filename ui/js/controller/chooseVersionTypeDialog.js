/**
 * Created by anczhang on 8/29/16.
 */
app.controller('chooseVersionTypeDialog', function ($scope, $rootScope, $location, $mdDialog, versionTypes) {
    DialogService.call(this, $scope, $location, $mdDialog);

    $scope.versionTypes = versionTypes;

    $scope.answer = function () {
        $mdDialog.hide($scope.version);
    }
})