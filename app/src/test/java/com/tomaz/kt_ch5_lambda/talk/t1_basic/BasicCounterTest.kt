package com.tomaz.kt_ch5_lambda.talk.t1_basic

import org.amshove.kluent.shouldEqualTo
import org.junit.Test

/**
 * Created by TomazWang on 2019/03/22.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/22
 **/

class BasicCounterTest {
    
    
    @Test
    fun `return from lambda`(){
        val sumOfEven = { ints: Array<Int>? ->
        
            if (ints == null) {
                0 // TODO: insert return to this statement
            } else {
                var sum = 0
                for (i in ints) {
                    if (i % 2 == 0) {
                        sum += i
                    }
                }
                sum // TODO: insert return to this statement
            }
        }
    }
    
    @Test
    fun `lambda can access outer variable`(){
        
        var counter = 0
        
        val inc = { counter++ }
        
        inc()
        
        counter shouldEqualTo 1
    }
    
}