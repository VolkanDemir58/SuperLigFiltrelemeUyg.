package com.example.ileriandroid

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ileriandroid.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var CleanCode: CleanCodePage
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // string.xml dosyasında tanımlanan super_lig_teams dizisini alıp bir liste oluşturuyoruz
        val teams = resources.getStringArray(R.array.super_lig_teams).toList()
        // CleanCodePage sınıfından bir nesne oluşturuyoruz
        CleanCode = CleanCodePage(this, binding.listView, teams)
        // listview'i bağlıyoruz
        listView = findViewById(R.id.listView)


        // searchView'i bağlıyoruz ve metin değişikliği dinleyicisi ekliyoruz
        searchView = findViewById(R.id.searchView)
        // searchView'de kullanıcının arama yapması durumunda tetiklenecek metodlar tanımlanıyor.
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // CleanCode objesindeki filterList metoduna kullanıcının arama kelimesi gönderiliyor.
                CleanCode.filterList(newText)
                return false
            }
        })

        // CleanCodePage sınıfındaki setOnItemClickListener metodunu kullanarak tıklanma olayı uygulanıyor
        CleanCode.setOnItemClickListener { teamName ->
            Toast.makeText(this, teamName, Toast.LENGTH_LONG).show()
        }
    }

    // Geri butonuna basıldığında uygulamadan çıkış yapmak istenip istenmediği
    // sorusunu soran bir dialog penceresi açılıyor
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

            // AlertDialog oluşturuluyor.
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Uygulamadan çıkmak istediğinizden emin misiniz?")

            // Evet butonuna tıklandığında uygulama sonlandırılıyor.
            builder.setPositiveButton("Evet") { _: DialogInterface, _: Int ->
                finishAffinity()
            }
            // Hayır butonuna tıklandığında dialog kapatılıyor.
            builder.setNegativeButton("Hayır") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}
