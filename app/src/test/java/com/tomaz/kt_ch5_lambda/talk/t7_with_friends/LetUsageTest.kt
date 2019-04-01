package com.tomaz.kt_ch5_lambda.talk.t7_with_friends

import org.junit.Test

/**
 * Created by TomazWang on 2019/03/28.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/28
 *
 * T.let        ---- extension function ... 使用方法是接在目標後面
 * (T) -> R     ---- lambda ............... 傳入的 lambda 要吃一個和呼叫目標一樣 type 的參數
 * return R     ---- return value ......... 回傳的資料，就會是 lambda 的回傳值
 *
 *
 * inline fun <T, R> T.let(block: (T) -> R): R {
 *      return block(this)
 * }
 *
 *
 **/
class LetUsageTest {
    
    
    /** let 最常接續在 null check 之後 **/
    @Test
    fun `use as with null check`() {
        
        val person = getNullablePerson()
        person?.let {
            
            // 這邊只有 person 不是 null 才會執行
            it.email = "abc@email.com"
            
            // 💡 小提醒：這邊因為是在 lambda 內，使用的是 it 而不是 person
        }
        
    }
    
    
    /** 因為 let 的回傳值是 R, 所以可以當作資料轉換使用 **/
    @Test
    fun `use as data transfer`() {
        
        val people = getListOfPerson()
        
        val ages: Int = people      // people = List<Person>
            .map { it.age }         // List<Int>
            .let { it.size }        // Int
    
    
        // ⛄ 冷知識時間： let vs map
        //
        // 以上面這段程式為例
        // - let 是對整個 list 的轉型
        // - map 對每一個 item 做轉型
        
    }
    
    
}


data class Person(val name: String?, val age: Int?, var email: String?)

fun getNullablePerson(): Person? = null

fun getListOfPerson(): List<Person> = listOf()




// +-- 💡 重點整理 --------------------------------------------------------------------------------
// |
// |    - 用法:   T.let
// |    - 用途:   null check 後的行為。
// |    - 回傳:   R, lambda 回傳值
// |
// +----------------------------------------------------------------------------------------------


// 📄 WithFriends.kt # with 🔐