@file:Suppress("unused", "ConvertCallChainIntoSequence", "MoveLambdaOutsideParentheses", "RedundantLambdaArrow")

package com.tomaz.kt_ch5_lambda

import com.tomaz.kt_ch5_lambda.intro.model.Planet

/**
 * Created by TomazWang on 2019/03/22.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/22
 **/


//  ██╗  ██╗████████╗         ██╗      ██╗      █████╗ ███╗   ███╗██████╗ ██████╗  █████╗
//  ██║ ██╔╝╚══██╔══╝         ╚██╗     ██║     ██╔══██╗████╗ ████║██╔══██╗██╔══██╗██╔══██╗
//  █████╔╝    ██║       █████╗╚██╗    ██║     ███████║██╔████╔██║██████╔╝██║  ██║███████║
//  ██╔═██╗    ██║       ╚════╝██╔╝    ██║     ██╔══██║██║╚██╔╝██║██╔══██╗██║  ██║██╔══██║
//  ██║  ██╗   ██║            ██╔╝     ███████╗██║  ██║██║ ╚═╝ ██║██████╔╝██████╔╝██║  ██║
//  ╚═╝  ╚═╝   ╚═╝            ╚═╝      ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═════╝ ╚═════╝ ╚═╝  ╚═╝


// Great resources
//
// - KotlinConf 2018 - Dissecting the stdlib by Huyen Tue Dao
// ---- 🔗 https://youtu.be/Fzt_9I733Yg
// ---- 🔗 https://github.com/queencodemonkey/dissecting-the-stdlib
//
// - KotlinConf 2017 - Two Stones, One Bird: Implementation Tradeoffs by Christina Lee
// ---- 🔗 https://youtu.be/YxOTU9F_YX4
//
// - Higher-Order Functions and Lambdas
// ---- 🔗 https://kotlinlang.org/docs/reference/lambdas.html

//
//
// ============================================================================================================
//
//


//
// 💡 今日目標
//
// - 學會什麼是 Lambda
// - 更了解 function ( extension function, higher order function, lambda with receiver)
// - 學會內建的好用 function
//


//
// 💡 使用教學
//
// - 看到 📄 -> 搜尋並切換到該檔案
// - 看到 🚀 -> 前往該檔案，並執行 main function
// - 看到 🏁 -> 切換並試著完成這個測試


/**
 * 找到適合人類居住的星球
 *
 * 假設我們今天要移民宇宙、有一個 function 可以快速找到適合的星球
 */
fun findEarthReplacements(planets: List<Planet?>): List<Pair<String, Planet>> =
    planets
        .filterNotNull()
        .filter { it.oxygen > 0.18 }
        .filter { it.water > 0.7 }
        .filter { it.temp < 80 && it.temp > -20 }
        .sortedByDescending { it.size }
        .map { it.name to it }

// 試試看
//
// 🚀 IntroTest#`test findEarthReplacements`


//
//
// ============================================================================================================
//
//

//
//   _______                       __                  __    __
//  |       \                     |  \                |  \  |  \
//  | $$$$$$$\  ______    _______  \$$  _______       | $$  | $$  _______   ______    ______    ______
//  | $$__/ $$ |      \  /       \|  \ /       \      | $$  | $$ /       \ |      \  /      \  /      \
//  | $$    $$  \$$$$$$\|  $$$$$$$| $$|  $$$$$$$      | $$  | $$|  $$$$$$$  \$$$$$$\|  $$$$$$\|  $$$$$$\
//  | $$$$$$$\ /      $$ \$$    \ | $$| $$            | $$  | $$ \$$    \  /      $$| $$  | $$| $$    $$
//  | $$__/ $$|  $$$$$$$ _\$$$$$$\| $$| $$_____       | $$__/ $$ _\$$$$$$\|  $$$$$$$| $$__| $$| $$$$$$$$
//  | $$    $$ \$$    $$|       $$| $$ \$$     \       \$$    $$|       $$ \$$    $$ \$$    $$ \$$     \
//   \$$$$$$$   \$$$$$$$ \$$$$$$$  \$$  \$$$$$$$        \$$$$$$  \$$$$$$$   \$$$$$$$ _\$$$$$$$  \$$$$$$$
//                                                                                  |  \__| $$
//                                                                                   \$$    $$
//                                                                                    \$$$$$$

// 📕 5.1.3


// 基本的 function 宣告法
fun simpleFunction(arg: String) {
    print("Hello $arg")
}


// 會回傳資料的 function
fun funcWithReturnValue(x: Int): Int {
    val y = x + 1
    return y
}

// 簡化簡單 return
fun funcWithReturnValue2(x: Int) = x + 1


// 簡化成 lambda
val addOne = { x: Int -> x + 1 }


val sumOfEven = { ints: IntArray? ->
    
    if (ints == null) {
        0   // lambda 中不能使用 return, 因為 Kotlin 中 return 是 return 最近一個 "fun" 定義
    } else {
        var sum = 0
        for (i in ints) {
            if (i % 2 == 0) {
                sum += i
            }
        }
        sum
    }
}

//❓那如果真的很想加 return 怎麼辦 => 🏁 BasicCounterTest#`return from lambda`


// ⛄ 冷知識時間
//
// IntArray 轉成 java 會變成 int[]
// Array<Int> 轉成 java 會變成 Integer[]


// lambda 可以吃到外部的變數
var counter = 0
val inc = { counter++ }

// 🚀️ BasicCounterTest#`lambda can access outer variable`


//
//
// ============================================================================================================
//
//


//
//   __    __  __            __                                   ______                   __
//  |  \  |  \|  \          |  \                                 /      \                 |  \
//  | $$  | $$ \$$  ______  | $$____    ______    ______        |  $$$$$$\  ______    ____| $$  ______    ______
//  | $$__| $$|  \ /      \ | $$    \  /      \  /      \       | $$  | $$ /      \  /      $$ /      \  /      \
//  | $$    $$| $$|  $$$$$$\| $$$$$$$\|  $$$$$$\|  $$$$$$\      | $$  | $$|  $$$$$$\|  $$$$$$$|  $$$$$$\|  $$$$$$\
//  | $$$$$$$$| $$| $$  | $$| $$  | $$| $$    $$| $$   \$$      | $$  | $$| $$   \$$| $$  | $$| $$    $$| $$   \$$
//  | $$  | $$| $$| $$__| $$| $$  | $$| $$$$$$$$| $$            | $$__/ $$| $$      | $$__| $$| $$$$$$$$| $$
//  | $$  | $$| $$ \$$    $$| $$  | $$ \$$     \| $$             \$$    $$| $$       \$$    $$ \$$     \| $$
//   \$$   \$$ \$$ _\$$$$$$$ \$$   \$$  \$$$$$$$ \$$              \$$$$$$  \$$        \$$$$$$$  \$$$$$$$ \$$
//                |  \__| $$
//                 \$$    $$
//                  \$$$$$$
//                        ________                                 __      __
//                       |        \                               |  \    |  \
//                       | $$$$$$$$__    __  _______    _______  _| $$_    \$$  ______   _______
//                       | $$__   |  \  |  \|       \  /       \|   $$ \  |  \ /      \ |       \
//                       | $$  \  | $$  | $$| $$$$$$$\|  $$$$$$$ \$$$$$$  | $$|  $$$$$$\| $$$$$$$\
//                       | $$$$$  | $$  | $$| $$  | $$| $$        | $$ __ | $$| $$  | $$| $$  | $$
//                       | $$     | $$__/ $$| $$  | $$| $$_____   | $$|  \| $$| $$__/ $$| $$  | $$
//                       | $$      \$$    $$| $$  | $$ \$$     \   \$$  $$| $$ \$$    $$| $$  | $$
//                        \$$       \$$$$$$  \$$   \$$  \$$$$$$$    \$$$$  \$$  \$$$$$$  \$$   \$$
//
//


// 首先 lambda 是可以被儲存、傳遞的

fun verify(x: String, verifyMethod: (String) -> Boolean) = verifyMethod.invoke(x)

// TODO: 簡化 verify

fun runVerifyWithLambda() {
    
    val allLowerCase = { s: String -> s.toLowerCase() == s }
    val allUpperCase = { s: String -> s.toUpperCase() == s }
    
    // lambda 可以被當成參數塞進其他 function 內
    verify("abcde", allLowerCase)
    verify("ABCDE", allUpperCase)
}


// 因為 lambda 本質就是 function ， 所以我們用 function 做一次

fun runVerifyWithFun() {
    
    // TODO: 改成 function 版
    
    val allLowerCase = { s: String -> s.toLowerCase() == s }
    val allUpperCase = { s: String -> s.toUpperCase() == s }
    
    verify("abcde", allLowerCase)
    verify("ABCDE", allUpperCase)
}


//
//
// 接下來一步一步的簡化 kotlin 中的 function
//
//


fun simplifyLambdas() {
    
    
    // 基本的 function 宣告
    fun allLowerCaseFun(s: String): Boolean {
        return s.toLowerCase() == s
    }
    
    // 簡化 return
    fun allLowerCaseFun2(s: String): Boolean = (s.toLowerCase() == s)
    
    
    // 自動偵測回傳 type
    fun allLowerCaseFun3(s: String) = s.toLowerCase() == s
    
    // 轉 lambda
    val allLowerCaseFun4 = { s: String -> s.toLowerCase() == s }
    
    
    // 用在 higher order function 中
    verify("abcde", allLowerCaseFun4)
    
    
    // 匿名 lambda
    verify("abcde", { s: String -> s.toLowerCase() == s })
    
    
    // 自動判別 type
    verify("abcde", { s -> s.toLowerCase() == s })
    
    
    // 取名叫做 it
    verify("abcde", { it -> it.toLowerCase() == it })
    
    
    // it 是一個特殊的變數名稱，在 lambda 中代表著預設參數（只有一個參數的時候）。可以省略
    verify("abcde", { it.toLowerCase() == it })
    
    
    // higher order function 的最後一個參數是 lambda 時，可以拉到括號外
    verify("abcde") { it.toLowerCase() == it }
}



//
//
// ============================================================================================================
//
//



//   __                               __              __                                      __
//  |  \                             |  \            |  \                                    /  \
//  | $$       ______   ______ ____  | $$____    ____| $$  ______         __   __   __      /  $$
//  | $$      |      \ |      \    \ | $$    \  /      $$ |      \       |  \ |  \ |  \    /  $$
//  | $$       \$$$$$$\| $$$$$$\$$$$\| $$$$$$$\|  $$$$$$$  \$$$$$$\      | $$ | $$ | $$   /  $$
//  | $$      /      $$| $$ | $$ | $$| $$  | $$| $$  | $$ /      $$      | $$ | $$ | $$  /  $$
//  | $$_____|  $$$$$$$| $$ | $$ | $$| $$__/ $$| $$__| $$|  $$$$$$$      | $$_/ $$_/ $$ /  $$
//  | $$     \\$$    $$| $$ | $$ | $$| $$    $$ \$$    $$ \$$    $$       \$$   $$   $$|  $$
//   \$$$$$$$$ \$$$$$$$ \$$  \$$  \$$ \$$$$$$$   \$$$$$$$  \$$$$$$$        \$$$$$\$$$$  \$$
//
//
//
//                                       _______                                 __
//                                      |       \                               |  \
//                                      | $$$$$$$\  ______    _______   ______   \$$ __     __   ______    ______
//                                      | $$__| $$ /      \  /       \ /      \ |  \|  \   /  \ /      \  /      \
//                                      | $$    $$|  $$$$$$\|  $$$$$$$|  $$$$$$\| $$ \$$\ /  $$|  $$$$$$\|  $$$$$$\
//                                      | $$$$$$$\| $$    $$| $$      | $$    $$| $$  \$$\  $$ | $$    $$| $$   \$$
//                                      | $$  | $$| $$$$$$$$| $$_____ | $$$$$$$$| $$   \$$ $$  | $$$$$$$$| $$
//                                      | $$  | $$ \$$     \ \$$     \ \$$     \| $$    \$$$    \$$     \| $$
//                                       \$$   \$$  \$$$$$$$  \$$$$$$$  \$$$$$$$ \$$     \$      \$$$$$$$ \$$



