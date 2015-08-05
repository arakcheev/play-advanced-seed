/**
 * @author Artem Arakcheev
 * @since 05.08.15
 */


package controllers

import play.api.mvc.{Action, Controller}
import example.firstproject.FirstProject.hello
import example.secondproject.SecondObject.world
import play.api.routing.JavaScriptReverseRoute
import play.api.routing.JavaScriptReverseRouter

class Application extends Controller{


  def index() = Action{ implicit req =>
    Ok(views.html.main(hello +" "+ world + " !"))
  }

  val routeCache: Seq[JavaScriptReverseRoute] = {
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
