package com.example.ileriandroid
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import com.example.ileriandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //ProgressBar tanımlaması yapılıyor
    private lateinit var progressBar: ProgressBar
    //ViewBinding için binding nesnesi oluşturuluyor
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  ViewBinding'i ayarı yapılıyor

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ProgressBar'ı ayarlanıyor
        progressBar = binding.progressBar
        progressBar.max = 5000

        // Geri sayım sayacı oluşturuluyor ve başlatılıyor
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Her saniyede ProgressBar görünür hale getiriliyor.
                progressBar.visibility = View.VISIBLE
                progressBar.progress = (5000 - millisUntilFinished).toInt()
            }
            override fun onFinish() {
                // Geri sayım tamamlandığında MainActivity2'ye geçiliyor
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}