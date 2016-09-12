/**
 * Created by anczhang on 9/3/16.
 */
app.controller('createDocumentDialog', function ($scope, $rootScope, $location, $mdDialog, documentTypes) {
    DialogService.call(this, $scope, $location, $mdDialog);

    $scope.documentTypes = documentTypes;

    $scope.answer = function () {
        $mdDialog.hide($scope.version);
    }
})