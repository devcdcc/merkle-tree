import scala.collection.mutable

class ThirdExercise {
  private val seq = mutable.ListBuffer.empty[Int]

  def roll(pins: Int*) : Unit = seq.addAll(pins)

  def score: Int = {
    val out = seq.sum
    out
  }

  /**
    * used only for testing purpose
    *
    */
  def clear =
    seq.clear()
}
