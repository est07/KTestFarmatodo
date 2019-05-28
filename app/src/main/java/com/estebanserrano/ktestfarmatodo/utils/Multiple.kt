package com.estebanserrano.ktestfarmatodo.utils

import com.estebanserrano.ktestfarmatodo.utils.Calculator.Companion.CONSTANT_ERROR_NUMBER
import com.estebanserrano.ktestfarmatodo.utils.Calculator.Companion.CONSTANT_NUMBER_ZERO

class Multiple {

    fun findMultipe(StringNumber: String) : String{

        val result:String

        val number:Int = Integer.parseInt(StringNumber)

        if(!isMultipe(number, 3)){
            if (!isMultipe(number, 5)){
                result = if (!isMultipe(number, 7)){
                    if (!isMultipe(number, 11)){
                        if (!isMultipe(number, 13 )){
                            CONSTANT_ERROR_NUMBER
                        }else{
                            (13).toString()
                        }
                    }else{
                        (11).toString()
                    }
                }else{
                    (7).toString()
                }
            }else{
                result = (5).toString()
            }
        }else{
            result = (3).toString()
        }

        return result
    }


    private fun isMultipe(numerator: Int, denominator: Int): Boolean {

        return numerator % denominator == CONSTANT_NUMBER_ZERO
    }
}