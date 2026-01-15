package com.example.controlhorario
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val prefs = getSharedPreferences("config", MODE_PRIVATE)

        val latInput = findViewById<EditText>(R.id.editLat)
        val lngInput = findViewById<EditText>(R.id.editLng)
        val radioInput = findViewById<EditText>(R.id.editRadio)

        latInput.setText(prefs.getString("lat", "48.7666"))
        lngInput.setText(prefs.getString("lng", "11.4257"))
        radioInput.setText(prefs.getString("radio", "100"))

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            prefs.edit().apply {
                putString("lat", latInput.text.toString())
                putString("lng", lngInput.text.toString())
                putString("radio", radioInput.text.toString())
                apply()
            }
            Toast.makeText(this, "Configuración guardada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
