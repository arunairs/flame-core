/**
 * Created by anczhang on 6/21/16.
 */
app.controller('dashboard', function ($scope, $rootScope, $location, $state, $http) {

    $rootScope.showNavShadow = true;

    var originatorEv;
    $scope.openMenu = function ($mdOpenMenu, ev) {
        originatorEv = ev;
        $mdOpenMenu(ev);

    };
})