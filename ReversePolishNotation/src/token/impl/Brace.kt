package token.impl

import token.Token
import token.type.BraceType
import visitor.TokenVisitor

class Brace(val braceType: BraceType) : Token {
    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

    @Override
    override fun toString() = braceType.name
}