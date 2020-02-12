package akakou.ankichocaptcha.ankicho

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

object NoteRepository {
    fun read(file: File) : Note {
        val text = file.readLines().joinToString("\n")
        return NoteFactory.generate(text)
    }
}