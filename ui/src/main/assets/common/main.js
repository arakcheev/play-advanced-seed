/**
 * Created by artem on 08.08.15.
 */

define(['angular',
        './services/playRoutes',
        './filters/filters'
    ],
    function (angular) {
        'use strict';

        return angular.module('app.common', [
            "common.playRoutes",
            'common.filters'
        ]);

    });