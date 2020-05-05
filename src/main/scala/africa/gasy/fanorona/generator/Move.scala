package africa.gasy.fanorona.generator

import africa.gasy.fanorona.board.Capture.Capture
import africa.gasy.fanorona.board.{Bits, Board, Capture, ElementSet}

object Move {

  // scalastyle:off

  def findNextSet(ingressMoveSetIndex: Int = 0,
                  storedFrom: Long,
                  storedTo: Long,
                  opponentPieces: Long): ElementSet = {

    val from = storedFrom
    val to = storedTo

    // capturability

    val target = opponentPieces

    val moveSetIndex = ingressMoveSetIndex + 1 // CASES 0-7: CAPTURES

    (moveSetIndex) match {
      case 0 => // CAPTURE FORWARD VERTICALLY OR BACKWARD -VERTICALLY

        val shift = Bits.SHIFT_VERTICAL
        val captureType = Capture.FORWARD
        val movesV = (from & (to >>> shift)) | (to & (from >>> shift))
        val set = movesV & (target >>> 2 * shift)
        if (set != 0) {
          //          shift = Bits.SHIFT_VERTICAL
          ElementSet(moveSetIndex,
                     shift,
                     captureType,
                     madeCapture = true,
                     set = set)
        }
//        moveSetIndex += 1 // fall into...
        else
          return findNextSet(moveSetIndex + 1,
                             storedFrom,
                             storedTo,
                             opponentPieces)
        ElementSet(moveSetIndex + 1,
                   shift,
                   captureType,
                   madeCapture = true,
                   set = set)
    }
  }
}
