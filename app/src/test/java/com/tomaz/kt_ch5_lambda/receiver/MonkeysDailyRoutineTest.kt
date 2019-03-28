package com.tomaz.kt_ch5_lambda.receiver

import com.tomaz.kt_ch5_lambda.receiver.model.Monkey
import org.junit.Test

/**
 * Created by TomazWang on 2019/03/24.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/24
 **/

@Suppress("MoveLambdaOutsideParentheses")
class MonkeysDailyRoutineTest {
    
    
    /** 基本的猴子述說自己的一天 **/
    @Test
    fun `a day of monkeys`() {
        
        val mondayMonkey = Monkey("Monday")
        val tuesdayMonkey = Monkey("Tuesday")
        val wednesdayMonkey = Monkey("Wednesday")
        
        
        mondayMonkey.wakeUp()
        mondayMonkey.say("I'm wearing new cloth.")
        mondayMonkey.sleep()
        
        
        tuesdayMonkey.wakeUp()
        tuesdayMonkey.say("I'm hungry.")
        tuesdayMonkey.sleep()
        
        
        wednesdayMonkey.wakeUp()
        wednesdayMonkey.say("I'm going hiking.")
        wednesdayMonkey.sleep()
        
        
        // 目前三隻猴子都做一樣的事情，每天都需要起床睡覺
        // 所以我們把起床睡覺讀立出來
        
        // 📄 MonkeysDailyRoutine.kt # monkeySaysHisDay
        
    }
    
    
    /** 獨立出來後，只需要關注每天要說什麼就好了**/
    @Test
    fun `a day of monkeys 2`() {
        
        val mondayMonkey = Monkey("Monday")
        val tuesdayMonkey = Monkey("Tuesday")
        val wednesdayMonkey = Monkey("Wednesday")
        
        monkeySaysHisDay(mondayMonkey, "I'm wearing new cloth.")
        monkeySaysHisDay(tuesdayMonkey, "I'm hungry.")
        monkeySaysHisDay(wednesdayMonkey, "I'm going hiking.")
        
    }
    
    // 但是如果除了說話外，猴子還需要做事情呢
    // 📄 MonkeysDailyRoutine # monkeyLiveHisDay
    
    
    @Test
    fun `a day of monkeys 3`() {
        
        val mondayMonkey = Monkey("Monday")
        val tuesdayMonkey = Monkey("Tuesday")
        val wednesdayMonkey = Monkey("Wednesday")
        
        monkeySaysHisDay(mondayMonkey, "I'm wearing new cloth.")
        monkeySaysHisDay(tuesdayMonkey, "I'm hungry.")
        monkeySaysHisDay(wednesdayMonkey, "I'm going hiking.")
        
        
        monkeyLiveHisDayWithFun(mondayMonkey, {
            it.does("The other thing")
            it.say("Hi another day.")
        })
        
        // 其實這樣看起來已經非常漂亮的了
        //
        //
        // 但為了劇情發展，稍微走點遠路
        // 把這個 lambda 轉成 Kotlin extension function: 前往 monkey_tue 🔐
        
        
        // 🔑 use_monkey_tue
        monkeyLiveHisDayWithFun(tuesdayMonkey, {
            it.tuesday()
        })
        
        
        // 回到 📄 MonkeysDailyRoutine 看看為什麼要這樣做 monkey_extension 🔐
        
        
    }
    
    // 🔑 monkey_tue
    //
    //
    //
    // 利用 Kotlin extension function 的特性，我們可以特別替 Monkey 增加 tuesday 的 function
    //
    
    fun Monkey.tuesday(monkey: Monkey) {
        monkey.does("eats some food")
        monkey.say("Hi a lovely tuesday")
    }
    
    
    //
    //
    //
    // 又因為是 extension function, 不需要把自己傳進來
    //
    
    fun Monkey.tuesday() {
        this.does("eats some food")
        this.say("Hi a lovely tuesday")
    }
    
    //
    //
    //
    //
    // 前往 use_monkey_tue 🔐
    
    
    
    
    //
    //
    //
    //
    //
    //
    //
    //
    //
    @Test
    fun `a day of monkeys 4`() {
        
        val mondayMonkey = Monkey("Monday")
        val tuesdayMonkey = Monkey("Tuesday")
        val wednesdayMonkey = Monkey("Wednesday")
        
        monkeySaysHisDay(mondayMonkey, "I'm wearing new cloth.")
        monkeySaysHisDay(tuesdayMonkey, "I'm hungry.")
        monkeySaysHisDay(wednesdayMonkey, "I'm going hiking.")
        
        
        monkeyLiveHisDayWithLambdaWithReceiver(mondayMonkey, {
            this.does("The other thing")
            this.say("Hi another day.")
        })
        
        
        //
        //
        //
        //
        // 別忘了 lambda 是最後一個參數的時候，可以獨立出去
        monkeyLiveHisDayWithLambdaWithReceiver(tuesdayMonkey) {
            this.does("The 2nd thing")
            this.say("Hi another Tuesday.")
        }
        
        //
        //
        //
        //
        // 因為視為 extension function, 所以可以省略 this
        monkeyLiveHisDayWithLambdaWithReceiver(tuesdayMonkey) {
            does("The 3rd thing")
            say("Hi another Wednesday.")
            say("Hi another Wednesday.")
            say("Hi another Wednesday.")
        }
        
        
        
        //
        //
        //
        //
        // 基本上剛剛的一大段邏輯， Kotlin 都幫我們做好了
        //
        with(mondayMonkey) {
            does("The final thing")
            say("Hi the last day.")
        }
        
        
        // 📄 Main.kt # with_friends 🔐
        
    }
    
    
}