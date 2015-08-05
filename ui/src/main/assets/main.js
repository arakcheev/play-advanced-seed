(function (requirejs) {
    'use strict';

    // -- RequireJS config --
    requirejs.config({
        // Packages = top-level folders; loads a contained file named 'main.js"
        packages: [],
        shim: {
            'jquery': {
                exports: 'jquery'
            },
            'jsRoutes': {
                deps: [],
                // it's not a RequireJS module, so we have to tell it what var is returned
                exports: 'jsRoutes'
            },
            // Hopefully this all will not be necessary but can be fetched from WebJars in the future
            'angular': {
                deps: ['jquery'],
                exports: 'angular'
            },
            'bootstrap': ['jquery'],
            'angular-route': ['angular'],
            'ui-bootstrap': {
                deps: ['angular'],
                exports: 'ui'
            },
            'ui-bootstrap-tpls': ['angular']
        },
        paths: {
            'requirejs': ['/assets/lib/requirejs/require.min'],
            'jquery': ['/assets/lib/jquery/jquery.min'],
            'angular': ['/assets/lib/angularjs/angular.min'],
            'angular-route': ['/assets/lib/angularjs/angular-route.min'],
            'bootstrap': ['/assets/lib/bootstrap/js/bootstrap.min'],
            'underscore': ['/assets/lib/underscorejs/underscore-min'],
            'ui-bootstrap': ['/assets/lib/angular-ui-bootstrap/ui-bootstrap-tpls.min'],
            'jsRoutes': ['/jsroutes']
        }
    });

    requirejs.onError = function (err) {
        console.error(err);
    };

    // Load the app. This is kept minimal so it doesn't need much updating.
    require(['angular',
            'angular-route',
            'jquery',
            'bootstrap',
            'underscore',
            'ui-bootstrap',
            './app'],
        function (angular) {
            angular.element(document).ready(function () {
                angular.bootstrap(document, ['app']);
            });
        }
    );
})(requirejs);