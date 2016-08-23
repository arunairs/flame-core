/**
 * Created by anczhang on 7/16/16.
 */
app.controller('editor.property', function ($scope, $rootScope, $location, $state, $stateParams, $http, dialogService) {

    $scope.$parent.menuIndex = 1;

    hljs.initHighlightingOnLoad();

    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = new Object();

    $scope.data = data;

    $scope.toPrettyJSON = function (objStr, tabWidth) {
        try {
            var obj = $parse(objStr)({});
        } catch (e) {
            // eat $parse error
            console.log(e)
        }

        var result = JSON.stringify(obj, null, Number(tabWidth));
        return result;
    };

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
})