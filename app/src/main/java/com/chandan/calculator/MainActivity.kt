package com.chandan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.chandan.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var lastNumeric = false
    var lastDot = false
    var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View) {
        binding.tvResult.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClr(view: View) {
        binding.tvResult.text = ""
        count = 0
        lastNumeric = false
    }

    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }
    }

    fun Operators(view: View) {
        binding.tvResult.let {
            if (lastNumeric && !isOperatorAdded(it.text.toString())) {
                it.append((view as Button).text)
                lastNumeric = false
                lastDot = false
                count = 0
            }
        }

    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDot && count == 0) {
            binding.tvResult.append(".")
            lastNumeric = false
            lastDot = true
            count = 1
        } else {

        }
    }

    fun Onequal(view: View) {
        var tvValue = binding.tvResult.text.toString()
        var prefix = ""
        var noprefix = true
        if (tvValue.startsWith("-")) {
            prefix = tvValue.substring(1)
            noprefix = false
        }else{
            prefix = tvValue
        }
        if (lastNumeric && prefix.contains("-")) {
            if (!noprefix) {
                val split = prefix.split("-")
                val result = (-(split[0].toDouble() + split[1].toDouble())).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result
                }

            } else {

                val split = prefix.split("-")
                val result = (split[0].toDouble() - split[1].toDouble()).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result.removeSuffix(".0")
                }
            }
        }



        else if (lastNumeric && tvValue.contains("+")) {
            if (!noprefix) {
                val split = prefix.split("+")
                val result = (-(split[0].toDouble() - split[1].toDouble())).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result
                }
            }else {
                val split = tvValue.split("+").toMutableList()
                var one = split[0]
                val two = split[1]
                val result = (one.toDouble() + two.toDouble()).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result.removeSuffix(".0")
                }
            }


        }
        else if (lastNumeric && prefix.contains("/")) {

            if (!noprefix) {
                val split = prefix.split("/")
                val result = (-(split[0].toDouble() / split[1].toDouble())).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result
                }
            } else {


                val split = prefix.split("/")
                val result = (split[0].toDouble() / split[1].toDouble()).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result.removeSuffix(".0")
                }
            }
        }

        else if (lastNumeric && prefix.contains("*")) {

            if (!noprefix) {
                val split = prefix.split("*")
                val result = (-(split[0].toDouble() * split[1].toDouble())).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result
                }
            } else {

                val split = prefix.split("*")
                val result = (split[0].toDouble() * split[1].toDouble()).toString()
                if (result.endsWith(".0")) {
                    binding.tvResult.text = result.removeSuffix(".0")
                } else {
                    binding.tvResult.text = result.removeSuffix(".0")
                }
            }
        }
         else{
             Toast.makeText(this,"Sorry this operation is not possible",Toast.LENGTH_SHORT).show()
        }
    }
}