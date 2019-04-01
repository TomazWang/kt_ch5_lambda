package com.tomaz.kt_ch5_lambda.playground.p1_space

/**
 * Created by TomazWang on 2019/03/29.
 *
 *
 * @author Tomaz Wang
 * @since 2019/03/29
 *
 *
 * Please refer to SpaceshipLoadingTest.kt
 *
 **/

class Spaceship(val name: String, val spaceCapacity: Int, val weightCapacity: Int) {
    
    val loading
        get() = items.toList()
    
    private val items = mutableListOf<Item>()
    
    
    val currentLoadingWeight
        get() = items.sumBy { it.weight }
    
    val currentLoadingVolume
        get() = items.sumBy { it.volume}
    
    
    fun load(item: Item): Boolean {
        return load(listOf(item))
    }
    
    
    fun load(items: Collection<Item>): Boolean {
        
        TODO()
        
        //TODO: 計算空間
        //TODO: 計算重量
        
        //TODO: 加入物件
    }
    
    
}