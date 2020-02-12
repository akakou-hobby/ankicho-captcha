package akakou.ankichocaptcha.ankicho

object NoteFactory {
    fun generate(text : String, size: Int=5) : Note {
        val lines = text.split("\n")

        var all = mutableListOf<Word>()

        for (line in lines) {
            val word = WordFactory.generate(line)
            all.add(word)
        }

        val words = all.shuffled()
//                .take(size)

        return Note(words)
    }
}