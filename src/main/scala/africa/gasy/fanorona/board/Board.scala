package africa.gasy.fanorona.board

case class Board(myPieces: Long = Bits.INITIAL_TOP,
                 opponentPieces: Long = Bits.INITIAL_BOT,
                 midCapture: Boolean = false)
