package com.tomaz.kt_ch5_lambda.playground.space

import com.tomaz.kt_ch5_lambda.playground.space.model.Item
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldContain
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by TomazWang on 2019/03/29.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/29
 **/

object SpaceshipLoadingTest : Spek({
    
    
    Feature("裝載功能") {
        
        Scenario("可以裝載一個物件") {
            
            val spaceship = Spaceship("", 200, 300)
            
            lateinit var item: Item
            Given("一個普通物件") {
                item = Item("AAA", 20, 30)
            }
            
            
            var result = false
            
            When("裝載進 Spaceship") {
                result = spaceship.load(item)
            }
            
            Then("可以成功裝載") {
                result shouldBe true
            }
            
            Then("Spaceship loading 加重") {
                spaceship.currentLoadingWeight shouldBe  20
            }
            
            Then("Spaceship 乘載物變多") {
                spaceship.loading shouldContain item
            }
            
            
        }
        
    }
    
    
})