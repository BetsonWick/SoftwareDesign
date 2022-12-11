import token.Tokenizer
import visitor.impl.CalcVisitor
import visitor.impl.ParserVisitor
import visitor.impl.PrintVisitor

fun main() {
    val newLine = readln()
    val tokens = ParserVisitor().parse(Tokenizer(newLine.byteInputStream()).tokenize())
    PrintVisitor().print(tokens)
    println(CalcVisitor().calculate(tokens))
}