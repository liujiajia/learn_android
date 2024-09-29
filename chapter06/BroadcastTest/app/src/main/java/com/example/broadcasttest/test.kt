package com.example.broadcasttest

fun example(func: (String, Int) -> Unit) {
    func("hello", 123)
}

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int) : Int {
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

// 将内联函数中的代码在编译的时候自动替换到调用它的地方， 这样不存在运行时的开销了
// 非内联函数类型参数可以自由的传递给其他任何函数
inline fun inlineTest(block1: () -> Unit, noinline block2: () -> Unit) {

}

fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}

// crossinline 保证在内联函数的Lambda表达式中一定不会使用return关键字
inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }

    runnable.run()

}

fun main() {
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2) { n1, n2 -> n1 + n2 }
    val result2 = num1AndNum2(num1, num2) { n1, n2 -> n1 - n2 }
    println("result1 is $result1")
    println("result2 is $result2")

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())


    println("main start")
    val str = ""
    printString(str) { s ->
        println("lambda start")
        if (s.isEmpty()) return@printString
        println(s)
        println("lambda end")
    }
    println("main end")
}