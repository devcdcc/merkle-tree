import scala.collection.Seq

class SecondExercise(seq: Seq[Int]) {

  private def reverse = seq.foldRight(Seq.empty[Int])((element, acc) => acc :+ element)

  def sum: Int = seq.foldRight(0)((value, acc) => value + acc) // also could be (_ + _)

  def elem(index: Int): Option[Int] = {
    val (_, findResult) = reverse.foldRight((0: Int, None: Option[Int])) {
      case (_, (i, Some(found))) => (i, Some(found))
      case (value, (i, None)) =>
        if (i == index) (i, Some(value))
        else (i + 1, None)
    }
    findResult
  }

  def filter(items: Seq[Int]): Seq[Int] =
    reverse.foldRight(Seq.empty[Int])((element, acc) => if (!items.contains(element)) acc :+ element else acc)

  def map[A](fn: Int => A): Seq[A] = reverse.foldRight(Seq.empty[A])((element, acc) => acc :+ fn(element))
}
