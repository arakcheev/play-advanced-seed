# play-advanced-seed
Advanced seed Play 2.4 application

[![Build Status](https://travis-ci.org/arakcheev/play-advanced-seed.svg?branch=master)](https://travis-ci.org/arakcheev/play-advanced-seed)

About
-------------
This is sample web application using [Play 2.4][play] and custom build.
The web application depends on two projects `project1` , `project2`.
All javascript assets located in `ui` project and recompiled by play reload. The main goal of this project is to
separate ui and application, so to develop ui you no longer need run play application. All UI build on RequireJs,
AngularJs and located in `ui/src/main/assets`. WebJars uses for JS dependencies.


Usage
-------------
`sbt run` run play app on 9000 port

`sbt clean compile state` build application stage and resolve all webjars dependencies in `ui`project to play assets.
Now all assets in ui project will path througth pipelines `rjs`, `digets` and `gzip` so all js, css files will be minimized ang gziped.

File Directory Layout
---------------------
    + application ....(play 2.4 project)
    + project1 .... (project1 code)
    -+ src .....(sourceDirectory)
    --+ main
    ---+ scala .....(Scala source)
    ---+ java .....(Java source)
    --+ test
    ---+ scala .....(Scala tests)
    ---+ java .....(Java tests)
    + project2 .... (project2 code)
    -+ src .....(sourceDirectory)
    --+ main
    ---+ scala .....(Scala source)
    ---+ java .....(Java source)
    --+ test
    ---+ scala .....(Scala tests)
    ---+ java .....(Java tests)
    + ui ....(ui assets)
    -+ src .....(sourceDirectory)
    --+ main
    ---+ assets .....(js code)
    --+ test
    ---+ assets .....(js tests)

UI directory layout bases on [sbt-web][sbt-web] structure.

Testing
-------------
Due to the application and ui are separated, its give more capabilities for testing. This application support two types
of testing - test beck-end and front-end.
### UI testing
UI is the same basic part of the project, as well as backend. So, one of the goals of this project is to simple UI
development and testing. Project uses [Karma][karma] for testing js code. How to setup karma, please see [this].
 There ara two config files for karma, both located at `ui/src/test/`:

* `karma.conf.js` - Kama test configuration.
* `test-main.js` - resolve RequireJs deps for testing modules

To correctly resolve js libs, need to define path where they located, in this case - location of webjars
 (path relative to `ui` folder)

```javascript
   var webjarsBasePath = 'target/web/web-modules/main/webjars/lib';
```

and enable pattern in karma-config

```javascript

    {pattern: 'target/web/web-modules/main/webjars/lib/**/*.js', included: false}, //webjars
    {pattern: 'target/web/web-modules/main/webjars/lib/**/**/*.js', included: false} //webjars bootstrap
```

Test example

```javascript

    define(['angular', //resolve angular from test-main.js
            'src/main/assets/common/filters/filters', //load angular filter module
            'angularMocks'],
        function () {

            describe('myFilter', function () {

                // Here we register the function returned by the filer AMD module
               // `common.filters` - is the name of module in 'src/main/assets/common/filters/filters'
                beforeEach(module('common.filters'));

                it('should not be null', inject(function ($filter) {
                    //apply myFilter
                    expect($filter("myFilter")(2)).toEqual([0, 1, 2]);
                }));

            });
        }
    );
```

To run UI tests

    sbt karma

Note!! You may run UI tests using only karma

    karma start ui/src/tests/karma.conf.js

You may provide some options to karma. If options not set, default values are used. Currently for example:

    sbt karma -Dkarma=./node_modules/karma/bin/karma -Dkarma-opt="--log-level=debug"

but you need to download webjars dependencies or correct `webjarsBasePath`.

### Back-end testing
As usual. For example, to test application:

    >sbt
    [info] Set crrent project to Root (in build file: play-advanced-seed/)
    > project application
    [info] Set current project to application (in build file: play-advanced-seed/)
    [application] $ test

or  `sbt 'project application' test`, or:

     sbt test

To run all test, backend and UI:

     sbt test -Dkarma=./node_modules/karma/bin/karma -Dkarma-opt="--log-level=debug"

To running tests and build coverage HTML report.

    sbt coverage test

For information about scala coverage plugin, please see [sbt-scoverage][sbt-scoverage].




Inspired by [play-angular-require-seed][p-a], [sbt-web][sbt-web].



[play]: http://playframework.com
[sbt-web]: https://github.com/sbt/sbt-web
[p-a]: https://github.com/mariussoutier/play-angular-require-seed
[sbt-scoverage]: https://github.com/scoverage/sbt-scoverage
[karma]: http://karma-runner.github.io/
[this]: http://monicalent.com/blog/2015/02/11/karma-tests-angular-js-require-j/
