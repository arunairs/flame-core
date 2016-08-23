/**
 * Created by anczhang on 6/30/16.
 */
app.controller('createProject', function ($scope, $rootScope, $location, $state, $http, $parse) {
    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = {
            "name": "铠狮网",
            "description": "铠狮网是一个汇集众多软件服务商的优秀案例展示平台，帮助客户通过在线搜索案例的方式寻找匹配的服务商，并在线完成项目实施管理的IT界B2B服务平台。"
        };

    $scope.data = data;

})