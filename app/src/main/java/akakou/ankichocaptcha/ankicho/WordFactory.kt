package akakou.ankichocaptcha.ankicho

object WordFactory {
    fun generate(text: String) : Word {
        val QandA = text.split(",")
        return Word(QandA[0], QandA[1])
    }
}