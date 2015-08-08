var allTestFiles = [];
var TEST_REGEXP = /(spec|test)\.js$/i;

// Get a list of all the test files to include
Object.keys(window.__karma__.files).forEach(function (file) {
    if (TEST_REGEXP.test(file)) {
        // Normalize paths to RequireJS module names.
        // If you require sub-dependencies of test files to be loaded as-is (requiring file extension)
        // then do not normalize the paths
        var normalizedTestModule = file.replace(/^\/base\/|\.js$/g, '');
        allTestFiles.push(normalizedTestModule);
    }
});

var webjarsPrefix = 'target/web/web-modules/main/webjars/lib';

require.config({
    // Karma serves files under /base, which is the basePath from your config file
    baseUrl: '/base',

    // dynamically load all test files
    deps: allTestFiles,

    // we have to kickoff jasmine, as it is asynchronous
    callback: window.__karma__.start,

    // Packages = top-level folders; loads a contained file named 'main.js"
    packages: [],
    shim: {
        'jquery': {
            exports: 'jquery'
        },
        // Hopefully this all will not be necessary but can be fetched from WebJars in the future
        'angular': {
            deps: ['jquery'],
            exports: 'angular'
        },
        'angularMocks': {
            deps: ['angular'],
            exports: 'angularMocks'
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
        'requirejs': [webjarsPrefix+'/requirejs/require.min'],
        'jquery': [webjarsPrefix+'/jquery/jquery.min'],
        'angular': [webjarsPrefix+'/angularjs/angular.min'],
        'angularMocks': webjarsPrefix+'/angular-mocks/angular-mocks',
        'angular-route': [webjarsPrefix+'/angularjs/angular-route.min'],
        'bootstrap': [webjarsPrefix+'/bootstrap/js/bootstrap.min'],
        'underscore': [webjarsPrefix+'/underscorejs/underscore-min'],
        'ui-bootstrap': [webjarsPrefix+'/angular-ui-bootstrap/ui-bootstrap-tpls.min'],
        'jsRoutes': ['src/test/jsRoutesCap']
    }
});
