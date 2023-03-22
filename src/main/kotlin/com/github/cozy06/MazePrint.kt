package com.github.cozy06

class MazePrint {
    fun printMaze(maze: Array<IntArray>, size: Int) {
        for (i: Int in 1..size+2) {
            print("■ ")
        }
        println()
        val message = "Hello, world!"
        for (row in maze) {
            print("■ ")
            for (col in row) {
                when (col) {
                    0 -> print("□ ")
                    1 -> print("■ ")
                    2 -> print("⊞ ")
                    3 -> print("⊠ ")
                    -1 -> print("⊡ ")
                }
            }
            print("■")
            println()
        }
        for (i: Int in 1..size+2) {
            print("■ ")
        }
        println()
    }
}