package africa.gasy.fanorona.generator

import africa.gasy.fanorona.board.{Bits, Board, Capture, ElementSet}
import org.scalatest._

class MoveSpec extends FlatSpec {

  behavior of "A Move Generator"

  it should "find next set" in {
    val board: Board = Board()

    // scalastyle:off

    val myPieces = 544790103982080L
    val opponentPieces = 562399469895680L
    val nextSet: Long =
      BigInt("0000000000000000000000000000000000001010010101010010100000000000",
             2).toLong

    assert(
      Move.findNextSet(ingressMoveSetIndex = -1,
                       storedFrom = myPieces,
                       storedTo = Bits.ON_BOARD & ~(myPieces | opponentPieces),
                       opponentPieces = opponentPieces) ===
        ElementSet(1,
                   shift = Bits.SHIFT_VERTICAL,
                   Capture.FORWARD,
                   true,
                   nextSet)
    )
  }

}
