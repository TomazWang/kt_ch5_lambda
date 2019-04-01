@file:Suppress("unused")

package com.tomaz.kt_ch5_lambda.talk.t7_with_friends

/**
 * Created by TomazWang on 2019/03/28.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/28
 **/

//
//
// T.let        ---- extension function ... 使用方法是接在目標後面
// (T) -> R     ---- lambda ............... 傳入的 lambda 要吃一個和呼叫目標一樣 type 的參數
// return R     ---- return value ......... 回傳的資料，就會是 lambda 的回傳值
//
// 📄 LetUsageTest.kt
//
//
inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}


//
//
// with()       ---- simple function ........ 使用方法是直接呼叫 with
// T.() -> R    ---- lambda with receiver ... 傳入的 lambda 會直接執行在 receiver 身上
// return R     ---- return value ........... 回傳的資料，就會是 lambda 的回傳值
//
// 📄 WithUsageTest.kt
//
//
inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    return receiver.block()
}


//
//
// T.apply      ---- extension function ..... 使用方法是接在目標後面，同時目標也是 receiver
// T.() -> Unit ---- lambda with receiver ... 傳入的 lambda 會直接執行在 receiver 身上, return Unit
// return T     ---- return receiver ........ 回傳的資料，就是吃這個 function 的人本身
//
// 📄 AlsoApplyTest.kt
//
//
inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

//
//
// T.also       ---- extension function ....... 使用方法是接在目標後面
// (T) -> Unit  ---- function with argument ... 傳入的 lambda 會帶入執行目標作為參數，return Unit
// return T     ---- return receiver .......... 回傳的資料，就是吃這個 function 的人本身
//
// 📄 AlsoApplyTest.kt
//
//
inline fun <T> T.also(block: (T) -> Unit): T {
    block(this)
    return this
}


//
//
// T.run        ---- extension function ..... 使用方法是接在目標後面, 同時也是receiver
// T.() -> R    ---- lambda with receiver ... 傳入的 lambda 會直接執行在 receiver 身上
// return R     ---- return value ........... 回傳的資料，就會是 lambda 的回傳值
//
// 📄 RunUsageTest.kt
//
//
inline fun <T, R> T.run(block: T.() -> R): R {
    return block()
}


//
//
// run()        ---- extension function .......... 使用方法是直接呼叫 run
// () -> R      ---- function with no argument ... 傳入的 lambda 沒有帶任何參數
// return R     ---- return value ................ 回傳的資料，就會是 lambda 的回傳值
//
// 📄 RunUsageTest.kt
//
//
inline fun <R> run(block: () -> R): R {
    return block()
}









// +-- 💡 重點整理 --------------------------------------------------------------------------------
// |
// |
// |                        |    需要連續操作    ||    不需要連續操作
// |       =================|===================|==================
// |                        |                   |
// |                        |                   |
// |        需要原物件               apply                also
// |                        |                   |
// |                        |                   |
// |       -----------------+-------------------+------------------
// |                        |                   |
// |                        |                   |
// |        不需要原物件              with                 let
// |     (需要 return 新值)  |                   |
// |                        |                   |
// |                        |                   |
// |
// +----------------------------------------------------------------------------------------------



