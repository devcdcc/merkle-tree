import org.scalatest._

class ThirdExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  var subject = new ThirdExercise()

  it should "be zero with only a strike" in {
    //given
    val strike01 = Seq(10)

    //when
    strike01.foreach(subject.roll)

    val score: Int = subject.score

    //then
    assert(score == 0)
  }

  it should "return 20 in first case" in {
    //given
    val spare02 = Seq(5, 5)

    //when
    spare02.foreach(subject.roll)
    val score: Int = subject.scoreAt(0)

    //then
    assert(score == 20)
  }
  it should "return 19 in second case" in {
    //given
    val open03 = Seq(9, 0)

    //when
    open03.foreach(subject.roll)
    val score: Int = subject.scoreAt(1)

    //then
    assert(score == 19)
  }
  it should "return 9 in third case" in {
    //when
    val score: Int = subject.scoreAt(2)

    //then
    assert(score == 9)
  }
  it should "return 48 as running score" in {
    //when
    val score: Int = subject.score

    //then
    assert(score == 48)
  }

  it should "return "

  it should "return 300 when receive only strikes" in {
    //give
    val throwList = (1 to 122).map(_ => 10)

    //when
    subject.clear
    throwList.foreach(subject.roll)
    val score: Int = subject.score

    //then
    assert(score == 300)
  }
}
