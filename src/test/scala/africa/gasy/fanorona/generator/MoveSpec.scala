package africa.gasy.fanorona.generator

import africa.gasy.fanorona.board.{Bits, Board, Capture, ElementSet}
import org.scalatest._

class MoveSpec extends FlatSpec {

  behavior of "A Move Generator"

  it should "find next set" in {
    val board: Board = Board()

    // scalastyle:off

    assert(
      Move.findNextSet(ingressMoveSetIndex = -1,
                       storedFrom = 544790103982080L,
                       storedTo = 190316031L,
                       opponentPieces = 562399469895680L) === ElementSet(
        1,
        10,
        Capture.FORWARD,
        true,
        173352960)
    )
  }

}
