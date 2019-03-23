package com.tomaz.kt_ch5_lambda.col

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBeIn
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by TomazWang on 2019/03/23.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/23
 **/


/**
 * 老闆要請員工，因為公司很缺人所以每一個人都請。但是有兩個原則
 *
 * 1. 看到"富二代"就不開心，略過富二代
 * 2. 無論請幾個，都要額外安插一個"老闆兒子"
 *
 * 請試著保持用 forEach
 */
fun hireWorkers(names: List<String>): MutableList<String> {
    
    val allWorkers = mutableListOf<String>()
    
    names.forEach l@{
        allWorkers.add(it)
        
        if (it == "富二代") {
            TODO()
        }
    }
    
    allWorkers.add("老闆兒子")
    
    return allWorkers
}

object LambdaWithCollections : Spek({
    
    Feature("招募") {
        
        Scenario("盡量招募所有應徵者") {
            
            lateinit var candidates: List<String>
            lateinit var results: List<String>
            val SON = "老闆兒子"
            
            
            Given("沒有富二代") {
                candidates = listOf("Albert", "Ben", "Charlie", "Daniel", "Edward")
            }
            
            When("老闆開始招募") {
                results = hireWorkers(candidates)
            }
            
            Then("招募 5 + 1  個員工") {
                results.size shouldEqualTo 6
            }
            
            
            Then("最後一個是安插的老闆兒子") {
                results.last() shouldBe SON
            }
            
        }
        
        
        Scenario("看到富二代就不爽") {
            
            lateinit var candidates: List<String>
            lateinit var results: List<String>
            val SON = "老闆兒子"
            
            
            Given("有富二代") {
                candidates = listOf("Albert", "Ben", "富二代", "Daniel", "Edward")
            }
            
            When("老闆開始招募") {
                results = hireWorkers(candidates)
            }
            
            Then("招募 4 + 1  個員工") {
                results.size shouldEqualTo 5
            }
            
            
            Then("最後一個是安插的老闆兒子") {
                results.last() shouldBe SON
            }
            
            Then("富二代不應該被錄取") {
                "富二代" shouldNotBeIn results
            }
            
        }
    }
    
})