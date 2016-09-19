/**
 * Created by anczhang on 6/21/16.
 */
app.controller('editor', function ($scope, $rootScope, $location, $state, $http, $parse, dialogService) {
    $scope.menus =
        [
            {'name': '接口', 'icon': 'image/icon/ic_layers_24px.svg', state: "dashboard.editor.api"},
            {'name': '发布', 'icon': 'image/icon/ic_publish_24px.svg', state: "dashboard.editor.publish"},
            {'name': '测试', 'icon': 'image/icon/ic_developer_mode_24px.svg'},
        ]

    $scope.redirect = function (index) {
        $state.go($scope.menus[index].state);
    }

    $scope.menuIndex = 0;

    $scope.openPublishDialog = function (event) {
        dialogService.publish(event, {}, function (answer) {
            alert(answer);
        }, function () {
        })
    }

    $scope.openCreateBranchDialog = function (event) {
        dialogService.createBranch(event, {}, function (answer) {
            alert(answer);
        }, function () {

        })
    }

    $scope.openChooseVersionTypeDialog = function (event) {
        dialogService.chooseVersionType(event, {}, function () {

        }, function () {

        })
    }

    $scope.openCreateInterfaceDialog = function (event) {
        dialogService.createInterface(event, null, function () {

        }, function () {

        });
    }

    $scope.openCreateModuleDialog = function (event) {
        dialogService.createModule(event, function () {

        }, function () {

        })
    }
})