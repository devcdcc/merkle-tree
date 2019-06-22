import SecondExercise.FoldRightContainer
import org.scalatest._

class SecondExerciseSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  val seq: scala.collection.Seq[Int] = 1 to 10
  val subject: FoldRightContainer    = new FoldRightContainer(seq)

  it should "sum must be equals to Seq.sum" in {
    assert(subject.sum == seq.sum)
  }

  it should "elem get same element inside seq" in {
    assert(subject.elem(3) == Option(seq(3)))
  }

  it should "elem return None when index is out" in {
    assert(subject.elem(11).isEmpty)
  }

  it should "filter works equals to Seq.filterNot" in {
    val elements = Seq(1, 5, 6)
    assert(subject.filter(elements) == seq.filterNot(elements.contains))
  }

  it should "map works like Seq.map" in {
    @inline def mapFunction(int: Int) = int.toString + "#"
    assert(subject.map(mapFunction) == seq.map(mapFunction))
  }
}
