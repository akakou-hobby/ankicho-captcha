package akakou.ankichocaptcha.ankicho

class Question(word: Word, all: List<Word>) {
    val correctAnswer = word
    val selection : List<Word>

    init {
        val correctAnswerList = listOf(correctAnswer)

        selection = all
                .filter { it != correctAnswer }
                .shuffled()
                .take(3)
                .plus(correctAnswerList)
                .shuffled()
    }
}