package africa.gasy.fanorona.board

import africa.gasy.fanorona.board.Capture.Capture

case class ElementSet(moveSetIndex: Int,
                      shift: Int,
                      captureType: Capture,
                      madeCapture: Boolean,
                      set: Long)
