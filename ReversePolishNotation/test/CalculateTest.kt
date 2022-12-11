import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import token.Tokenizer
import visitor.impl.CalcVisitor
import visitor.impl.ParserVisitor
import visitor.impl.PrintVisitor

class CalculateTest {
    private val parserVisitor = ParserVisitor()
    private val calcVisitor = CalcVisitor()
    private val printVisitor = PrintVisitor()

    @MethodSource("getExpressions")
    @ParameterizedTest(name = "{0}")
    fun multipleExpressions(name: String, expression: String, expectedTokens: String, expectedValue: Long) {
        val tokens = parserVisitor.parse(Tokenizer(expression.byteInputStream()).tokenize())
        assertEquals(expectedTokens.trim(), printVisitor.print(tokens).trim())
        assertEquals(expectedValue, calcVisitor.calculate(tokens))
    }

    companion object {
        @JvmStatic
        private fun getExpressions(): List<Arguments> {
            return listOf(
                Arguments.of("Simple sum", "1 + 3", "NUMBER(1) NUMBER(3) PLUS", 4),
                Arguments.of("Extra whitespaces", "        1    +    3    ", "NUMBER(1) NUMBER(3) PLUS", 4),
                Arguments.of(
                    "All operations",
                    "1 + 2 * 3 / 4 - (10 + 10)",
                    "NUMBER(1) NUMBER(2) NUMBER(3) MUL NUMBER(4) DIV PLUS NUMBER(10) NUMBER(10) PLUS MINUS",
                    -18
                ),
                Arguments.of(
                    "Big expression",
                    "1000 + ( 3000 - 2000 / 10 + (50 * 40 ))",
                    "NUMBER(1000) NUMBER(3000) NUMBER(2000) NUMBER(10) DIV MINUS NUMBER(50) NUMBER(40) MUL PLUS PLUS",
                    5800
                ),
            )

        }
    }
}