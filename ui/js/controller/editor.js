/**
 * Created by anczhang on 6/21/16.
 */
app.controller('editor', function ($scope, $rootScope, $location, $state, $http, $parse) {
    $scope.menus =
        [
            {'name': '接口', 'class': 'mdi-layers', state: "dashboard.editor.api"},
            {'name': '发布', 'class': 'mdi-publish', state: "dashboard.editor.publish"},
            {'name': '测试', 'class': 'mdi-developer-mode'},
        ]

    $scope.redirect = function (index) {
        $state.go($scope.menus[index].state);
    }

    $scope.menuIndex = 0;
})