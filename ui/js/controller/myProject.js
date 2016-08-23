/**
 * Created by anczhang on 6/27/16.
 */
app.controller('myProject', function ($scope, $rootScope, $location, $state, $http, $parse, $mdDialog) {
    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = {
            "name": "铠狮网",
            "description": "铠狮网是一个汇集众多软件服务商的优秀案例展示平台，帮助客户通过在线搜索案例的方式寻找匹配的服务商，并在线完成项目实施管理的IT界B2B服务平台。"
        };

    $scope.data = data;

    $scope.menus = [{'name': '所有项目', 'class': 'mdi-album'},
        {'name': '公开', 'class': 'mdi-grade'},
        {'name': '私密', 'class': 'mdi-comment'},
        {'name': '回收站', 'class': 'mdi-account-box'}
    ]

    this.notificationsEnabled = true;
    this.toggleNotifications = function () {
        this.notificationsEnabled = !this.notificationsEnabled;
    };
    this.redial = function () {
        $mdDialog.show(
            $mdDialog.alert()
                .targetEvent(originatorEv)
                .clickOutsideToClose(true)
                .parent('body')
                .title('Suddenly, a redial')
                .textContent('You just called a friend; who told you the most amazing story. Have a cookie!')
                .ok('That was easy')
        );
        originatorEv = null;
    };
    this.checkVoicemail = function () {
        // This never happens.
    };

    $scope.showCreateProjectDialog = function (ev) {
        $mdDialog.show({
            controller: 'createProjectDialog',
            templateUrl: '../boat/html/createProjectDialog.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            targetEvent: ev,
            fullscreen: false
        }).then(function (answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });

    }
})