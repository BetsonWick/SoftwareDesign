package visitor

import token.impl.Brace
import token.impl.NumberToken
import token.impl.Operation

interface TokenVisitor {
    fun visit(token: NumberToken)

    fun visit(token: Brace)

    fun visit(token: Operation)
}
