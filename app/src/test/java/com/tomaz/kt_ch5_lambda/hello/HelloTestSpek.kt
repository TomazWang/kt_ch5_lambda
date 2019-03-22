

package com.tomaz.kt_ch5_lambda.hello

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by TomazWang on 2019/03/22.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/22
 *
 *
 * 這個專案中，也有部分的 Test 會用 Spek 這個 framework 寫測試
 * Spek 可以用更明確的語意撰寫 BDD like test.
 *
 *
 **/
object HelloTestSpek : Spek({
    
    Feature("打招呼功能") {
        
        val word = "hi"
        lateinit var greeting: String
        
        Scenario("觸發") {
            
            When("當有人觸發 HelloWorldSpek") {
                greeting = "$word World!"
            }
            
            Then("他應該要顯示成功") {
                greeting shouldEqual "hi World!"
            }
            
        }
    }
    
})


// 📄 after successfully running test. go to Main.kt