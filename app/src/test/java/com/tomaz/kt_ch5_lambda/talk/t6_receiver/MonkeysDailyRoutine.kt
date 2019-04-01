@file:Suppress("unused")

package com.tomaz.kt_ch5_lambda.talk.t6_receiver

/**
 * Created by TomazWang on 2019/03/24.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/24
 **/

/** 把 wakeUp(), sleep() 這兩個重複動作拉成一個 function **/
fun monkeySaysHisDay(monkey: Monkey, words: String) {
    monkey.wakeUp()
    monkey.say(words)
    monkey.sleep()
}


// 🚀 MonkeysDailyRoutineTest#`a day of monkeys 2`




// 假設每天猴子都會先說完話在做事
// 下面這個寫法是很 OK 的
//
fun monkeyLiveHisDay(monkey: Monkey, words: String, thing: String) {
    monkey.wakeUp()
    monkey.say(words)
    monkey.does(thing)
    monkey.sleep()
}
// 但是有時候猴子會先說兩句話在做事，有時候猴子想先做事再說話
//
//
//
//


// 於是就改成這樣，讓猴子自己決定他那一天要做什麼
fun monkeyLiveHisDayWithFun(monkey: Monkey, day: (Monkey) -> Unit) {
    monkey.wakeUp()
    day(monkey)
    monkey.sleep()
}
// 🚀 MonkeysDailyRoutineTest#`a day of monkeys 3`



//
//
//
//
//
//
//
//
//
//
// 🔑 monkey_extension
//
//
fun Monkey.day() {
    this.does("One thing")
    this.say("Hi")
}


// 如果我們把這種 extension function 的寫法直接包進 function 內，讓使用者客製 `day` 的能力就消失了
fun monkeyLiveHisDayWithExtFun(monkey: Monkey) {
    monkey.wakeUp()
    monkey.day()
    monkey.sleep()
}

//
//
//
// 好加在 Kotlin 有這種寫法，直接在參數上定義一個 lambda
// 這個 lambda 當下會被視為是某個參數的 extension function
//
//
fun monkeyLiveHisDayWithLambdaWithReceiver(monkey: Monkey, theOtherDay: Monkey.() -> Unit) {
    monkey.wakeUp()
    monkey.theOtherDay()    // 外面給的，從來不存在 Monkey 的 function
    monkey.sleep()
}

// T.() -> R
//
// 這種使用 lambda 使用方法稱為 "lambda with receiver"
//
// - "T"        是 "Receiver"
// - "() -> R"  是 lambda
// - "R"        是回傳值


// 🚀 MonkeysDailyRoutineTest#`a day of monkeys 4`