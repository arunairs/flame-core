/**
 * Created by anczhang on 7/10/16.
 */
function DialogService($scope, $location, $mdDialog) {
    $scope.cancel = function () {
        $mdDialog.cancel();
    }
}

app.factory("dialogService", function ($rootScope, $http, $mdDialog) {

    var service = {};

    // service.createModule = function (ev, answered, cancelled) {
    //     $mdDialog.show({
    //         controller: 'createModuleDialog',
    //         templateUrl: '../boat/html/createProjectDialog.html',
    //         parent: angular.element(document.body),
    //         clickOutsideToClose: true,
    //         targetEvent: ev,
    //         fullscreen: false
    //     }).then(function (answer) {
    //         if (answered)
    //             answered(answer);
    //     }, function () {
    //         if (cancelled)
    //             cancelled()
    //     })
    // }

    service.createModule = function (ev, answered, cancelled) {
        var confirm = $mdDialog.prompt()
            .title('创建一个模块')
            .textContent('名称不能重复')
            .placeholder('模块名称')
            .ariaLabel('Module Name')
            //.initialValue('Buddy')
            .targetEvent(ev)
            .clickOutsideToClose(true)
            .ok('确定')
            .cancel('取消');
        $mdDialog.show(confirm).then(function (answer) {
            if (answered)
                answered(answer);
        }, function () {
            if (cancelled)
                cancelled()
        });
    }

    service.editModule = function (module, ev, confirmed, cancelled) {
        var confirm = $mdDialog.prompt()
            .title('重命名模块')
            .textContent('名称不能重复')
            .placeholder('新的模块名称')
            .ariaLabel('Module Name')
            .initialValue(module.name)
            .targetEvent(ev)
            .clickOutsideToClose(true)
            .ok('确定')
            .cancel('取消');
        $mdDialog.show(confirm).then(function () {
            if (confirmed)
                confirmed();
        }, function () {
            if (cancelled)
                cancelled()
        });
    }

    service.createInterface = function (ev, args, answered, cancelled) {
        $mdDialog.show({
            controller: 'createInterfaceDialog',
            templateUrl: '../ui/html/createInterfaceDialog.html',
            parent: angular.element(document.body),
            clickOutsideToClose: false,
            targetEvent: ev,
            fullscreen: false,
            locals: {dialogEvent: ev, args: args}
        }).then(function (answer) {
            if (answered)
                answered(answer);
        }, function () {
            if (cancelled)
                cancelled()
        })
    }

    service.createBranch = function (ev, args, answered, cancelled) {
        $mdDialog.show({
            controller: 'createBranchDialog',
            templateUrl: '../ui/html/createBranchDialog.html',
            parent: angular.element(document.body),
            clickOutsideToClose: false,
            targetEvent: ev,
            fullscreen: false,
            locals: {dialogEvent: ev, args: args}
        }).then(function (answer) {
            if (answered)
                answered(answer);
        }, function () {
            if (cancelled)
                cancelled()
        })
    }

    service.chooseVersionType = function (ev, args, answered, cancelled) {
        $mdDialog.show({
            controller: 'chooseVersionTypeDialog',
            templateUrl: '../ui/html/chooseVersionTypeDialog.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            targetEvent: ev,
            fullscreen: false,
            locals: {dialogEvent: ev, args: args}
        }).then(function (answer) {
            if (answered)
                answered(answer);
        }, function () {
            if (cancelled)
                cancelled()
        })
    }

    service.publish = function (ev, args, confirmed, cancelled) {
        var confirm = $mdDialog.confirm()
            .title('确认')
            .textContent('是否要发布文档? 一经发布无法在原文档上再次修改。')
            .targetEvent(ev)
            .ok('发布')
            .cancel('取消');
        $mdDialog.show(confirm).then(function () {
            if (confirmed)
                confirmed();
        }, function () {
            if (cancelled)
                cancelled()
        });
    }
    return service;
})