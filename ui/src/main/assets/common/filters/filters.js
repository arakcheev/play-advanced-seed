/**
 * Created by artem on 08.08.15.
 */


define(['angular', './filterDep'], function (angular, filterDep) {

    //return function (number) {
    //    return filterDep(number);
    //};

    return angular.module('common.filters', []).filter('myFilter', function () {
        return function (number) {
            return filterDep(number);
        };
    });

});