package visitor.impl

import token.Token
import token.impl.Brace
import token.impl.NumberToken
import token.impl.Operation
import visitor.TokenVisitor

class CalcVisitor : TokenVisitor {
    private val stack = mutableListOf<Token>()

    fun calculate(tokenList: List<Token>): Long {
        if(tokenList.isEmpty()) {
           throw IllegalArgumentException("Token list is empty!")
        }
        for (token in tokenList) {
            token.accept(this)
        }
        return (stack.removeLast() as NumberToken).value
    }

    override fun visit(token: NumberToken) {
        stack.add(token)
    }

    override fun visit(token: Brace) {
        throw IllegalArgumentException("Brace token is unexpected in Reverse Polish Notation!")
    }

    override fun visit(token: Operation) {
        if(stack.size < 2 || stack.last() !is NumberToken || stack[stack.size - 2] !is NumberToken) {
            throw IllegalArgumentException("Incorrect arguments count!")
        }
        val second = stack.removeLast() as NumberToken
        val first = stack.removeLast() as NumberToken
        stack.add(NumberToken(token.operationType.binaryOperation(first.value, second.value)))
    }
}
