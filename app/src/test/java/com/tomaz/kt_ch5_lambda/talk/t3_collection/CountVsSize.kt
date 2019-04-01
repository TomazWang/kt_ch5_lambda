package com.tomaz.kt_ch5_lambda.talk.t3_collection

import org.amshove.kluent.shouldEqualTo
import org.junit.Test

/**
 * Created by TomazWang on 2019/03/23.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/23
 **/

class CountVsSize {
    
    
    @Test
    fun countVsSize() {
        (1..20).filter { it < 7 }.count()   shouldEqualTo 6
        (1..20).count { it < 7 }            shouldEqualTo 6
        (1..20).filter { it < 7 }.size      shouldEqualTo 6
    }
    
    
}