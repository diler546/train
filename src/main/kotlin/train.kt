import kotlin.random.Random

class Train{
    private val cities = arrayOf("Москва",
        "Санкт-Петербург",
        "Краснодар",
        "Челябинск",
        "Екатеринбург",
        "Уфа",
        "Краснодар",
        "Пермь",
        "Воронеж",
        "Казань",
        "Владивосток",
        "Самара",
        "Нижний Новгород",
        "Выборг",
        "Новосибирск",
        "Саратов",
        "Омск"
    )

    private var beginСity = ""
    private var endСity = ""

    private var ticketCount = 0

    private var railcarCapacity = mutableListOf<Int>()

    fun add_rus_endings(input: Int, type: String):String
    {
        if (type == "ИП"){
            if (input%100 in (11..14)) return "ов"
            return when (input%10) {
                1 -> ""
                in (2..4) -> "а"
                in (5..9) -> "ов"
                0 -> "ов"
                else -> ""
            }
        }
        if (type == "РП"){
            if (input%100 in (11..14)) return "ов"
            return when (input%10) {
                1 -> "а"
                in (2..9) -> "ов"
                0 -> "ов"
                else -> ""
            }
        }
        return ""
    }

    fun route(){
        println("Шаг первый: (Выбор направления)")
        beginСity = cities[Random.nextInt(0, 16)]
        endСity = beginСity
        while (beginСity == endСity)
            endСity = cities[Random.nextInt(0, 16)]
        println("Начальный город: $beginСity")
        println("Конечный город: $endСity\n")
    }

    fun sell_tickets(){
        println("Шаг второй: (Продажа билетов)")
        ticketCount = Random.nextInt(5, 201)
        println("Было куплено $ticketCount билет${add_rus_endings(ticketCount, "ИП")}\n")
    }

    fun train_formation(){
        println("Шаг третий: (Формирование поезда)")
        var sum = 0
        while (sum < ticketCount){
            railcarCapacity.add(Random.nextInt(5, 26))
            println("Вместимость вагона ${railcarCapacity.count()}: ${railcarCapacity.last()}")
            sum += railcarCapacity.last()
        }

        println("(${railcarCapacity.count()} вагон${add_rus_endings(railcarCapacity.count(), "ИП")})\n")
    }

    fun result(){
        println("Поезд $beginСity - $endСity, состоящий из ${railcarCapacity.count()} вагон${add_rus_endings(railcarCapacity.count(), "РП")} был отправлен")
        for (i in railcarCapacity.indices){
            println("Вместимость вагона ${i + 1}: ${railcarCapacity[i]}")
            ticketCount -= railcarCapacity[i]
        }
    }
}

fun main() {
    var exit=true
    do{
        val train = Train()
        train.route()
        train.sell_tickets()
        train.train_formation()
        train.result()
        println("(Для выхода напишите EXIT, для продолжения любой набор символ)")
        when(readln().uppercase())
        {
            "EXIT" ->
            {
                println("Вокзал закрыт")
                exit = false
            }
        }
        println("---------------------------------------------")
    } while (exit)
}