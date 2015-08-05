/**
 * @author Artem Arakcheev
 * @since 05.08.15
 */


package controllers

import play.api.mvc.{Action, Controller}
import example.firstproject.FirstProject.hello
import example.secondproject.SecondObject.world

class Application extends Controller{


  def index() = Action{ implicit req =>


    Ok(hello +" "+ world + " !")
  }
}
