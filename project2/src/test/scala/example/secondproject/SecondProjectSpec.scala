/**
 * @author Artem Arakcheev
 * @since 07.08.15
 */


package example.secondproject

import org.scalatest._

class SecondProjectSpec extends WordSpec with Matchers with OptionValues with Inside with Inspectors{

   "A Second project" should{
     "start with `worl`" in{
       SecondObject.world should startWith("worl")
     }
   }

 }