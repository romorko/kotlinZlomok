class noNumber(message: String) : Exception(message)

class Bod(private var x: Int = 0, private var y: Int = 0)
{
    constructor() : this(0, 0)
    {
        while (true)
        {
            print("Zadaj suradnice v tvare: x,y: ")
            val cisla = readLine()?.split(Regex(","))
            try
            {
                x = cisla?.get(0)?.toInt() ?: 0
                y = cisla?.get(1)?.toInt() ?: 0
                break
            }
            catch (e: NumberFormatException)
            {
                print("Bolo najdeny nepovoleny znak! " + e.message+". ")
            }
        }

    }

    override fun toString(): String = "[$x,$y]"

    fun getInt(text: String = "Zadaj cislo:", min: Int = -10, max: Int = 10): Int {
        var cislo: Int
        while (true) {
            try {
                print("$text z intervalu <$min , $max>: ")
                cislo = readLine()!!.toInt()
                if (cislo < min || cislo > max) {
                    throw noNumber("Zadane cislo $cislo nepatri do stanoveneho intervalu <$min,$max>!\n")
                }
                return cislo
            }
            catch (e: NumberFormatException) {
                print("Nebolo zadane cislo!")
            }
            catch (e: noNumber) {
                print(e.message)
            }
        }
    }
}

fun main() {
    val A = Bod()
    println(A)
//println("Program arguments: ${args.joinToString()}")
}