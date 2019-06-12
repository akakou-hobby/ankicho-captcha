package akakou.ankichocaptcha

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.nio.file.Files
import java.nio.file.Paths

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val path = Paths.get(filesDir.toPath().toString() + "/note.csv")

        val data = Files.readAllLines(path).joinToString(separator = "\n")

        Toast.makeText(this, data, Toast.LENGTH_LONG).show()

    }
}
