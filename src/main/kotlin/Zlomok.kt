fun getInt(
    text: String = "Zadaj cislo:",
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
            print("$text z intervalu <$min , $max>: ")
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
        if (men == 0)
        {
            print("Menovatel nesmie byt nula! ")
            men = getInt(ajNula = false)
        }
    }

    constructor(zlom: String) : this(0, 1)
    {
        if (zlom.indexOf("/") != -1) //je tam lonitko
        {
            val cisla = zlom.split(Regex("/"))
            cit = checkInt(ajNulaMozna = true, preved = cisla[0].trim())
            men = checkInt(ajNulaMozna = false, preved = cisla[1].trim())
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
}