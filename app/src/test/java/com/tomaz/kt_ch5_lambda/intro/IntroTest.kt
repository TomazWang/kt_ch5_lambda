@file:Suppress("unused", "ConvertCallChainIntoSequence")

package com.tomaz.kt_ch5_lambda.intro

import com.tomaz.kt_ch5_lambda.intro.model.Planet
import org.junit.Test

/**
 * Created by TomazWang on 2019/03/22.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/22
 **/
class IntroTest {
    private fun findEarthReplacements(planets: List<Planet?>): List<Pair<String, Planet>> =
        planets
            .filterNotNull()
            .filter { it.oxygen > 0.15 }
            .filter { it.water > 0.4 }
            .filter { it.temp < 80 && it.temp > -20 }
            .sortedByDescending { it.size }
            .map { it.name to it }
    
    
    @Test
    fun `run findEarthReplacements`() {
        
        val planets = listOf(
            Planet("Satum", 10, 0.03, 0.01, 300.0, 300),
            Planet("Earth", 8, 0.2, 0.72, 34.0, 100),
            null,
            Planet("Mars", 12, 0.16, 0.42, 0.43, 120),
            Planet("Jupiter", 230, 0.1, 0.2, -30.6, 300),
            Planet("Neptune", 6, 0.02, 0.08, -130.0, 1200)
        )
        
        println(
        """
        
        Scanning ....
        
        """.trimIndent()
        )
        
        findEarthReplacements(planets).forEach {
            val (name, planet) = it
            println("- $name is a stable planet which is ${planet.disFromSun} unit(s) away.")
        }
        
    }
    
}

// 📄 After you run the test, back to Main.kt