/**
 * Created by artem on 08.08.15.
 */


define(["underscore"], function (_) {

    return function (n) {
        return _.filter([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], function (number) {
            return number <= n;
        });
    };

});