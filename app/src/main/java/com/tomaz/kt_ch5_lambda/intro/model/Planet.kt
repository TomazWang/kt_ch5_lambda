package com.tomaz.kt_ch5_lambda.intro.model

/**
 * Created by TomazWang on 2019/03/22.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/22
 *
 * 星球 class
 *
 * @property name 星球名稱
 * @property size 星球大小
 * @oxygen
 *
 **/
data class Planet(
    val name: String,
    val size: Long,
    val oxygen: Double,
    val water: Double,
    val temp: Double,
    val disFromSun: Long
)
