/**
 * @author Artem Arakcheev
 * @since 05.08.15
 */


package controllers

import com.google.inject.{Singleton, Inject}
import play.api.Logger
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import example.firstproject.FirstProject.hello
import example.secondproject.SecondObject.world
import play.api.routing.JavaScriptReverseRoute
import play.api.routing.JavaScriptReverseRouter

@Singleton
class Application @Inject() (wsClient: WSClient) extends Controller{

  private val logger = Logger("application")

  def index() = Action { implicit req =>
    logger.trace("Got index req")
    Ok(views.html.main(hello + " " + world + " !"))
  }

  private val routeCache: Seq[JavaScriptReverseRoute] = {
    val jsRoutesClasses = Seq(classOf[routes.javascript]) // TODO add your own packages
    jsRoutesClasses.flatMap { jsRoutesClass =>
      val controllers = jsRoutesClass.getFields.map(_.get(null))
      controllers.flatMap { controller =>
        controller.getClass.getDeclaredMethods.filter(_.getName != "_defaultPrefix").map { action =>
          action.invoke(controller).asInstanceOf[JavaScriptReverseRoute]
        }
      }
    }
  }

  def jsRoutes(varName: String = "jsRoutes") = Action { implicit request =>
    Ok(JavaScriptReverseRouter(varName)(routeCache: _*)).as(JAVASCRIPT)
  }
}
