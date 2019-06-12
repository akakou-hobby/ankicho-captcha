package akakou.ankichocaptcha.ankicho

object NoteFactory {
    fun generate(text : String) : Note {
        var note = Note()
        val lines = text.split("\n")

        for (line in lines) {
            val word = WordFactory.generate(line)
            note.words.add(word)
        }

        return note
    }
}