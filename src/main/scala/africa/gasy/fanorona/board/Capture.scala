package africa.gasy.fanorona.board

object Capture extends Enumeration {
  type Capture = Value
  val FORWARD, BACKWARD, NO, PASS, NO_MORE_MOVES = Value
}
