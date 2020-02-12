package akakou.ankichocaptcha

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption


class ConfigActivity : Activity() {
    private val REQUEST_CSV_GET = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "text/csv"

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CSV_GET)
        }

        Toast.makeText(this, "Select note file.", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CSV_GET && resultCode == RESULT_OK) {
            val uri = data.data
            val inputStream = contentResolver.openInputStream(uri!!)

            val destinationPath = "$filesDir/note.csv"
            val destinationFile = File(destinationPath)

            inputStream!!.copyTo(destinationFile.outputStream())
            finish()
        }
    }

}
