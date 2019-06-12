package akakou.ankichocaptcha.ankicho

import java.io.Serializable

class Note : Serializable {
    var words : MutableList<Word> = mutableListOf()

    var word : Word? = null

    fun sendQuestion() : String? {
        val list = words
                .shuffled()

        word = list.getOrNull(0)

        if(word == null) return null

        return word!!.question
    }

    fun recieveAnswer(hasCorrect: Boolean): String {
        if(!hasCorrect) return ""

        words = words
                .filter { _word -> _word != word }
                .toMutableList()

        return word!!.answer
    }

    fun hasNext() : Boolean{
        return words.lastIndex != 0
    }
}