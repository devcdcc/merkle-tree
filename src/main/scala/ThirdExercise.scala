import scala.collection.mutable

class ThirdExercise {

  def roll(pins: Int): Unit = this.synchronized {
    if (bufferPins.isEmpty) firstThrowHandler(pins)
    else secondThrowHandler(pins)
  }

  def scoreAt(frameNumber: Int): Int = {
    val calculation = for {
      frame  <- frames.lift(frameNumber)
      result <- calculateScore(frame, frameNumber)
    } yield result
    calculation.getOrElse(0)
  }

  def score: Int = (0 until currentFrame).map(scoreAt).sum

  //for testing only
  def clear = {
    frames.clear()
    clearBuffer
  }
  //Testing only
  def getFrames: Seq[FrameTrait] = frames.toList

  private def calculateScore(frameTrait: FrameTrait, frameIndex: Int): Option[Int] =
    frameTrait match {
      case StrikeFrame()    => calculateStrikeScore(frameTrait, frameIndex)
      case SpareFrame(_, _) => calculateSpareScore(frameTrait, frameIndex)
      case FaultFrame()     => Some(0)
      case OpenFrame(_, _)  => Some(frameTrait.simpleFrameScore)
    }
  private def calculateSpareScore(frameTrait: FrameTrait, frameIndex: Int): Option[Int] =
    frames
      .lift(frameIndex + 1)
      .flatMap(frame => frame.throwOne.map(one => one + frameTrait.simpleFrameScore))

  private def calculateStrikeScore(frameTrait: FrameTrait, frameIndex: Int): Option[Int] = {
    val (nextOne, nextTwo) = (frames.lift(frameIndex + 1), frames.lift(frameIndex + 2))
    (nextOne, nextTwo) match {
      case (None, _)                    => Some(0)
      case (Some(_: StrikeFrame), None) => Some(0)
      case (Some(strikeFrame: StrikeFrame), Some(otherFrame)) =>
        Some(frameTrait.simpleFrameScore + strikeFrame.simpleFrameScore + otherFrame.throwOne.getOrElse(0))
      case (Some(otherFrame), _) => Some(frameTrait.simpleFrameScore + otherFrame.simpleFrameScore)
    }
  }

  private def currentFrame = if (frames.length > 9) 10 else frames.length

  private def firstThrowHandler(pins: Int): Unit = pins match {
    case 10 => frames.addOne(StrikeFrame())
    case 0  => frames.addOne(FaultFrame())
    case _  => bufferPins = Some(pins)
  }

  private def secondThrowHandler(pins: Int): Unit = {
    val firstThrow = bufferPins.get
    validateMaxSimpleFrameScore(firstThrow + pins)

    if (firstThrow + pins == 10)
      frames.addOne(SpareFrame(Some(firstThrow), Some(pins)))
    else
      frames.addOne(OpenFrame(Some(firstThrow), Some(pins)))
    clearBuffer
  }

  private def clearBuffer =
    bufferPins = None

  private def validateMaxSimpleFrameScore(score: Int) =
    if (score > 10)
      throw new Exception("Error, simple frame can't be greater than 10")

  private val frames                  = mutable.ListBuffer.empty[FrameTrait]
  private var bufferPins: Option[Int] = None

}

trait FrameTrait {
  def throwOne: Option[Int]      = None
  def throwTwo: Option[Int]      = None
  def frameThrows: Iterable[Int] = throwOne ++ throwTwo
  def simpleFrameScore: Int      = frameThrows.sum
  if (simpleFrameScore > 10 || simpleFrameScore < 0)
    throw new Exception(s"Error, a frame can't be lower than 0 or greater than 10. Actual value:$simpleFrameScore")
}

case class FaultFrame() extends FrameTrait

trait NonFaultFrame extends FrameTrait

case class StrikeFrame() extends NonFaultFrame {
  override def throwOne = Some(10)
}

case class SpareFrame(override val throwOne: Option[Int], override val throwTwo: Option[Int]) extends NonFaultFrame {
  if (simpleFrameScore != 10)
    throw new Exception("Error, a spare frame can't be different to 10")
}

case class OpenFrame(override val throwOne: Option[Int], override val throwTwo: Option[Int]) extends NonFaultFrame {
  if (simpleFrameScore > 9)
    throw new Exception("Error, a open frame can't be greater than 9")
}
