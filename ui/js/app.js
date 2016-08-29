/**
 * Created by anczhang on 6/21/16.
 */
var app = angular.module('promise', ['ui.router', 'ngMaterial', 'hljs', 'ui.codemirror']);
app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('dashboard', {
            url: '',
            templateUrl: '../ui/html/dashboard.html',
            controller: 'dashboard',
            abstract: true
        })
        .state('dashboard.editor', {
            url: '/editor?doc',
            templateUrl: '../ui/html/editor.html',
            controller: 'editor',
            abstract: true
        })
        .state('dashboard.editor.api', {
            url: '/api',
            templateUrl: '../ui/html/editor.api.html',
            controller: 'editor.api'
        })
        .state('dashboard.editor.publish', {
            url: '/publish',
            templateUrl: '../ui/html/editor.publish.html',
            controller: 'editor.publish'
        })
        .state('dashboard.api', {
            url: '/api',
            templateUrl: '../ui/html/api.html',
            controller: 'api',
            abstract: true
        })
        .state('dashboard.api.interfaces', {
            url: '/interfaces',
            templateUrl: '../ui/html/api.interfaces.html',
            controller: 'api.interfaces'
        })
        .state('dashboard.project', {
            url: '/project',
            templateUrl: '../ui/html/project.html',
            controller: 'project'
        })
        .state('dashboard.createProject', {
            url: '/createProject',
            templateUrl: '../ui/html/createProject.html',
            controller: 'createProject'
        })
        .state('dashboard.me', {
            url: '/me',
            templateUrl: '../ui/html/me.html',
            controller: 'me'
        })
        .state('dashboard.me.project', {
            url: '/project',
            templateUrl: '../ui/html/myProject.html',
            controller: 'myProject'
        })
        .state('dashboard.me.docList', {
            url: '/document',
            templateUrl: '../ui/html/docList.html',
            controller: 'docList'
        })
        .state('404', {
            url: '/404',
            templateUrl: '404.html'
        })

    $urlRouterProvider
        .when('', '/signin')
        .when('/dashboard', '/dashboard/editor');
});
app.value('API_URL', 'http:/promise.blinkmind.cn/api/');


app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('pink', {
            'default': '400'
        })
        // If you specify less than all of the keys, it will inherit from the
        // default shades
        .accentPalette('blue', {
            'default': '800' // use shade 200 for default, and keep all other shades the same
        });
});

app.constant('requestMethods', [
    {name: 'GET', bodyEnabled: false},
    {name: 'POST', bodyEnabled: false},
    {name: 'PUT', bodyEnabled: false},
    {name: 'PATCH', bodyEnabled: false},
    {name: 'DELETE', bodyEnabled: false},
    {name: 'COPY', bodyEnabled: false},
    {name: 'HEAD', bodyEnabled: false},
    {name: 'OPTIONS', bodyEnabled: false},
    {name: 'LINK', bodyEnabled: false},
    {name: 'UNLINK', bodyEnabled: false},
    {name: 'PURGE', bodyEnabled: false},
    {name: 'LOCK', bodyEnabled: false},
    {name: 'UNLOCK', bodyEnabled: false},
    {name: 'PROPFIND', bodyEnabled: false},
    {name: 'VIEW', bodyEnabled: false}
]).constant('rawTypes', [
    {name: "text", value: "text"},
    {name: "text/plain", value: "text/plain"},
    {name: "application/json", value: "application/json"},
    {name: "application/javascript", value: "application/javascript"},
    {name: "application/xml", value: "application/xml"},
    {name: "text/xml", value: "text/xml"},
    {name: "text/html", value: "text/html"}
]).constant('bodyTypes', [
    {name: "form-data", field: "form-data"},
    {name: "x-www-form-urlencoded", field: "x-www-form-urlencoded"},
    {name: "binary", field: "binary"},
    {name: "raw", field: "raw"}
]).constant('fieldTypes', [
    {name: "byte", isFileField: false},
    {name: "short", isFileField: false},
    {name: "int", isFileField: false},
    {name: "long", isFileField: false},
    {name: "char", isFileField: false},
    {name: "float", isFileField: false},
    {name: "double", isFileField: false},
    {name: "boolean", isFileField: false},
    {name: "string", isFileField: false},
    {name: "date", isFileField: false},
    {name: "file", isFileField: true}
]).constant('versionTypes', [
    {name: "Pre Alpha"},
    {name: "Alpha"},
    {name: "Beta"},
    {name: "Release Candidate"},
    {name: "RTM"},
    {name: "GA"},
    {name: "Release"}
]);
