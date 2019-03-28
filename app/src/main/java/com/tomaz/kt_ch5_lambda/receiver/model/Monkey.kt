package com.tomaz.kt_ch5_lambda.receiver.model

class Monkey(val name: String) {
    
    /** 猴子起床，這個動作每天早上一定會做 **/
    fun wakeUp() {
        println("monkey $name wakes up")
    }
    
    /** 猴子睡覺，這個動作每天晚上一定會做 **/
    fun sleep() {
        println("monkey $name goes to sleep")
    }
    
    
    /** 猴子說句話 **/
    fun say(words: String) {
        println("monkey $name says: \"$words\"")
    }
    
    
    /** 猴子做事情 **/
    fun does(thing: String) {
        println("monkey $name does: \"$thing\"")
    }
}



// 去看看猴子怎麼過一天
// 🚀 MonkeyDailyRoutineTest#`a day of monkeys`
