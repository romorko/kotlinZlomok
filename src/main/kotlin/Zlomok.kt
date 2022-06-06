fun getInt(text: String = "Zadaj cislo:",
           min: Int = -100,
           max: Int = 100,
           ajNula: Boolean = true,
           lenKontrola: Boolean = false,
           kontroluj: String = ""): Int
{
    var cislo: Int
    while (true)
    {
        try
        {
            if (!lenKontrola)//ak si pytam cislo
            {
                print("$text z intervalu <$min , $max>: ")
                cislo = readLine()!!.toInt()
                if (cislo < min || cislo > max)
                {
                    throw NoNumber("Zadane cislo $cislo nepatri do stanoveneho intervalu <$min,$max>!\n")
                }
            }
            else
            {
                cislo = kontroluj.toInt()
            }
            if (ajNula == false && cislo == 0)
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

class Zlomok(var cit: Int, var men: Int = 1)
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
        if (zlom.indexOf("/") != -1)
        {
            val cisla = zlom.split(Regex("/"))
            cit = getInt(lenKontrola = true, kontroluj = cisla[0].trim())
            men = getInt(ajNula = false, lenKontrola = true, kontroluj = cisla[1].trim())
        }
        else
        {
            cit = getInt(lenKontrola = true, kontroluj = zlom)
            men = 1
        }
    }

    override fun toString(): String
    {
        return cit.toString() + "/" + men.toString()
    }
}