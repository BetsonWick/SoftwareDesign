package token.impl

import token.Token
import visitor.TokenVisitor

class NumberToken(val value: Long) : Token {
    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

    override fun toString() = "NUMBER($value)"
}
