import org.scalatest._

class ThirdExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  var subject = new ThirdExercise()

  val strike01 = Seq(10)
  val spare02  = Seq(5, 5)
  val open03   = Seq(9, 0)
  it should "return 20 in first case" in {
    //given
    val total1: Seq[Int] = strike01 :+ spare02

    //when
    subject.roll(total1: _*)
    val score: Int = subject.score

    //then
    assert(score == 20)
  }
}
