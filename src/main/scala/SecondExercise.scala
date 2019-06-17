import java.security.MessageDigest

import scala.collection.Seq

object SecondExercise {

  class FoldRightWrapper(seq: Seq[Int]) {

    def sum: Int = seq.foldRight(0)((value, acc) => value + acc) // also could be (_ + _)

    def elem(index: Int): Option[Int] = {
      val (_, foundElement) = seq.reverse.foldRight((0: Int, None: Option[Int])) {
        case (value, (i, None)) if i == index => (i, Some(value))
        case (_, (i, Some(found)))            => (i, Some(found))
        case (_, (i, _))                      => (i + 1, None)
      }
      foundElement
    }

    def filter(items: Seq[Int]): Seq[Int] =
      seq.reverse.foldRight(Seq.empty[Int])((element, acc) => if (!items.contains(element)) acc :+ element else acc)

    def map[A](fn: Int => A): Seq[A] = seq.reverse.foldRight(Seq.empty[A])((element, acc) => acc :+ fn(element))
  }

  def main(args: Array[String]): Unit = {
    println("SUM:");
    {
      val x = new FoldRightWrapper(1 to 10)
      println(x.sum)
    }
    println("FIND:");
    {
      val x = new FoldRightWrapper(11 to 20)
      println(x.elem(6))
      println(x.elem(11))
    }
    println("FILTER");
    {
      val x = new FoldRightWrapper(1 to 10)
      println(x.filter(Seq(4, 6)))
    }
    println("MAP");
    {
      val x = new FoldRightWrapper(1 to 10)
      println(x.map(_.toString + "#"))
    }
  }
}
