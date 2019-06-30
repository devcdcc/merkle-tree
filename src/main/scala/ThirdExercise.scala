import scala.collection.mutable

class ThirdExercise {
  private val seq = mutable.ListBuffer.empty[Int]
  private val bufferPins = new FixedBuffer[Int](2)

  def roll(pins: Int) : Unit = seq.addOne(pins)

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
