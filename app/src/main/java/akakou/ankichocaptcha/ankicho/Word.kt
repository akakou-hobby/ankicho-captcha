package akakou.ankichocaptcha.ankicho

import java.io.Serializable

class Word (question: String, answer: String) : Serializable {
    var question = question
    var answer = answer
}