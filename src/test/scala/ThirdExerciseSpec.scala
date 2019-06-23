import org.scalatest._
import scala.collection.mutable

class ThirdExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  val seq     = mutable.Seq.empty[Int]
  var subject = new ThirdExercise(seq)
  it should "a" in {

    //given

    //when
    val score: Int = subject.score

    //then
    assert(score == 30)
  }
}
