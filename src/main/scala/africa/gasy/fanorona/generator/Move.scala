package africa.gasy.fanorona.generator

import africa.gasy.fanorona.board.Capture.Capture
import africa.gasy.fanorona.board.{Bits, Board, Capture}

/*
object Move {

  // scalastyle:off

  // The main routine to find sets of moves
  // nextElement then pulls off the moves from these sets one bit at a time.
  //
  // This is really not a subroutine but a coroutine: each time we call it we
  // want
  // to continue at the statement immediately after the point at which we
  // returned
  // from the previous call. Since Java doesn't have coroutines built-in, we
  // use1
  // moveSetIndex as a program counter, with a big switch statement marking
  // all the
  // different possible entry points, but (unlike the usual switch) falling
  // through
  // from case to case rather than having any break statements.
  def findNextSet(board: Board, ingressMoveSetIndex: Int = 0)
    : (Int, Int, Capture, Boolean, Long) /* moveSetIndex, captureType, madeCapture, shift, set */ = {
    var storedFrom: Long = 0
    var storedTo: Long = 0

    var from = storedFrom
    val to = storedTo

    // TODO: mutable variables

//    var captureType: Capture = Capture.FORWARD
//    var madeCapture: Boolean = false
    var moveSetIndex: Int = ingressMoveSetIndex
//    var shift = 0
//    var set: Long = 0

//    var movesV: Long = 0
    var movesH: Long = 0
    var movesS: Long = 0
    var movesB: Long = 0L // positions we can move to ignoring
    // capturability

    val target = board.opponentPieces

    (moveSetIndex += 1) match { // CASES 0-7: CAPTURES
      case 0 => // CAPTURE FORWARD VERTICALLY OR BACKWARD -VERTICALLY

        val shift = Bits.SHIFT_VERTICAL
        val captureType = Capture.FORWARD
        val movesV = (from & (to >>> shift)) | (to & (from >>> shift))
        val set = movesV & (target >>> 2 * shift)
        if (set != 0) {
//          shift = Bits.SHIFT_VERTICAL
          val madeCapture = true
          return (moveSetIndex, shift, captureType, madeCapture, set)
        }
        moveSetIndex += 1 // fall into...

      case 1 => // CAPTURE FORWARD HORIZONTALLY OR BACKWARD -HORIZONTALLY

        movesH = (from & (to >>> Bits.SHIFT_HORIZONTAL)) | (to & (from >>> Bits.SHIFT_HORIZONTAL))
        set = movesH & (target >>> 2 * Bits.SHIFT_HORIZONTAL)
        if (set != 0) {
          shift = Bits.SHIFT_HORIZONTAL
          madeCapture = true
          return
        }
        moveSetIndex += 1
      case 2 => // CAPTURE FORWARD SLANTLY OR BACKWARD -SLANTLY

        // TODO WHAT IS THIS?
//        storedFrom = from &= Bits.DIAGONAL
        movesS = (from & (to >>> Bits.SHIFT_SLANT)) | (to & (from >>> Bits.SHIFT_SLANT))
        set = movesS & (target >>> 2 * Bits.SHIFT_SLANT)
        if (set != 0) {
          shift = Bits.SHIFT_SLANT
          madeCapture = true
          return
        }
        moveSetIndex += 1
      case 3 => // CAPTURE FORWARD BACKSLANTLY OR BACKWARD -BACKSLANTLY

        movesB = (from & (to >>> Bits.SHIFT_BACKSLANT)) | (to & (from >>> Bits.SHIFT_BACKSLANT))
        set = movesB & (target >>> 2 * Bits.SHIFT_BACKSLANT)
        if (set != 0) {
          shift = Bits.SHIFT_BACKSLANT
          madeCapture = true
          return
        }
        moveSetIndex += 1
      case 4 => // CAPTURE FORWARD -VERTICALLY OR BACKWARD VERTICALLY

        captureType = Capture.BACKWARD
        set = movesV & (target << Bits.SHIFT_VERTICAL)
        if (set != 0) {
          shift = Bits.SHIFT_VERTICAL
          madeCapture = true
          return
        }
        moveSetIndex += 1
      case 5 => // CAPTURE FORWARD -HORIZONTALLY OR BACKWARD HORIZONTALLY
        set = movesH & (target << Bits.SHIFT_HORIZONTAL)
        if (set != 0) {
          shift = Bits.SHIFT_HORIZONTAL
          madeCapture = true
          return
        }
        moveSetIndex += 1
      case 6 => // CAPTURE FORWARD -SLANTLY OR BACKWARD SLANTLY
        set = movesS & (target << Bits.SHIFT_SLANT)
        if (set != 0) {
          shift = Bits.SHIFT_SLANT
          madeCapture = true
          return
        }
        moveSetIndex += 1
      case 7 => // CAPTURE FORWARD -BACKSLANTLY OR BACKWARD BACKSLANTLY
        set = movesB & (target << Bits.SHIFT_BACKSLANT)
        if (set != 0) {
          shift = Bits.SHIFT_BACKSLANT
          madeCapture = true
          return
        }
        moveSetIndex += 1
      // CASES 8-11: SHUFFLES
      case 8 => // VERTICAL SHUFFLE OR PASS

        if (board.midCapture) { // illegal to shuffle?
          captureType = Capture.PASS
          moveSetIndex = 11 // set up so next call to findset runs into

          // case 12
          set = 1 // return a set with one move in it

          return
        } else if (madeCapture) { // capture was forced?
          moveSetIndex = 11 // move to case 12, end of moves

          captureType = Capture.NO_MORE_MOVES
          return
        }
        captureType = Capture.NO
        set = movesV
        if (set != 0) {
          shift = Bits.SHIFT_VERTICAL
          return
        }
        moveSetIndex += 1
      case 9 => // HORIZONTAL SHUFFLE
        set = movesH
        if (set != 0) {
          shift = Bits.SHIFT_HORIZONTAL
          return
        }
        moveSetIndex += 1
      case 10 => // SLANT SHUFFLE
        set = movesS
        if (set != 0) {
          shift = Bits.SHIFT_SLANT
          return
        }
        moveSetIndex += 1
      case 11 => // BACKSLANT SHUFFLE
        set = movesB
        if (set != 0) {
          shift = Bits.SHIFT_BACKSLANT
          return
        }
        moveSetIndex += 1
      // CASE 12: RAN OUT OF SHUFFLES
      case 12 =>
        moveSetIndex -= 1 // stay in this case and always return zero

        captureType = Capture.NO_MORE_MOVES
        return
      case _ =>
        throw new IllegalArgumentException
    }
  }

}
 */
