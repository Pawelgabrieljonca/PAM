package pbs.edu.lab05

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnLinear).setOnClickListener {
            startActivity(Intent(this, SimpleListsActivity::class.java))
        }

        findViewById<Button>(R.id.btnRelative).setOnClickListener {
            startActivity(Intent(this, HarderListsActivity::class.java))
        }

        findViewById<Button>(R.id.btnTable).setOnClickListener {
            startActivity(Intent(this, ListsActivity::class.java))
        }
    }

    // obsługa klawisza ESC (na emulatorze / fizycznej klawiaturze)
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            // przykładowe zachowanie: cofnięcie do poprzedniej aktywności
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
