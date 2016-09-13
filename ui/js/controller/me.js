/**
 * Created by anczhang on 6/26/16.
 */
app.controller('me', function ($scope, $rootScope, $location, $state, $http, $parse, dialogService) {
    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = {
            "name": "铠狮网",
            "description": "铠狮网是一个汇集众多软件服务商的优秀案例展示平台，帮助客户通过在线搜索案例的方式寻找匹配的服务商，并在线完成项目实施管理的IT界B2B服务平台。"
        };

    $scope.data = data;

    $scope.menus =
        [
            {'name': '项目', 'icon': 'image/icon/ic_explore_24px.svg', state: '^.project'},
            {'name': '收藏', 'icon': 'image/icon/ic_grade_24px.svg', state: '^.favorite'},
            {'name': '反馈', 'icon': 'image/icon/ic_comment_24px.svg', state: '^.comment'},
            {'name': '设置', 'icon': 'image/icon/ic_settings_24px.svg', state: '^.setting'}
        ]

    $scope.menuIndex = 0;

    $scope.redirect = function (index) {
        $state.go($scope.menus[index].state);
    }

    $scope.openCreateProjectDialog = function (event) {
        dialogService.createProject(event, {}, function () {

        }, function () {

        })
    }

    $scope.openCreateDocumentDialog = function (event) {
        dialogService.createDocument(event, {}, function () {

        }, function () {

        })
    }
})