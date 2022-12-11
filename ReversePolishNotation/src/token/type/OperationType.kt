package token.type

enum class OperationType(val look: String, val priority: Int, val binaryOperation: (a: Long, b: Long) -> Long) {
    PLUS("+", 1, { a, b -> a + b }),
    MINUS("-", 1, { a, b -> a - b }),
    MUL("*", 2, { a, b -> a * b }),
    DIV("/", 2, { a, b -> a / b }),
}
