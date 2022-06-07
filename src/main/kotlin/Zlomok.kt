fun getInt(
    text: String = "Zadaj cislo ",
    min: Int = -100,
    max: Int = 100,
    ajNula: Boolean = true,
): Int
{
    var cislo: Int
    while (true)
    {
        try
        {
            print("$text z intervalu:<$min , $max>, nula: " + if (ajNula) "povolena: " else "zakazana: ")
            cislo = readLine()!!.toInt()
            if (cislo < min || cislo > max)
            {
                throw NoNumber("Zadane cislo $cislo nepatri do stanoveneho intervalu <$min,$max>!\n")
            }
            if (!ajNula && cislo == 0)
            {
                throw NoNumber("Cislo nemoze byt nula!\n")
            }
            return cislo
        }
        catch (e: NumberFormatException)
        {
            print("Nebolo zadane cislo!")
        }
        catch (e: NoNumber)
        {
            print(e.message)
        }
    }
}

fun checkInt(preved: String, ajNulaMozna: Boolean): Int
{
    var cislo: Int
    while (true)
    {
        try
        {
            cislo = preved.toInt()
            if (!ajNulaMozna && cislo == 0)
            {
                throw NoNumber("Cislo nemoze byt nula!\n")
            }
        }
        catch (e: java.lang.NumberFormatException)
        {
            print("Nebolo zadane cislo!")
            cislo = getInt(ajNula = ajNulaMozna)
        }
        catch (e:NoNumber)
        {
            print(e.message)
            cislo = getInt(ajNula = ajNulaMozna)
        }
        return cislo
    }
}

class Zlomok(private var cit: Int, private var men: Int = 1)
{
    init
    {
        if (this.men == 0)
        {
            print("Menovatel nesmie byt nula! ")
            this.men = getInt(ajNula = false)
        }
        val spolocny=nsd(this.cit, this.men)
        this.cit /=spolocny
        this.men /=spolocny

    }

    constructor(zlom: String) : this(0, 1)
    {
        if (zlom.indexOf("/") != -1) //je tam lonitko
        {
            val cisla = zlom.split(Regex("/"))
            this.cit = checkInt(ajNulaMozna = true, preved = cisla[0].trim())
            this.men = checkInt(ajNulaMozna = false, preved = cisla[1].trim())
            val spolocny=nsd(cit,men)
            this.cit /=spolocny
            this.men /=spolocny
        }
        else
        {
            cit = checkInt(ajNulaMozna = true, preved = zlom)
            men = 1
        }
    }

    override fun toString(): String
    {
        return "$cit/$men"
    }
    operator fun Zlomok.plus(other:Zlomok)=Zlomok(cit*other.men+men*other.cit,men*other.men)
    operator fun Zlomok.minus(other:Zlomok)=Zlomok(cit*other.men-men*other.cit,men*other.men)
    operator fun Zlomok.times(other:Zlomok)=Zlomok(cit*other.cit,men*other.men)
    operator fun Zlomok.div(other:Zlomok)=Zlomok(cit*other.men,men*other.cit)

    private fun nsd(nom:Int,den:Int):Int
    {
        var t:Int
        var d = den
        var n = nom
        while (d != 0)
        {
            t = d
            d = n % d
            n = t
        }
        return n
    }
}