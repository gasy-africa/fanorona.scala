package africa.gasy

import org.scalatest._

class DisplaySpec extends FlatSpec {

  behavior of "A Binary Literal"

  it should "display binaries" in {
    assert(
      Bits.fill64(Bits.INITIAL_TOP.toBinaryString) === "0000000000000001111111110111111111010100101000000000000000000000")
  }

  it should "INITIAL_TOP bitboard" in {
    Bits.display(Bits.INITIAL_TOP.toBinaryString)
    assert(true)
  }

}
