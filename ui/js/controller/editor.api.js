/**
 * Created by anczhang on 7/15/16.
 */
app.controller('editor.api', function ($scope, $rootScope, $location, $state, $http, $parse, dialogService) {

    $scope.$parent.menuIndex = 0;

    hljs.initHighlightingOnLoad();

    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = {"text": "/api/template/{id}", "http_methods": ['POST', 'PATCH']};

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

    $scope.showCreateInterfaceDialog = function (event) {
        dialogService.createInterface(event, null, function () {

        }, function () {

        });
    }

})