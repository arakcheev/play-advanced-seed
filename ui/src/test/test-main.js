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

var webjarsBasePath = 'target/web/web-modules/main/webjars/lib';

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
        'requirejs': [webjarsBasePath+'/requirejs/require.min'],
        'jquery': [webjarsBasePath+'/jquery/jquery.min'],
        'angular': [webjarsBasePath+'/angularjs/angular.min'],
        'angularMocks': webjarsBasePath+'/angular-mocks/angular-mocks',
        'angular-route': [webjarsBasePath+'/angularjs/angular-route.min'],
        'bootstrap': [webjarsBasePath+'/bootstrap/js/bootstrap.min'],
        'underscore': [webjarsBasePath+'/underscorejs/underscore-min'],
        'ui-bootstrap': [webjarsBasePath+'/angular-ui-bootstrap/ui-bootstrap-tpls.min'],
        'jsRoutes': ['src/test/jsRoutesCap']
    }
});
