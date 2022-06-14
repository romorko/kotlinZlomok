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
        catch (e: NoNumber)
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
        val spolocny = nsd(this.cit, this.men)
        this.cit /= spolocny
        this.men /= spolocny

    }

    constructor(zlom: String) : this(0, 1)
    {
        if (zlom.containsLatinMath) //ak je sucastou vyrazu operacia +-*:
        {
            val oddelovace = setOf("+","-","*",":")
            for (z in oddelovace)
            {
                if (z in zlom)
                {
                    val zlomky = zlom.split(z)
                    val c = dajZlomok(zlomky[0])
                    val m = dajZlomok(zlomky[1])
                    when(z)
                    {
                        "+"->println("$c $z $m = ${c + m}")
                        "-"->println("$c $z $m = ${c - m}")
                        "*"->println("$c $z $m = ${c * m}")
                        ":"->println("$c $z $m = ${c / m}")
                    }
                    break
                }
            }
        }
        else
        {
            dajZlomok(zlom)
        }
    }

    private fun dajZlomok(zlom: String): Zlomok
    {
        //if (zlom.indexOf("/") != -1) //je tam lonitko
        if (zlom.contains("/") )
        {
            val cisla = zlom.split(Regex("/"))
            this.cit = checkInt(ajNulaMozna = true, preved = cisla[0].trim())
            this.men = checkInt(ajNulaMozna = false, preved = cisla[1].trim())
            val spolocny = nsd(cit, men)
            this.cit /= spolocny
            this.men /= spolocny
        }
        else
        {
            cit = checkInt(ajNulaMozna = true, preved = zlom)
            men = 1
        }
        return Zlomok(this.cit,this.men)
    }

    override fun toString(): String
    {
        return "$cit/$men"
    }

    operator fun plus(other: Zlomok) = Zlomok(cit * other.men + men * other.cit, men * other.men)
    operator fun minus(other: Zlomok) = Zlomok(cit * other.men - men * other.cit, men * other.men)
    operator fun times(other: Zlomok) = Zlomok(cit * other.cit, men * other.men)
    operator fun div(other: Zlomok) = Zlomok(cit * other.men, men * other.cit)

    private val String.containsLatinMath: Boolean
        get() = matches(Regex(".*[+*:-].*"))

    private fun nsd(nom: Int, den: Int): Int
    {
        var t: Int
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