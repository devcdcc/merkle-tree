import scala.collection._
import mutable.ListBuffer

class FixedBuffer[A](max: Int) extends Iterable[A] {

  val list: ListBuffer[A] = ListBuffer()

  def append(elem: A): Iterator[A] = {
    if (list.size == max)
      list.trimStart(1)
    list.append(elem).iterator
  }

  def trimStart(n: Int): Unit              = list.trimStart(n)
  override def foreach[U](f: A => U): Unit = list.foreach(f)
  override def iterator: Iterator[A]       = list.iterator

}
