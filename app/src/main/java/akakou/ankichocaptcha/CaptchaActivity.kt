package akakou.ankichocaptcha

import akakou.ankichocaptcha.ankicho.Note
import akakou.ankichocaptcha.ankicho.NoteRepository
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.urbandroid.sleep.captcha.CaptchaSupport
import com.urbandroid.sleep.captcha.CaptchaSupportFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Paths
import android.widget.TextView
import android.content.Intent
import android.view.View


class CaptchaActivity : Activity() {
    var captchaSupport : CaptchaSupport?  = null

    var note : Note? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        captchaSupport = CaptchaSupportFactory.create(this)

        val path = Paths.get(filesDir.toPath().toString() + "/note.csv")
        note = NoteRepository.read(path)

        renderQuestion()
    }

    fun renderQuestion() {
        val question = note!!.sendQuestion()

        if (question == null) {
            captchaSupport!!.solved()
            finish()


        } else {
            setContentView(R.layout.activity_question)
            val questionTextView : TextView = findViewById(R.id.question_text)

            questionTextView.text = note!!.sendQuestion()
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
            R.id.solve_button -> {
                note!!.recieveAnswer(true)
                renderQuestion()
            }

            R.id.unsolve_button -> {
                note!!.recieveAnswer(false)
                renderQuestion()
            }

            R.id.go_button -> {
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
