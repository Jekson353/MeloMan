import java.util.*

const val standardDiscount = 10000 //100 рублей
const val discountRegular = 100 //1%
const val discount = 500 //5%

fun main() {
    var summaAmount = 0 //общая сумма покупок
    val summa1minDiscount = 100100
    val summa1maxDiscount = 1000000
    val summa2Discount = 1000100
    val regularCustomer: Boolean


    val scan = Scanner(System.`in`)
    println("Добро пожаловать в магазин!")
    println("Это постоянный покупатель? Да - 1, нет - 0")
    val regular = scan.nextInt()
    regularCustomer = regular == 1

    while (true) {
        try {
            println("Введите сумму покупки")

            val summa = scan.nextDouble()
            val summaShop = (summa * 100).toInt()
            if (summa == -1.00) {
                println("Спасибо за покупки")
                break
            }

            println("   Предыдущая сумма покупок: ${convertToRouble(summaAmount)}")

            if (summaAmount < summa1minDiscount) {
                summaAmount = summaShop
                println("Скидки не применяются. Сумма текущей покупки составила: ${convertToRouble(summaShop)} рублей")
            } else if ((summaAmount < summa1maxDiscount) && (summaAmount > summa1minDiscount)) {
                println("Сумма текущей покупки: ${convertToRouble(summaShop)} ")
                summaAmount = skidFix100(summaShop)
                println("После применения скидки ${convertToRouble(standardDiscount)} рублей - ${convertToRouble(summaAmount)}")

                if (regularCustomer) {
                    summaAmount -= skidRegular(summaAmount)
                    println("После применения скидки ${convertToRouble(discountRegular)} % - ${convertToRouble(summaAmount)}")
                }

            } else if (summaAmount > summa2Discount) {
                println("Сумма текущей покупки: ${convertToRouble(summaShop)} ")
                summaAmount = skidPercent(summaShop)

                println("После применения скидки ${convertToRouble(discount)} % - ${convertToRouble(summaAmount)}")
                if (regularCustomer) {
                    summaAmount -= skidRegular(summaAmount)

                    println("После применения скидки ${convertToRouble(discountRegular)} % - ${convertToRouble(summaAmount)}")
                }
            }
        } catch (e: InputMismatchException) {
            println("Ошибка при введении данных. Допускаются только цифры. Исполнение программы было завершено. Повторите запуск вновь.")
            break
        }
    }
}

fun convertToRouble(rouble: Int): Double {
    return rouble.toDouble() / 100
}

fun skidFix100(sumShop: Int): Int {
    return sumShop - standardDiscount
}

fun skidRegular(sumShop: Int): Int {
    return (sumShop * discountRegular / 10000)
}

fun skidPercent(sumShop: Int): Int {
    return (sumShop - (sumShop * discount / 10000))
}