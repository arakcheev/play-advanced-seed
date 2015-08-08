// Karma configuration
// Generated on Sat Aug 08 2015 15:20:50 GMT+0300 (MSK)

module.exports = function (config) {
    config.set({

        // base path that will be used to resolve all patterns (eg. files, exclude)
        // path of ui module
        basePath: '../../',


        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['jasmine', 'requirejs'],


        // list of files / patterns to load in the browser
        files: [
            'src/test/test-main.js',
            'src/test/jsRoutesCap.js', //cap for jsRoutes. Test can be run separated of application.
            {pattern: 'src/test/assets/*Spec.js', included: false},
            {pattern: 'src/main/assets/*.js', included: false},
            {pattern: 'src/main/assets/**/*.js', included: false},
            {pattern: 'target/web/web-modules/main/webjars/lib/**/*.js', included: false}, //webjars
            {pattern: 'target/web/web-modules/main/webjars/lib/**/**/*.js', included: false} //webjars bootstrap
        ],


        // list of files to exclude
        exclude: [
            'src/main/assets/main.js', //exclude main.js require config
            'src/main/assets/**/main.js' //exclude all main.js files in modules
        ],


        // preprocess matching files before serving them to the browser
        // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
        preprocessors: {},


        // test results reporter to use
        // possible values: 'dots', 'progress'
        // available reporters: https://npmjs.org/browse/keyword/karma-reporter
        reporters: ['progress'],


        // web server port
        port: 9876,


        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,


        // start these browsers /'Chrome'
        // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
        browsers: ['PhantomJS'],

        phantomjsLauncher: {
            // Have phantomjs exit if a ResourceError is encountered (useful if karma exits without killing phantom)
            exitOnResourceError: true
        },


        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: true
    })
}
