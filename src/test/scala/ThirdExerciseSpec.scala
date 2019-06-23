import org.scalatest._
import scala.collection.mutable

class ThirdExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  var subject = new ThirdExercise()
  it should "a" in {
    //given
    subject.roll(10, 5, 5)

    //when
    val score: Int = subject.score

    //then
    assert(score == 20)
  }
}
