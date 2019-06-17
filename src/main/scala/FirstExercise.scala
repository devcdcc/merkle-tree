import scala.annotation.tailrec
import scala.collection.immutable.Seq
import java.security.MessageDigest

object FirstExercise {
  case class Node(someData: Seq[Byte])

  def hash(data: Seq[Byte]): Seq[Byte] =
    MessageDigest
      .getInstance("MD5")
      .digest(data.toArray[Byte])
      .toSeq
  def merkleRootHash(node: Node*): Seq[Byte] = ???

  def main(args: Array[String]): Unit =
    println(merkleRootHash(Node(Seq(1, 1)), Node(Seq(0, 8)), Node(Seq(7, 10)), Node(Seq(2, 3)), Node(Seq(1))))
  // Add more cases if needed
}
