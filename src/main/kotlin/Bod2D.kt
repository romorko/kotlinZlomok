import kotlin.math.pow
import kotlin.math.sqrt


class NoNumber(message: String) : Exception(message)

class Bod(private var x: Float = 0.0F, private var y: Float = 0.0F) //primarny konstruktor
{
    constructor() : this(0.0F, 0.0F) //sekundarny konstruktor
    {
        while (true)
        {
            print("Zadaj suradnice v tvare: x,y: ")
            val cisla = readLine()?.split(Regex(","))
            try
            {
                x = cisla?.get(0)?.trim()?.toFloat() ?: 0.0F
                y = cisla?.get(1)?.trim()?.toFloat() ?: 0.0F
                break
            }
            catch (e: NumberFormatException)
            {
                print("Bolo najdeny nepovoleny znak! " + e.message+". ")
            }
        }
    }
    override fun toString(): String = "[$x,$y]"
    fun getDistance(other:Bod=Bod(0.0F,0.0F)): Double =sqrt(((this.x - other.x).toDouble()).pow(2.0) +(this.y-other.y)*(this.y-other.y))
    fun getCenter(other: Bod=Bod(0.0F,0.0F))=(this+other)/2.0F
    //fun getCenter(other: Bod=Bod(0.0F,0.0F))=Bod((this.x+other.x)/2,(this.y+other.y)/2)
    operator fun plus(other: Bod)=Bod(this.x+other.x,this.y+other.y)
    operator fun div(cislo:Float)=Bod(this.x/cislo,this.y/cislo)
    operator fun minus(other: Bod)=Bod(this.x-other.x,this.y-other.y)
}