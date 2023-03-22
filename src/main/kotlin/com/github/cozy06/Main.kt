import kotlin.concurrent.thread

fun main() {
    val calculator = object {
        //사칙 연산
        val cal = object {
            var func: String? = null
            var num: Double? = null

            //덧셈
            val addition: (a: Double, b: Double) -> Unit = { a: Double, b: Double ->
                func = "+"
                num = a + b
            }

            //뺄셈
            val subtraction: (a: Double, b: Double) -> Unit = { a: Double, b: Double ->
                func = "-"
                num = a - b
            }

            //곱셈
            val multiplication: (a: Double, b: Double) -> Unit = { a: Double, b: Double ->
                func = "*"
                num = a * b
            }

            //나눗셈
            val division: (a: Double, b: Double) -> Unit = { a: Double, b: Double ->
                func = "/"
                num = a / b
            }

        }


        //계산 함수 (return: 계산값)
        fun calculate(a: Double, b: Double, function: (Double, Double) -> Unit, printTF: Boolean = true): Double? {
            function(a, b)
            if (printTF) println("$a ${cal.func} $b = ${cal.num}")
            return cal.num
        }
    }


    calculator.calculate(3.4, 2.4, calculator.cal.addition)        //덧셈
    calculator.calculate(3.4, 2.4, calculator.cal.subtraction)    //뺄셈
    calculator.calculate(3.4, 2.4, calculator.cal.multiplication) //곱셈
    calculator.calculate(3.4, 2.4, calculator.cal.division)       //나눗셈

    /* //without print
    calculator.calculate(3.4, 2.4, calculator.cal.addition, false)        //덧셈
    calculator.calculate(3.4, 2.4, calculator.cal.subtraction, false)    //뺄셈
    calculator.calculate(3.4, 2.4, calculator.cal.multiplication, false) //곱셈
    calculator.calculate(3.4, 2.4, calculator.cal.division, false)       //나눗셈 */

    // 비동기 딜레이 함수
    val async = object {
        fun delay(millis: Long, function: () -> Unit, repeat: Int = 1) {
            thread(start = true) {
                for(i: Int in 1..repeat) {
                    Thread.sleep(millis)
                    function()
                }
            }
        }
    }

    async.delay(1000, { println("1초 뒤") }, 2)

}