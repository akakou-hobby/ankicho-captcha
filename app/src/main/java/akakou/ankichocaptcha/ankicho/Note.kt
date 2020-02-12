package akakou.ankichocaptcha.ankicho


class Note(val all: List<Word>, size: Int = 5){
    var words : MutableList<Word> = all.map { it }.shuffled().take(size).toMutableList()
    var word : Word? = null

    fun question() : Question? {
        val list = words
                .shuffled()

        word = list.getOrNull(0)

        if(word == null) return null

        return Question(word!!, all)
    }

    fun answer(answer: Answer): Boolean {
        if(answer.text != word!!.answer) return false

        words = words
                .filter { _word -> _word != word }
                .toMutableList()

        return true
    }

    fun hasNext() : Boolean{
        return words.lastIndex != 0
    }
}