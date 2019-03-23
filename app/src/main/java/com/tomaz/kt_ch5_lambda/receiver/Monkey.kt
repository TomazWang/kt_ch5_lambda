package com.tomaz.kt_ch5_lambda.receiver

class Monkey(val name: String) {
    
    fun wakeUp() {
        println("monkey $name wakes up")
    }
    
    fun sleep() {
        println("monkey $name goes to sleep")
    }
}
