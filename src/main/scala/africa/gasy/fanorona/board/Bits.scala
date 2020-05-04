package africa.gasy.fanorona.board

// Game of Fanorona
// David Eppstein, UC Irvine, 11 Jun 1997
//
// Bit manipulation
//
// We represent Fanorona positions using two bitboards (64-bit longs)
// one for the pieces of each player.  The bits in the bitboard form
// six 10-bit groups: an empty group followed by one for each row of the board.
// Each row has an empty bit followed by a bit for each position in the row.
// The empty bits help avoid extra range checking while finding captures.
//
// The top four bits are free for other purposes; we use one
// to determine which color pieces are on each side, and one to determine
// whether the side on move's most recent move was a capture.
//
// This class is not instantiable; instead it consists of constants
// for various board configurations, used in game setup, move generation,
// and evaluation.

// The original version presented the Bit Constants in Octal then decimal
// the following is a binary representation which helps avoiding scratching too much head

object Bits {
  val IS_WHITE: Long = BigInt(
    "0100000000000000000000000000000000000000000000000000000000000000",
    2).toLong // 1", 2).toLong << 62
  val CAPTURED: Long = BigInt(
    "1000000000000000000000000000000000000000000000000000000000000000",
    2).toLong // 1L << 63; sign

  // bit speeds
  // tests
  // BINARY Annotation
  // 5 rows (numbers) 9 columns (letters)
  // C(capt) W(White) x, numbers(Empty)
  // CWxxxxxxxxxxxx5abcdefghi4abcdefghi3abcdefghi2abcdefghi1abcdefghi
  val INITIAL_BOT: Long = BigInt(
    "0000000000000000000000000000000000001010010101111111110111111111",
    2).toLong
  val INITIAL_TOP: Long = BigInt(
    "0000000000000001111111110111111111010100101000000000000000000000",
    2).toLong
  val TOP_ROW: Long = BigInt(
    "0000000000000001111111110000000000000000000000000000000000000000",
    2).toLong
  val BOTTOM_ROW: Long = BigInt(
    "0000000000000000000000000000000000000000000000000000000111111111",
    2).toLong
  val LEFT_COL: Long = BigInt(
    "0000000000000001000000000100000000010000000001000000000100000000",
    2).toLong
  val RIGHT_COL: Long = BigInt(
    "0000000000000000000000010000000001000000000100000000010000000001",
    2).toLong
  val DIAGONAL: Long = BigInt(
    "0000000000000001010101010010101010010101010100101010100101010101",
    2).toLong
  val ON_BOARD: Long = BigInt(
    "0000000000000001111111110111111111011111111101111111110111111111",
    2).toLong
  val CENTER: Long = BigInt(
    "0000000000000000000000000000000000000111110000000000000000000000",
    2).toLong

  // how much to shift from coordinates
  val SHIFT_VERTICAL: Int = BigInt("1010", 2).toInt // 10
  val SHIFT_HORIZONTAL: Int = BigInt("1", 2).toInt // 1
  val SHIFT_SLANT: Int = BigInt("1011", 2).toInt // 11
  val SHIFT_BACKSLANT: Int = BigInt("1001", 2).toInt // 9

  // count number of set bits in a word
  val ONES: Long = BigInt(
    "0101010101010101010101010101010101010101010101010101010101010101",
    2).toLong // A series of 0101 0101 0101 on all 64 bits
  val TWOS: Long = BigInt(
    "0011001100110011001100110011001100110011001100110011001100110011",
    2).toLong // A series of 0011 0011 0011 on all 64 bits
  val FOURS: Long = BigInt("00001111000011110000111100001111", 2).toInt // A series 0f 0000 1111 0000 1111 on only last 32 bits

  // isolate one of the bits from a bitboard
  def lastBit(bitboard: Long): Long = bitboard & -bitboard

  def fill64(bin: String): String =
    String.format("%0" + (64 - bin.length) + "d%s", 0, bin)

  def display(bin: String): List[String] = {
    val fillBin = fill64(bin)
    val control: List[String] =
      fillBin.take(4) ::
        fillBin.slice(55, 64) ::
        fillBin.slice(45, 54) ::
        fillBin.slice(35, 44) ::
        fillBin.slice(25, 34) ::
        fillBin.slice(15, 24) ::
        Nil

    println("|`~`     |a | b| c| d| e| f| g| h|i|")
    println("|--------|--|--|--|--|--|--|--|--|-|")

    for ((i, j) <- (1 to 5).zip((5 to 1 by -1))) {
      val row =
        String.format("|%7s %-25s",
                      displayEmoji(j),
                      displayRow(control(i).toList))
      println(row)
    }
    control
  }

  def displayRow(row: List[Char]): String = {
    val pipe = '|'
    row.toString
      .map(x => if (x == ',' || x == '(' || x == ')') pipe else x)
      .replace("List", "")
      .reverse
  }

  def displayEmoji(number: Int): String = {
    val emoji = List(":zero:",
                     ":one:",
                     ":two:",
                     ":three:",
                     ":four:",
                     ":five:",
                     ":six:",
                     ":seven:",
                     ":eight:",
                     ":nine:")
    emoji(number)
  }

}
