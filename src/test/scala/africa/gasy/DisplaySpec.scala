package africa.gasy

import org.scalatest._

class DisplaySpec extends FlatSpec {

  behavior of "A Binary Literal"

  it should "compare INITIAL_TOP binary literal" in {
    assert(
      Bits.fill64(Bits.INITIAL_TOP.toBinaryString) === "0000000000000001111111110111111111010100101000000000000000000000")
  }

  it should "compare INITIAL_BOT binary literal" in {
    assert(
      Bits.fill64(Bits.INITIAL_BOT.toBinaryString) === "0000000000000000000000000000000000001010010101111111110111111111"
    )
  }

  it should "display INITIAL_TOP bitboard" in {
    Bits.display(Bits.INITIAL_TOP.toBinaryString)
    assert(true)
  }

  it should "display INITIAL_BOT bitboard" in {
    Bits.display(Bits.INITIAL_BOT.toBinaryString)
    assert(true)
  }

}
