package token.impl

import token.Token
import token.type.OperationType
import visitor.TokenVisitor

class Operation(val operationType: OperationType) : Token {
    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

    @Override
    override fun toString() = operationType.name
}
