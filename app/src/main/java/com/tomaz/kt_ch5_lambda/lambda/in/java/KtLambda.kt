@file:Suppress("unused", "ObjectLiteralToLambda")

package com.tomaz.kt_ch5_lambda.lambda.`in`.java

/**
 * Created by TomazWang on 2019/03/23.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/23
 **/


class Button {
    
    
    /** 在 Kotlin 中定義一個帶有 lambda 的  HOF **/
    fun onClick(listener: (Button) -> Unit) {
        listener(this)
    }
    
    //
    // 📄 UsingLambda.java using_kotlin_lambda 🔐
    
    
    
    //
    //
    //
    //
    //
    //
    //
    // 🔑 solve_return_unit
    //
    //
    //
    /// 另外也定義一個和傳統 java 一樣的 SAM (single abstract method, 基本上就是 Java 的 lambda 寫法)
    fun onLongClick(listener: LongClickListener) {
        listener.onLongClick()
    }
    
    //
    //
    //
    //
    // 📄 UsingLambda.java using_sam_from_kotlin 🔐
    //
    
    //
    //
    //
    //
    //
    //
    //
    //
    // 🔑 using_sam_from_java
    //
    //
    fun onDoubleClick(listener: OnDoubleClickListener) {
        listener.onDoubleClick()
    }
    
    
    // goto using_on_double_click 🔐
}


interface LongClickListener {
    fun onLongClick() {
    }
}


//
//
//
//
//
//
//
// 🔑 side_effect
//
// 在 Kotlin 中定義 SAM 可以解決 Java 端呼叫的問題
// 但是在 Kotlin 中使用就會變得很麻煩 (沒辦法使用 lambda)
//
fun usingSAM() {
    
    val btn = Button()
    
    btn.onLongClick(object : LongClickListener {
        // do something
    })
    
    
    // 📄 請到 OnDoubleClickListener.java 看看另一個解法 another_solution 🔐
}



// 🔑 using_on_double_click
fun usingSAMFromJava(){
    
    val btn = Button()
    
    
    btn.onDoubleClick( object: OnDoubleClickListener {
        override fun onDoubleClick() {
            // do something
        }
    })
    
    btn.onDoubleClick(OnDoubleClickListener {
        // do something
    })
    
    // 📄 UsingLambda.java using_on_double_click 🔐
}