/**
 * @author Artem Arakcheev
 * @since 07.08.15
 */


package example.firstproject

import org.scalatest._

class FirstProjectSpec extends WordSpec with Matchers with OptionValues with Inside with Inspectors{

  "A First project" should{
    "equal hello value" in{
      FirstProject.hello should equal("Hello")
    }
  }

}
