@file:Suppress("UNUSED_PARAMETER", "unused")

package com.tomaz.kt_ch5_lambda.with.friends.usage

import org.junit.Test

/**
 * Created by TomazWang on 2019/03/28.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/28
 *
 *
 * with()       ---- simple function ........ 使用方法是直接呼叫 with
 * T.() -> R    ---- lambda with receiver ... 傳入的 lambda 會直接執行在 receiver 身上
 * return R     ---- return value ........... 回傳的資料，就會是 lambda 的回傳值
 *
 *
 * inline fun <T, R> with(receiver: T, block: T.() -> R): R {
 *     return receiver.block()
 * }
 *
 * with 最後一個參數是 lambda with receiver
 * 並且執行在第一個參數上
 *
 * 在 { ... } 內可以視為在 T class 內執行的 function
 *
 **/
class WithUsageTest {
    
    
    /**
     * with 的特性特別適合包裝 "典禮式 (ceremony)" code block, 一堆東西需要執行在同樣的 item 上的時候
     */
    @Test
    fun `use as ceremony wrapper`() {
        val db = MockDb()
        
        with(db) {
            open()
            select(2)
            update(4, "test data")
            close()
        }
        
        
        val sp = SharedPreference()
        
        
        with(sp.edit()) {
            putString("MEMBER_ID", "id")
            putString("MEMBER_NAME", "name")
            putBoolean("IS_CENSOR", false)
        }
        
    }
    
    
}


class MockDb {
    fun open() {}
    fun close() {}
    fun select(id: Int) {}
    fun update(id: Int, data: String) {}
}


class SharedPreference{
    fun edit(): SharedPreferenceEditor = SharedPreferenceEditor()
}

class SharedPreferenceEditor {
    fun apply() {}
    fun putBoolean(key: String, b: Boolean) {}
    fun putString(key: String, s: String) {}
}


// +-- 💡 重點整理 --------------------------------------------------------------------------------
// |
// |    - 用法:   with(T) { ... }
// |    - 用途:   封裝對同一個物件大量操作的 code block
// |    - 回傳:   R, lambda 回傳值
// |
// +----------------------------------------------------------------------------------------------


// 📄 WithFriends.kt # also 🔐