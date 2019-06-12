package akakou.ankichocaptcha

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.urbandroid.sleep.captcha.CaptchaSupport
import com.urbandroid.sleep.captcha.CaptchaSupportFactory


class CaptchaActivity : Activity() {
    var captchaSupport : CaptchaSupport?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captcha)

        captchaSupport = CaptchaSupportFactory.create(this)

        val button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            captchaSupport!!.solved()
            finish()
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
