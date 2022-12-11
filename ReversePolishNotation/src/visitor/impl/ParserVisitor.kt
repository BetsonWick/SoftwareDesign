package visitor.impl

import token.Token
import token.impl.Brace
import token.impl.NumberToken
import token.impl.Operation
import token.type.BraceType
import visitor.TokenVisitor

class ParserVisitor : TokenVisitor {
    private val stack = mutableListOf<Token>()
    private val result = mutableListOf<Token>()

    fun parse(tokenList: List<Token>): List<Token> {
        for (token in tokenList) {
            token.accept(this)
        }
        if (stack.any { it !is Operation }) {
            throw IllegalArgumentException("Unmatched parentheses!")
        }
        result.addAll(stack.reversed())
        return result
    }

    override fun visit(token: NumberToken) {
        result.add(token)
    }

    override fun visit(token: Brace) {
        if (token.braceType == BraceType.LEFT) {
            stack.add(token)
        } else if (token.braceType == BraceType.RIGHT) {
            var lastToken = stack.lastOrNull()
            if (lastToken == null) {
                throw IllegalArgumentException("Unmatched right parenthesis!")
            }
            while (!(lastToken is Brace && lastToken.braceType == BraceType.LEFT)) {
                result.add(stack.removeLast())
                lastToken = stack.lastOrNull()
            }
            stack.removeLast()
        }
    }

    override fun visit(token: Operation) {
        var lastToken = stack.lastOrNull()
        while (lastToken is Operation && lastToken.operationType.priority >= token.operationType.priority) {
            result.add(stack.removeLast())
            lastToken = stack.lastOrNull()
        }
        stack.add(token)
    }
}
