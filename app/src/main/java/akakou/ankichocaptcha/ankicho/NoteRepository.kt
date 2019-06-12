package akakou.ankichocaptcha.ankicho

import java.nio.file.Files
import java.nio.file.Path

object NoteRepository {
    fun read(path : Path) : Note {
        val text = Files.readAllLines(path).joinToString(separator = "\n")
        return NoteFactory.generate(text)
    }
}