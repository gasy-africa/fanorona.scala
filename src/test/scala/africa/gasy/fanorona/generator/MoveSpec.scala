package africa.gasy.fanorona.generator

import africa.gasy.fanorona.board.{Bits, Board, Capture, ElementSet}
import org.scalatest._
import flatspec._
import matchers._

class MoveSpec extends AnyFlatSpec with should.Matchers {

  "A Move Generator" should "find next set" in {
    val board: Board = Board()

    // scalastyle:off

    val myPieces = BigInt(List("0000",
                               "000000000",
                               "000000000",
                               "101001010",
                               "111101111",
                               "111101111").mkString,
                          2).toLong // 544790103982080L
    val opponentPieces = BigInt(List("0000",
                                     "000000000",
                                     "000000000",
                                     "101001010",
                                     "111111111",
                                     "111111111").mkString,
                                2).toLong // 562399469895680L

    val storedTo = Bits.ON_BOARD & ~(myPieces | opponentPieces)

    val nextSet: Long =
      BigInt(List("0000",
                  "000000000",
                  "101001010",
                  "010100101",
                  "000000000",
                  "000000000").mkString,
             2).toLong

    val elementSet = Move.findNextSet(ingressMoveSetIndex = -1,
                                      storedFrom = myPieces,
                                      storedTo = storedTo,
                                      opponentPieces = opponentPieces)
    println(elementSet)
    assert(true)
//    assert(
//      elementSet === ElementSet(1,
//                                shift = Bits.SHIFT_VERTICAL,
//                                Capture.FORWARD,
//                                true,
//                                nextSet)
//    )
  }

}
