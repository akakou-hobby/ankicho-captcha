package akakou.ankichocaptcha

import akakou.ankichocaptcha.ankicho.Answer
import akakou.ankichocaptcha.ankicho.Note
import akakou.ankichocaptcha.ankicho.NoteRepository
import android.app.Activity
import android.os.Bundle
import com.urbandroid.sleep.captcha.CaptchaSupport
import com.urbandroid.sleep.captcha.CaptchaSupportFactory
import android.widget.TextView
import android.view.View
import android.widget.Button
import java.io.File


class CaptchaActivity : Activity() {
    var captchaSupport : CaptchaSupport?  = null

    var note : Note? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        captchaSupport = CaptchaSupportFactory.create(this)

        val file = File("$filesDir/note.csv")
        note = NoteRepository.read(file)

        renderQuestion()
    }

    fun renderQuestion() {
        val question = note!!.question()

        if (question == null) {
            captchaSupport!!.solved()
            finish()

        } else {
            setContentView(R.layout.activity_question)

            val question = note!!.question()

            val questionTextView : TextView = findViewById(R.id.question_text)
            val answerButton0 : TextView = findViewById(R.id.answer_button0)
            val answerButton1 : TextView = findViewById(R.id.answer_button1)
            val answerButton2 : TextView = findViewById(R.id.answer_button2)
            val answerButton3 : TextView = findViewById(R.id.answer_button3)

            questionTextView.text = question!!.correctAnswer.question
            answerButton0.text = question!!.selection[0].answer
            answerButton1.text = question!!.selection[1].answer
            answerButton2.text = question!!.selection[2].answer
            answerButton3.text = question!!.selection[3].answer

        }
    }

    fun renderAnswer(){
        setContentView(R.layout.activity_answer)

        val questionTextView = findViewById<View>(R.id.question_text) as TextView
        questionTextView.text = note!!.word!!.question

        val answerTextView = findViewById<View>(R.id.answer_text) as TextView
        answerTextView.text = note!!.word!!.answer
    }


    fun onClick(v: View) {
        when (v.id) {
            R.id.next_button -> {
                renderQuestion()
            }

            R.id.skip_button -> {
                renderAnswer()
            }

            else -> {
                val answerText = findViewById<Button>(v.id).text
                var answer = Answer(answerText.toString())
                note!!.answer(answer)

                renderAnswer()
            }
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        captchaSupport!!.unsolved() // .unsolved() broadcasts an intent back to AlarmAlertFullScreen that captcha was not solved
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        captchaSupport!!.alive() // .alive() refreshes captcha timeout - intended to be sent on user interaction primarily, but can be called anytime anywhere
    }

    override fun onDestroy() {
        super.onDestroy()
        captchaSupport!!.destroy()
    }
}
