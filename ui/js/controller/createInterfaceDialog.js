/**
 * Created by anczhang on 7/27/16.
 */
app.controller('createInterfaceDialog', function ($scope, $rootScope, $location, $state, $http, $parse, $mdDialog, $timeout, $animateCss, requestMethods, rawTypes, bodyTypes, fieldTypes, dialogService, tabService, dialogEvent, args) {
    DialogService.call(this, $scope, $location, $mdDialog);

    $scope.requestMethods = requestMethods;
    $scope.formTitles = ["变量名", "类型", "示例", "备注"];
    $scope.formTitlesRequired = $scope.formTitles.push("必填");
    $scope.paramTabs = [{name: "path"}, {name: "query"}, {name: "matrix"}, {name: "header"}, {name: "cookie"}, {name: "body"}];
    $scope.bodyTypes = bodyTypes;
    $scope.rawTypes = rawTypes;
    $scope.fieldTypes = fieldTypes;

    $scope.editorOptions = {
        matchBrackets: true,
        autoCloseBrackets: true,
        mode: "application/ld+json",
        lineWrapping: true,
        lineNumbers: true
    };

    $scope.currentInterfaceIndex = 0;
    $scope.selectTab = function (tab, index, opts) {
        tabService.selectTab(tab, index, opts);
    }

    $scope.selectedIndex = args ? args.selectedIndex ? args.selectedIndex : 0 : 0;

    $scope.showBasicInfo = function (event) {
        $scope.selectTab($(event.target), 0);
        $scope.selectedIndex = 0;
    }

    $scope.showInterface = function (event, index) {
        $scope.selectInterface($(event.target), index);
    }

    $scope.selectInterface = function (tab, index) {
        $scope.selectTab(tab, index + 1);
        $scope.selectedIndex = index + 1;
        $scope.function.interfaces[$scope.currentInterfaceIndex].processing = false;
        $scope.currentInterfaceIndex = index;
        $scope.function.interfaces[index].processing = true;
        $timeout(function () {
            $scope.function.interfaces[index].processing = false;
        }, 1000);
    }

    $scope.switchParamTab = function (event, index) {
        $scope.selectTab($(event.target), index);
    }

    $scope.addParamItem = function (paramName, index) {
        if (index == $scope.function.interfaces[$scope.currentInterfaceIndex][paramName].length - 1)
            $scope.function.interfaces[$scope.currentInterfaceIndex][paramName].push({});
    }

    $scope.toggleRequired = function (paramName, index) {
        $scope.function.interfaces[$scope.currentInterfaceIndex][paramName][index].isRequired = !$scope.function.interfaces[$scope.currentInterfaceIndex][paramName][index].isRequired;
        $scope.addParamItem(paramName, index);
    }

    $scope.deleteParamItem = function (paramName, index) {
        $scope.function.interfaces[$scope.currentInterfaceIndex][paramName].splice(index, 1);
    }

    $scope.addBodyParamItem = function (paramName, index) {
        if (index == $scope.function.interfaces[$scope.currentInterfaceIndex].body[paramName].length - 1)
            $scope.function.interfaces[$scope.currentInterfaceIndex].body[paramName].push({});
    }

    $scope.toggleBodyRequired = function (paramName, index) {
        $scope.function.interfaces[$scope.currentInterfaceIndex].body[paramName][index].isRequired = !$scope.function.interfaces[$scope.currentInterfaceIndex].body[paramName][index].isRequired;
        $scope.addBodyParamItem(paramName, index);
    }

    $scope.deleteBodyParamItem = function (paramName, index) {
        $scope.function.interfaces[$scope.currentInterfaceIndex].body[paramName].splice(index, 1);
    }

    $scope.addInterface = function () {
        $scope.function.interfaces.push($scope.getFunction({
            name: "接口" + ($scope.function.interfaces.length + 1),
            processing: false
        }));

        $timeout(function () {
            $scope.selectInterface($('#paramTab>md-tabs md-tab-item').eq($scope.function.interfaces.length - 1), $scope.function.interfaces.length - 1);
        }, 0)
    }

    $scope.deleteInterface = function (event) {
        var confirm = $mdDialog.confirm()
            .title('确认')
            .textContent('确定要删除这个接口?')
            .targetEvent(dialogEvent)
            .ok('删除')
            .cancel('取消');
        $mdDialog.show(confirm).then(function () {
            $scope.selectTab($('#paramTab>md-tabs md-tab-item').eq($scope.selectedIndex - 1), $scope.selectedIndex - 1);
            $scope.function.interfaces.splice($scope.selectedIndex - 1, 1);
        }, function () {
            dialogService.createInterface(dialogEvent, {
                function: angular.copy($scope.function),
                selectedIndex: $scope.selectedIndex
            }, null, null);
        });
    }

    $scope.getFunction = function (func) {
        if (!func.interfaces)
            func.interfaces = [{}];
        var field = ["path", "query", "matrix", "form", "header", "cookie"];
        for (var m = 0; m < func.interfaces.length; m++) {
            var interface = func.interfaces[m];
            for (var i = 0; i < field.length; i++) {
                if (!interface[field[i]])
                    interface[field[i]] = [{}];
            }
            if (!interface.body)
                interface.body = {
                    type: 'form-data',
                    rawType: 'text',
                    binary: {
                        fileTypes: []
                    },
                    'form-data': [{}],
                    'x-www-form-urlencoded': [{}]
                };
        }
        return func;
    };

    $scope.function = $scope.getFunction({
        isPublic: true,
        interfaces: [{
            name: "接口1",
            processing: false
        }]
    });

    if (args)
        $scope.function = args.function;

    $timeout(function () {
        if ($scope.selectedIndex > 0)$scope.selectTab($('#paramTab>md-tabs md-tab-item').eq($scope.selectedIndex), $scope.selectedIndex, {skipAnimate: true});
    }, 0)
})