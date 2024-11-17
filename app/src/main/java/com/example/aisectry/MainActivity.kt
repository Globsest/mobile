package com.example.aisectry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var selectPhotosButton: Button
    private lateinit var okButton: Button
    private var selectedPhotos: List<Uri>? = null

    private val photoPickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri>? ->
        if (uris != null && uris.isNotEmpty()) {
            selectedPhotos = uris
            okButton.visibility = Button.VISIBLE // Показываем кнопку "Окей"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        selectPhotosButton = findViewById(R.id.selectPhotosButton)
        okButton = findViewById(R.id.okButton)

        selectPhotosButton.setOnClickListener {
            photoPickerLauncher.launch("image/*")
        }


        okButton.setOnClickListener {
            val intent = Intent(this, EmptyActivity::class.java)
            startActivity(intent)
        }
    }
}
