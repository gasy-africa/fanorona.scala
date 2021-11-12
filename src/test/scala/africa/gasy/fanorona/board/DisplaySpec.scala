package africa.gasy.fanorona.board

import org.scalatest._
import flatspec._
import matchers._

class DisplaySpec extends AnyFlatSpec with should.Matchers {

  "A Binary Literal" should "compare INITIAL_TOP binary literal" in {
    Bits.fill64(Bits.INITIAL_TOP.toBinaryString) should be(
      "0000000000000001111111110111111111010100101000000000000000000000")
  }

  it should "compare INITIAL_BOT binary literal" in {
    Bits.fill64(Bits.INITIAL_BOT.toBinaryString) should be(
      "0000000000000000000000000000000000001010010101111111110111111111")
  }

  // -- TODO fix me
  // it should "display INITIAL_TOP bitboard" in {
  //   Bits.display(Bits.INITIAL_TOP.toBinaryString) should be ("0000000000000001111111110111111111010100101000000000000000000000")
  // }

  // it should "display INITIAL_BOT bitboard" in {
  //   Bits.display(Bits.INITIAL_BOT.toBinaryString) should be ("0000000000000000000000000000000000001010010101111111110111111111")
  // }

}
