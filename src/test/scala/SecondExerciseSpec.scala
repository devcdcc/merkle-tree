import SecondExercise.FoldRightWrapper
import org.scalatest._

class SecondExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  val seq: scala.collection.Seq[Int] = 1 to 10
  val subject: FoldRightWrapper      = new FoldRightWrapper(seq)

  it should "sum using foldRight" in {
    assert(subject.sum == seq.sum)
  }

  it should "elem should find using foldRight" in {
    assert(subject.elem(3) == Option(seq(3)))
    assert(subject.elem(5) == Option(seq(5)))
  }

  it should "filter distinct should using foldRight" in {
    val elements = Seq(1, 5, 6)
    assert(subject.filter(elements) == seq.filterNot(elements.contains))
  }

  it should "map like seq.filter" in {
    @inline def mapFunction(int: Int) = int.toString + "#"
    assert(subject.map(mapFunction) == seq.map(mapFunction))
  }
}
