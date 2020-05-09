package africa.gasy.fanorona.board

import africa.gasy.fanorona.board.Capture.Capture

case class ElementSet(moveSetIndex: Int = -1,
                      shift: Int = -1,
                      captureType: Capture = Capture.NO_MORE_MOVES,
                      madeCapture: Boolean = false,
                      set: Long = 0)
