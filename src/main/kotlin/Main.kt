
fun getInt(text: String = "Zadaj cislo:", min: Int = -10, max: Int = 10): Int {
    var cislo: Int
    while (true) {
        try {
            print("$text z intervalu <$min , $max>: ")
            cislo = readLine()!!.toInt()
            if (cislo < min || cislo > max) {
                throw NoNumber("Zadane cislo $cislo nepatri do stanoveneho intervalu <$min,$max>!\n")
            }
            return cislo
        }
        catch (e: NumberFormatException) {
            print("Nebolo zadane cislo!")
        }
        catch (e: NoNumber) {
            print(e.message)
        }
    }
}
class NoNumber(message: String) : Exception(message)

class Bod(private var x: Int = 0, private var y: Int = 1)
{
    constructor() : this(0, 0)
    {
        while (true)
        {
            print("Zadaj suradnice v tvare: x,y: ")
            val cisla = readLine()?.split(Regex(","))
            try
            {
                x = cisla?.get(0)?.trim()?.toInt() ?: 0
                y = cisla?.get(1)?.trim()?.toInt() ?: 0
                break
            }
            catch (e: NumberFormatException)
            {
                print("Bolo najdeny nepovoleny znak! " + e.message+". ")
            }
        }
    }

    override fun toString(): String = "[$x,$y]"


}

fun main() {
    val A = Bod(3)
    println(A)
    val B = Bod(3,5)
    print(B)
    val C= Bod()
    print(C)
//println("Program arguments: ${args.joinToString()}")
}