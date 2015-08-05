import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys
import com.typesafe.sbt.rjs.Import.RjsKeys._
import com.typesafe.sbt.rjs.Import.RjsKeys
import com.typesafe.sbt.rjs.Import._
import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._
import com.typesafe.sbt.web.js.JS

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

pipelineStages := Seq(rjs, digest, gzip)

// RequireJS with sbt-rjs (https://github.com/sbt/sbt-rjs#sbt-rjs)
// ~~~
RjsKeys.paths += ("jsRoutes" -> ("/jsroutes" -> "empty:"))

buildProfile := JS.Object("locale" -> "ru-ru")


