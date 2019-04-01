@file:Suppress("unused", "UNUSED_VARIABLE")

package com.tomaz.kt_ch5_lambda.talk.t7_with_friends

/**
 * Created by TomazWang on 2019/03/28.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/28
 *
 * T.apply      ---- extension function ..... 使用方法是接在目標後面，同時目標也是 receiver
 * T.() -> Unit ---- lambda with receiver ... 傳入的 lambda 會直接執行在 receiver 身上, return Unit
 * return T     ---- return receiver ........ 回傳的資料，就是吃這個 function 的人本身
 *
 *
 * inline fun <T> T.apply(block: T.() -> Unit): T {
 *     block()
 *     return this
 * }
 *
 *
 * apply 的特色是 return 值是 T (還不只是Ｔ 而已，是 this) => 把東西操作後還回去給使用者的概念
 *
 *
 *
 *  ================================================================================
 *
 *
 *
 * T.also       ---- extension function ....... 使用方法是接在目標後面
 * (T) -> Unit  ---- function with argument ... 傳入的 lambda 會帶入執行目標作為參數，return Unit
 * return T     ---- return receiver .......... 回傳的資料，就是吃這個 function 的人本身
 *
 *
 * inline fun <T> T.also(block: (T) -> Unit): T {
 *     block(this)
 *     return this
 * }
 *
 *
 * 和 apply 不同的是，also 的參數並不是 lambda with receiver, 而是一個吃 T 參數的 function
 * 相同的是，also 一樣會 return this
 *
 *
 **/
class AlsoApplyTest {
    
    
    /**
     * apply 常用在 initialization
     */
    fun `apply usually use in initialization`() {
        
        val tv = TextView().apply { setText("123") }
        // val tvIRL = findViewById(R.id.tv_irl).apply { setText("123") }
        
    }

//    another exapmle from google/iosched:

//    fun newInstance(featureId: String): MapFragment {
//        return MapFragment().apply {
//            arguments = Bundle().apply {
//                putString(ARG_FEATURE_ID, featureId)
//            }
//        }
//    }
    
    
    /**
     * also 就像是他的名字一樣，"啊！還有 .... "
     * 通常會用在要使用一物件時順帶做的操作
     */
    
    fun `also usually use as a side effect`() {
        
        Builder()
            .setText("!23")
            .also {
                println(it)
                /** insert some log **/
            }
            .setColor(123)
            .build()
    }
    
}


class TextView {
    fun setText(s: String) {}
    fun setColor(color: Int) {}
}


class Builder {
    fun setText(s: String): Builder = this
    fun setColor(c: Int): Builder = this
    fun build() {}
}


// +-- 💡 重點整理 apply --------------------------------------------------------------------------
// |
// |    - 用法:   T.apply { ... }
// |    - 用途:   初始化一個物件並且使用它
// |    - 回傳:   T, 物件本身
// |
// +----------------------------------------------------------------------------------------------


// +-- 💡 重點整理 also ---------------------------------------------------------------------------
// |
// |    - 用法:   T.also { ... }
// |    - 用途:   在主要行為執行同時順帶執行的事件
// |    - 回傳:   T, 物件本身
// |
// +----------------------------------------------------------------------------------------------


// +-- 💡 重點整理 apply vs also ------------------------------------------------------------------
// |
// |    apply 和 also 使用方法相同、回傳值也相同。
// |
// |    最大的差異在 apply 使用的是 lambda with receiver => 在 lambda 中可以是同在 T class 內。適合重複
// |    對 T 執行多次操作
// |
// |    當然 also 也能對 `it` 做多次操作，但是比較適合一次性的操作
// |
// +----------------------------------------------------------------------------------------------


// 📄 WithFriends.kt # run 🔐