package africa.gasy.fanorona.generator

import africa.gasy.fanorona.board.Capture.Capture
import africa.gasy.fanorona.board.{Bits, Board, Capture, ElementSet}

object Move {

  // scalastyle:off

  def findNextSet(ingressMoveSetIndex: Int = 0,
                  storedFrom: Long,
                  storedTo: Long,
                  opponentPieces: Long,
                  es: ElementSet = ElementSet()): ElementSet = {

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
                             opponentPieces,
                             es)
        ElementSet(moveSetIndex + 1,
                   shift,
                   captureType,
                   madeCapture = true,
                   set = set)
      case _ =>
        es
    }
  }

  // find next move in sequence by pulling bits out of set
  def nextElement(ingressMoveSetIndex: Int,
                  ingressSet: Long,
                  shift: Int,
                  capture: Capture,
                  storedFrom: Long,
                  storedTo: Long,
                  opponentPieces: Long): (Long, Long) = {
    if (ingressSet == 0)
      findNextSet(ingressMoveSetIndex,
                  storedFrom = storedFrom,
                  storedTo = storedTo,
                  opponentPieces) // make sure we have a move to generate
    // TODO imperative code
    var bit = Bits.lastBit(ingressSet)
    val set = ingressSet ^ bit
    capture match {
      case Capture.FORWARD =>
        // TODO imperative code
        var retval = bit | (bit << shift)
        bit <<= 2 * shift
        while ({
          (bit & opponentPieces) != 0
        }) {
          retval |= bit
          bit <<= shift
        }
        (set, retval)
      case Capture.BACKWARD =>
        // TODO imperative code
        var retval1 = bit | (bit << shift)
        bit >>>= shift
        while ({
          (bit & opponentPieces) != 0
        }) {
          retval1 |= bit
          bit >>>= shift
        }
        (set, retval1)
      case Capture.NO =>
        (set, bit | (bit << shift))
      case Capture.PASS =>
        (set, 0)
      case Capture.NO_MORE_MOVES =>
        (set, -1L)
    }
  }
}
