package com.daydev.tictactoexo

class Game(var tableSize: Int, var consecutivePoint: Int) {

    var table = Array(tableSize){ Array(tableSize){""}}

    val player1 = "X"
    val player2 = "O"
    private var countPointX = 0
    private var countPointO = 0
    var playerTurn = 1

    fun resetTable(){
        table = Array(tableSize){Array(tableSize){""} }
        playerTurn = 1
    }

    fun isGameOver(): Boolean{
        countPointX = 0
        countPointO = 0

        if (isTableFull()){
            return true
        }

        //check Horizontal
        for (i in table.indices){
            for (j in table.indices){
                when(table[i][j]){
                    player1 -> {
                        countPointX++
                        //check j -> get table array of data that you want that can throw out of array size
                        if (countPointX!=consecutivePoint && j+1 < table.size-1 && table[i][j+1] != player1) {
                            countPointX = 0
                            continue
                        }
                    }
                    player2 -> {
                        countPointO++
                        if (countPointO != consecutivePoint && j+1 < table.size-1 && table[i][j+1] != player2) {
                            countPointO = 0
                            continue
                        }
                    }
                }
                if (countPointX==consecutivePoint || countPointO==consecutivePoint){
                    return true
                }
            }
            countPointX = 0
            countPointO = 0
        }

        //check Vertical
        for (i in table.indices){
            for (j in table.indices){
                when(table[j][i]){
                    player1 -> {
                        countPointX++
                        if (countPointX != consecutivePoint && j+1 < table.size-1 && table[j+1][i] != player1) {
                            countPointX = 0
                            continue
                        }
                    }
                    player2 -> {
                        countPointO++
                        if (countPointO != consecutivePoint && j+1 < table.size-1 && table[j+1][i] != player2) {
                            countPointO = 0
                            continue
                        }
                    }
                }
                if (countPointX==consecutivePoint || countPointO==consecutivePoint){
                    return true
                }
            }
            countPointX = 0
            countPointO = 0
        }

        //check Diagonally(1\1)
        for (i in table.indices){
            when(table[i][i]){
                player1 -> {
                    countPointX++
                    if (countPointX != consecutivePoint && i+1 < table.size-1 && table[i+1][i+1] != player1) {
                        countPointX = 0
                        continue
                    }
                }
                player2 -> {
                    countPointO++
                    if (countPointO != consecutivePoint && i+1 < table.size-1 && table[i+1][i+1] != player2) {
                        countPointO = 0
                        continue
                    }
                }
            }
            if (countPointX==consecutivePoint || countPointO==consecutivePoint){
                return true
            }
        }

        //check Diagonally(1/1)
        var j = tableSize-1
        for (i in table.indices){
            when(table[j--][i]){
                player1 -> {
                    countPointX++
                    if (countPointX != consecutivePoint && i+1 < table.size-1 && j-1 >= 0 && table[j-1][i+1] != player1) {
                        countPointX = 0
                        continue
                    }
                }
                player2 -> {
                    countPointO++
                    if (countPointO != consecutivePoint && i+1 < table.size-1 && j-1 >= 0 && table[j-1][i+1] != player2) {
                        countPointO = 0
                        continue
                    }
                }
            }
            if (countPointX==consecutivePoint || countPointO==consecutivePoint){
                return true
            }
        }

        return false
    }

    //if > 0; X win, if < 0; O win and if = 0; Equal
    fun winner(): String{
        var winner = countPointX - countPointO
        return when{
            winner > 0 -> "$player1 Win! $countPointX : $countPointO"
            winner < 0 -> "$player2 Win! $countPointX : $countPointO"
            else -> "Equal"
        }
    }

    fun nextTurn(){
        when(playerTurn){
            1 -> playerTurn = 2
            2 -> playerTurn = 1
        }
    }

    fun isTableFull(): Boolean{
        for (i in table.indices){
            for (j in table.indices){
                if (table[i][j] == ""){
                    return false
                }
            }
        }
        return true
    }


}