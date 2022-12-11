package visitor.impl

import token.Token
import token.impl.Brace
import token.impl.NumberToken
import token.impl.Operation
import visitor.TokenVisitor

class PrintVisitor : TokenVisitor {
    private val result = StringBuilder()

    fun print(tokenList: List<Token>): String {
        for (token in tokenList) {
            token.accept(this)
        }
        println(result)
        return result.toString()
    }

    override fun visit(token: NumberToken) {
        result.append("$token ")
    }

    override fun visit(token: Brace) {
        result.append("$token ")
    }

    override fun visit(token: Operation) {
        result.append("$token ")
    }
}
