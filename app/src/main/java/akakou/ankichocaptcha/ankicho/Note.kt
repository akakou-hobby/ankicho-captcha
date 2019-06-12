package akakou.ankichocaptcha.ankicho

import java.io.Serializable

class Note : Serializable {
    var words : MutableList<Word> = mutableListOf()

    var word : Word? = null

    fun sendQuestion() : String? {
        val list = words
                .shuffled()

        word = list.getOrNull(0)
        return word!!.answer
    }

    fun recieveAnswer(answer: String) : Boolean {
        if (answer != word!!.answer) return false

        words = words
                .filter { _word -> _word != word }
                .toMutableList()

        return true
    }

    fun hasNext() : Boolean{
        return words.lastIndex != 0
    }
}