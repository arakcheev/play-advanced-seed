# play-advanced-seed
Advanced seed Play 2.4 application

About
-------------
This is sample web application using [Play 2.4][play] and custom build.
The web application depends on two projects `project1` , `project2`.
All javascript assets located in `ui` project and recompiled by play reload.

Usage
-------------
`sbt run` run play app on 9000 port

`sbt clean compile state` build application stage and resolve all webjars dependencies in `ui` project to play assets`

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
`sbt test` run all tests

`sbt coverage test` running tests and build coverage HTML report. For information about scala coverage plugin,
 please see [sbt-scoverage][sbt-scoverage].

Inspired by [play-angular-require-seed][p-a], [sbt-web][sbt-web].



[play]: http://playframework.com
[sbt-web]: https://github.com/sbt/sbt-web
[p-a]: https://github.com/mariussoutier/play-angular-require-seed
[sbt-scoverage]: https://github.com/scoverage/sbt-scoverage
