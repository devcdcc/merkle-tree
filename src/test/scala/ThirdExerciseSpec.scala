import org.scalatest._

class ThirdExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  var subject = new ThirdExercise()
  it should "return 20 in first case" in {
    //given
    val strike01 = Seq(10)
    val spare02  = Seq(5, 5)
    val total1: Seq[Int] = strike01 :++ spare02

    //when
    total1.foreach(subject.roll)
    val score: Int = subject.score

    //then
    assert(score == 20)
  }
  it should "return 49 in second case" in {
    //given
    val open03   = Seq(9, 0)

    //when
    open03.foreach(subject.roll)
    val score: Int = subject.score

    //then
    assert(score == 19)
  }
}
