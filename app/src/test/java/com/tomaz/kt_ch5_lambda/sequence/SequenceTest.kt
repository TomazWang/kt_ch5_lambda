package com.tomaz.kt_ch5_lambda.sequence

import org.junit.Test

/**
 * Created by TomazWang on 2019/03/23.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/23
 **/

class SequenceTest {
    
    private val numbers = (1..4)
    
    /**
     * 積極處理，每一個步驟都是 list in list out
     */
    @Test
    fun `eager as list`() {
        
        numbers
            .map {
                println("map $it")
                it * 2
            }
            .filter {
                println("filter $it")
                it > 4
            }
        
        // map 1
        // map 2
        // map 3
        // map 4
        // filter 2
        // filter 4
        // filter 6
        // filter 8
    }
    
    
    /**
     * 序列處理，每一個步驟都是 item in item out
     * 沒有 terminate operation 不會處理
     * 很有 Rx 的 feel
     */
    @Test
    fun `lazy as sequence`() {
        
        val result = numbers
            .asSequence()
            .map {
                println("sequence map $it")
                it * 2
            }
            .filter {
                println("sequence filter $it")
                it > 4
            }
        
        // print nothing
    }
    
    
}