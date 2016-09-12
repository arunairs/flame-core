/**
 * Created by anczhang on 7/5/16.
 */
app.controller('api.interfaces', function ($scope, $rootScope, $location, $state, $http, $parse, $mdToast, dialogService) {
    hljs.initHighlightingOnLoad();

    var data = new Array();
    for (var i = 0; i < 10; i++)
        data[i] = {
            "http_methods": ['POST', 'PATCH']
        };

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

    var originatorEv;
    $scope.openMenu = function ($mdOpenMenu, ev) {
        originatorEv = ev;
        $mdOpenMenu(ev);

    };

    $scope.openCreateModuleDialog = function (ev) {
        dialogService.createModule(ev, function (answer) {

        }, function () {

        });

    }

    $scope.openEditModuleDialog = function (ev) {
        dialogService.editModule({name: "name"}, ev, function () {
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Simple Toast!')
                    .position("bottom right")
                    .hideDelay(3000)
            );
        }, function () {
        });
    }
})