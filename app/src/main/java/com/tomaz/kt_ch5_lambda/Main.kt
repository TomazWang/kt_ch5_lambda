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


// +-- 💡 今日目標 -------------------------------------------------------------------------------
// |
// |    - 學會什麼是 Lambda
// |    - 更了解 function ( extension function, higher order function, lambda with receiver)
// |    - 學會內建的好用 function
// |
// +----------------------------------------------------------------------------------------------


//
// +-- 💡 使用教學 --------------------------------------------------------------------------------
// |
// |    - 看到 📄 -> 搜尋並切換到該檔案 ( 🔐, 🔑 一對互相連結 )
// |    - 看到 🚀 -> 前往該檔案，並執行 main function
// |    - 看到 🏁 -> 切換並試著完成這個測試
// |
// +----------------------------------------------------------------------------------------------


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
// ================================================================================================
//
//

//

//    ____            _        _   _
//   | __ )  __ _ ___(_) ___  | | | |___  __ _  __ _  ___
//   |  _ \ / _` / __| |/ __| | | | / __|/ _` |/ _` |/ _ \
//   | |_) | (_| \__ \ | (__  | |_| \__ \ (_| | (_| |  __/
//   |____/ \__,_|___/_|\___|  \___/|___/\__,_|\__, |\___|
//                                             |___/
//
//    基礎 function 用法複習


// 📕 5.1.3

//
//
// 基本的 function 宣告法
fun simpleFunction(arg: String) {
    print("Hello $arg")
}

//
//
// 會回傳資料的 function
fun funcWithReturnValue(x: Int): Int {
    val y = x + 1
    return y
}

//
//
// 簡化簡單 return
fun funcWithReturnValue2(x: Int) = x + 1

//
//
// 簡化成 lambda
val addOne = { x: Int -> x + 1 }

//
//
//
//
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

//
//
//❓那如果真的很想加 return 怎麼辦 => 🏁 BasicCounterTest#`return from lambda`


//
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


//    _   _ _       _                  ___          _             _____                 _   _
//   | | | (_) __ _| |__   ___ _ __   / _ \ _ __ __| | ___ _ __  |  ___|   _ _ __   ___| |_(_) ___  _ __
//   | |_| | |/ _` | '_ \ / _ \ '__| | | | | '__/ _` |/ _ \ '__| | |_ | | | | '_ \ / __| __| |/ _ \| '_ \
//   |  _  | | (_| | | | |  __/ |    | |_| | | | (_| |  __/ |    |  _|| |_| | | | | (__| |_| | (_) | | | |
//   |_| |_|_|\__, |_| |_|\___|_|     \___/|_|  \__,_|\___|_|    |_|   \__,_|_| |_|\___|\__|_|\___/|_| |_|
//            |___/
//
//   高階函式 📕 8.1


// 首先 lambda 是可以被儲存、傳遞的

fun verify(x: String, block: (String) -> Boolean): Boolean {
    return block.invoke(x)
}

// TODO: 簡化 verify

//
//
//
//
//
fun runVerifyWithLambda() {
    
    val allLowerCase = { s: String -> s.toLowerCase() == s }
    val allUpperCase = { s: String -> s.toUpperCase() == s }
    
    // lambda 可以被當成參數塞進其他 function 內
    verify("abcde", allLowerCase)
    verify("ABCDE", allUpperCase)
}


//
//
//
// 因為 lambda 本質就是 function ， 所以我們用 function 做一次
//
//
//
//
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
    
    //
    // 簡化 return
    //
    fun allLowerCaseFun2(s: String): Boolean = (s.toLowerCase() == s)
    
    
    //
    // 自動偵測回傳 type
    //
    fun allLowerCaseFun3(s: String) = s.toLowerCase() == s
    
    //
    // 轉 lambda
    //
    val allLowerCaseFun4 = { s: String -> s.toLowerCase() == s }
    
    
    //
    // 用在 higher order function 中
    //
    verify("abcde", allLowerCaseFun4)
    
    
    //
    // 匿名 lambda
    //
    verify("abcde", { s: String -> s.toLowerCase() == s })
    
    
    //
    // 自動判別 type
    //
    verify("abcde", { s -> s.toLowerCase() == s })
    
    
    //
    // 取名叫做 it
    //
    verify("abcde", { it -> it.toLowerCase() == it })
    
    
    //
    // it 是一個特殊的變數名稱，在 lambda 中代表著預設參數（只有一個參數的時候）。可以省略
    //
    verify("abcde", { it.toLowerCase() == it })
    
    
    //
    // higher order function 的最後一個參數是 lambda 時，可以拉到括號外
    //
    verify("abcde") { it.toLowerCase() == it }
}


//
//
// ============================================================================================================
//
//


//    _                    _         _                   __   ____      _ _           _   _
//   | |    __ _ _ __ ___ | |__   __| | __ _  __      __/ /  / ___|___ | | | ___  ___| |_(_) ___  _ __
//   | |   / _` | '_ ` _ \| '_ \ / _` |/ _` | \ \ /\ / / /  | |   / _ \| | |/ _ \/ __| __| |/ _ \| '_ \
//   | |__| (_| | | | | | | |_) | (_| | (_| |  \ V  V / /   | |__| (_) | | |  __/ (__| |_| | (_) | | | |
//   |_____\__,_|_| |_| |_|_.__/ \__,_|\__,_|   \_/\_/_/     \____\___/|_|_|\___|\___|\__|_|\___/|_| |_|
//
//
//   集合相關 Kotlin 內建 API 📕 5.2


//
// +-- 💡 重點整理 --------------------------------------------------------------------------------
// |
// |    Standard library 中有很多好用的 function 利用了 lambda 的特性
// |
// |    - forEach
// |    - map
// |    - filter
// |
// |    - ... and more 🔗 https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html
// |
// +----------------------------------------------------------------------------------------------


// forEach
/*

public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

*/


val names = listOf("Albert", "Ben", "Charlie", "Daniel", "Edward")

fun readNames() {
    names.forEach {
        println("Hello, $it")
    }
}

// ❓ forEach 和 for .. in 的差異
//
// 🏁 試著完成️ LambdaWithCollections#Feature("招募")


//
// map
//
// map 可以把一連串的資料一個一個用同樣的邏輯轉換成別的東西

/*

public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
    return mapTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
}

 */

val numbers = (1..5)      // [1, 2, 3, 4, 5]
val numberSquare = numbers.map { it * it } // [1, 4, 9, 16, 25]

//
// filter
//
// filter 可以把一個列表中符合某個邏輯的過濾出來

/*

public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList<T>(), predicate)
}

 */

val oddNumbers = numbers.filter { it % 2 != 0 }


//
//    ___  ___  __ _ _   _  ___ _ __   ___ ___  ___
//   / __|/ _ \/ _` | | | |/ _ \ '_ \ / __/ _ \/ __|
//   \__ \  __/ (_| | |_| |  __/ | | | (_|  __/\__ \
//   |___/\___|\__, |\__,_|\___|_| |_|\___\___||___/
//                |_|
//
//   序列、惰性集合操作 📕 5.3

//
//
//
//
// 上面的那些 function 一次都會處理一整個 list
// 當 list 內容太多的時候，對記憶體是一個很大的負擔
//
//
//

//
//
//
//
// 簡單範例
// 🚀️ SequenceTest
//


//
//
//
//
//
// +-- 💡 重點整理 --------------------------------------------------------------------------------
// |
// |    - sequence 可以單個 item 處理，不會需要讓記憶體一次存放大量資料
// |    - sequence 沒有 terminal operation 不會有結果
// |
// +----------------------------------------------------------------------------------------------


//
//
//
// terminal operator
//
// 📕 5.3.1
//
// - all & any
// - count


// all 檢查是否全部的 item 都符合
// any 檢查其中是否有 item 都符合

val thisValIsFalse = (1..5).all { it > 3 }
val thisValIsTrue = (1..5).any { it > 3 }


// count 計算列表的 item 數量
val thisValIs6 = (1..20).filter { it < 7 }.count()
val thisValIsAlso6 = (1..20).count { it < 7 }
val thisValIsAlso6Too = (1..20).filter { it < 7 }.size

// ⛄ 冷知識時間
//
// count 在記憶體中，只會計算數量（ item 內容會被 gc 掉）
// size 會產生並儲存 list 內容
//
// --> 當只需要數量的時候，使用 count 是比較優化的作法

// 🚀️ BasicCounterTest#`lambda can access outer variable`


//
//
// ============================================================================================================
//
//


//   🔑 kotlin_lambda_in_java
//
//
//    _  __     _   _ _         _                    _         _
//   | |/ /___ | |_| (_)_ __   | |    __ _ _ __ ___ | |__   __| | __ _
//   | ' // _ \| __| | | '_ \  | |   / _` | '_ ` _ \| '_ \ / _` |/ _` |
//   | . \ (_) | |_| | | | | | | |__| (_| | | | | | | |_) | (_| | (_| |
//   |_|\_\___/ \__|_|_|_| |_| |_____\__,_|_| |_| |_|_.__/ \__,_|\__,_|
//   (_)_ __       | | __ ___   ____ _
//   | | '_ \   _  | |/ _` \ \ / / _` |
//   | | | | | | |_| | (_| |\ V / (_| |
//   |_|_| |_|  \___/ \__,_| \_/ \__,_|
//
//
//   在 Java 中引用 Kotlin Lambda 📕 5.4

// 📄 前往 KtLambda.kt (超級迷宮)
//
// 或是 🔗 https://developer.android.com/kotlin/interop#lambda_arguments


//
//
//
//
//


//
//
// ========================= 我 是 分 隔 線 =======================================================================
//
//


//
//
//
//
//


//   🔑 lambda_with_receiver
//
//
//    _                    _         _                   __
//   | |    __ _ _ __ ___ | |__   __| | __ _  __      __/ /
//   | |   / _` | '_ ` _ \| '_ \ / _` |/ _` | \ \ /\ / / /
//   | |__| (_| | | | | | | |_) | (_| | (_| |  \ V  V / /
//   |_____\__,_|_| |_| |_|_.__/ \__,_|\__,_|   \_/\_/_/
//   |  _ \ ___  ___ ___(_)_   _____ _ __
//   | |_) / _ \/ __/ _ \ \ \ / / _ \ '__|
//   |  _ <  __/ (_|  __/ |\ V /  __/ |
//   |_| \_\___|\___\___|_| \_/ \___|_|
//
//
//   有接收器的 lambda 📕 5.5


// 📄 Monkey.kt (超級迷宮 2)


//
//
//
//
//
//


//
//
// ============================================================================================================
//
//


//   🔑 with_friends
//
//
//
//
//
//    _         _   _ _         ██╗    ██╗██╗████████╗██╗  ██╗     __      _                _
//   | | _____ | |_| (_)_ __    ██║    ██║██║╚══██╔══╝██║  ██║    / _|_ __(_) ___ _ __   __| |___
//   | |/ / _ \| __| | | '_ \   ██║ █╗ ██║██║   ██║   ███████║   | |_| '__| |/ _ \ '_ \ / _` / __|
//   |   < (_) | |_| | | | | |  ██║███╗██║██║   ██║   ██╔══██║   |  _| |  | |  __/ | | | (_| \__ \
//   |_|\_\___/ \__|_|_|_| |_|  ╚███╔███╔╝██║   ██║   ██║  ██║   |_| |_|  |_|\___|_| |_|\__,_|___/
//
//
//   with 與他的們的產地 📕 5.5





