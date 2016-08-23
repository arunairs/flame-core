/**
 * Created by anczhang on 7/5/16.
 */
app.controller('api', function ($scope, $rootScope, $location, $state, $http, $parse) {

    $rootScope.showNavShadow = true;
    
    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = {
            "name": "铠狮网",
            "description": "铠狮网是一个汇集众多软件服务商的优秀案例展示平台，帮助客户通过在线搜索案例的方式寻找匹配的服务商，并在线完成项目实施管理的IT界B2B服务平台。"
        };

    $scope.data = data;

    $scope.menus =
        [
            {'name': '接口', 'class': 'mdi-layers'},
            {'name': '文档', 'class': 'mdi-folder-open'},
            {'name': '版本对比', 'class': 'mdi-history'},
            {'name': '反馈', 'class': 'mdi-forum'}
        ]

})