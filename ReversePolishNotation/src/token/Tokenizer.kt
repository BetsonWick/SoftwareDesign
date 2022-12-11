package token

import token.impl.Brace
import token.impl.NumberToken
import token.impl.Operation
import token.type.BraceType
import token.type.OperationType
import java.io.InputStream

class Tokenizer(private val inputStream: InputStream) {
    private var state = StateType.INIT
    private val result = mutableListOf<Token>()
    private var currentCharInt = Int.MIN_VALUE

    fun tokenize(): List<Token> {
        return start()
    }

    private fun start(): List<Token> {
        state = StateType.START
        next()
        while (currentCharInt != -1) {
            val currentChar = currentCharInt.toChar()
            if (currentChar.isDigit()) {
                numberState()
                continue
            } else if (!currentChar.isWhitespace()) {
                createToken(currentChar)
            }
            next()
        }
        return endState()
    }

    private fun numberState() {
        state = StateType.NUMBER
        val numberString = StringBuilder()
        while (isDigit()) {
            numberString.append(currentCharInt.toChar())
            next()
        }
        result.add(NumberToken(numberString.toString().toLong()))
    }

    private fun createToken(currentChar: Char) {
        when (currentChar.toString()) {
            BraceType.LEFT.look -> result.add(Brace(BraceType.LEFT))
            BraceType.RIGHT.look -> result.add(Brace(BraceType.RIGHT))
            OperationType.PLUS.look -> result.add(Operation(OperationType.PLUS))
            OperationType.MINUS.look -> result.add(Operation(OperationType.MINUS))
            OperationType.MUL.look -> result.add(Operation(OperationType.MUL))
            OperationType.DIV.look -> result.add(Operation(OperationType.DIV))
            else -> errorState(currentChar)
        }
    }

    private fun errorState(currentChar: Char) {
        state = StateType.ERROR
        throw IllegalArgumentException("Illegal character: $currentChar")
    }

    private fun endState(): List<Token> {
        state = StateType.END
        return result
    }

    fun next() {
        currentCharInt = inputStream.read()
    }

    private fun isDigit(): Boolean {
        return currentCharInt != -1 && currentCharInt.toChar().isDigit()
    }
}
