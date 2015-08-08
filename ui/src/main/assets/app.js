/**
 * The app module, as both AngularJS as well as RequireJS module.
 * Splitting an app in several Angular modules serves no real purpose in Angular 1.2.
 * (Hopefully this will change in the near future.)
 * Splitting it into several RequireJS modules allows async loading. We cannot take full advantage
 * of RequireJS and lazy-load stuff because the angular modules have their own dependency system.
 */
define(['angular', 'underscore', 'common'], function (angular, _) {
    'use strict';

    //application deps, for example app.common
    var appDependencies = [
        'app.common'
    ];

    var dependencies = _.union([
        //other angular dependencies
        "ui.bootstrap",
        "ngRoute"
    ], appDependencies);


    var app = angular.module('app', dependencies)
        .config(['$locationProvider', function ($locationProvider) {
            $locationProvider.html5Mode(true);
        }])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/notFound', {templateUrl: '/assets/templates/notFound.html'});
        }]);

    String.prototype.endsWith = function (suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };

    if (typeof String.prototype.startsWith != 'function') {
        String.prototype.startsWith = function (str) {
            return this.indexOf(str) === 0;
        };
    }

    app.config(['$logProvider', function ($logProvider) {
        $logProvider.debugEnabled(true);
    }]);

    //Example controller
    app.controller('AppCtrl', ['$scope', '$modal', function ($scope, $modal) {

        $scope.open = function () {
            $modal.open({
                templateUrl: '/assets/lib/client-side/templates/modal.html'
            });
        };

    }]);

    return app;

});
