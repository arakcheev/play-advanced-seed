package controllers

/**
 * @author Artem Arakcheev
 * @since 07.08.15
 */

import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.Environment

import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.guice.GuiceApplicationBuilder

class ApplicationControllerSpec extends PlaySpec with Results with MockitoSugar {

  val injector = new GuiceApplicationBuilder()
    .in(Environment.simple())
    .build.injector

  "A REST API" should {
    val controller = injector.instanceOf[Application]

    "GET index page" in {
      val request = FakeRequest(controllers.routes.Application.index())

      val result = contentAsString(controller.index().apply(request))

      result must (include(example.firstproject.FirstProject.hello) and include(example.secondproject.SecondObject.world))
    }

    "GET js reverse routes" in {
      val request = FakeRequest("GET", "/jsroutes.js")

      val result = controller.jsRoutes().apply(request)

      status(result) must equal(200)

      contentType(result) must contain("text/javascript")
    }
  }
}