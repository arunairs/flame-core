/**
 * Created by anczhang on 7/16/16.
 */
app.controller('editor.publish', function ($scope, $rootScope, $location, $state, $stateParams, $http, dialogService, versionTypes) {

    $scope.$parent.menuIndex = 1;
    $scope.versionTypes = versionTypes;
    $scope.doc = {visibility: 'public'};

    var imagePath = "";
    $scope.editors = [
        {
            portrait: imagePath,
            name: 'Min Li Chan',
            email: 'Min Li Chan',
            datetime: '3:08PM',
        },
        {
            portrait: imagePath,
            name: 'Min Li Chan',
            email: 'Min Li Chan',
            datetime: '3:08PM',
        },
        {
            portrait: imagePath,
            name: 'Min Li Chan',
            email: 'Min Li Chan',
            datetime: '3:08PM',
        },
        {
            portrait: imagePath,
            name: 'Min Li Chan',
            email: 'Min Li Chan',
            datetime: '3:08PM',
        },
        {
            portrait: imagePath,
            name: 'Min Li Chan',
            email: 'Min Li Chan',
            datetime: '3:08PM',
        },
    ];

    $scope.statuses = [
        {
            key: "接口数",
            value: 343
        },
        {
            key: "访问次数",
            value: 75
        },
        {
            key: "最后更新",
            value: "2016.5.2"
        },
        {
            key: "最新版本",
            value: "1.2.1"
        }
    ];

    $scope.showPublishDialog = function (event) {
        dialogService.publish(event, {}, function (answer) {
            alert(answer);
        }, function () {
        })
    }

    $scope.showCreateBranchDialog = function (event) {
        dialogService.createBranch(event, {}, function (answer) {
            alert(answer);
        }, function () {

        })
    }

    $scope.showChooseVersionTypeDialog = function (event) {
        dialogService.chooseVersionType(event, {}, function () {

        }, function () {

        })
    }

    $scope.setVisibility = function (visibility) {
        $scope.doc.visibility = visibility;
    }
})