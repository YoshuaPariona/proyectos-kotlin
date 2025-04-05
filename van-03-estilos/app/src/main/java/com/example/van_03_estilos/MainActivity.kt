package com.example.van_03_estilos

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.van_03_estilos.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_fade)
        binding.tvAnimado.startAnimation(anim)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this,"Abriste ajustes", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun mostrarNotificacion(view:View) {
        val canalId = "canal_demo"
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(canalId, "Canal Demo", NotificationManager.IMPORTANCE_DEFAULT)
            canal.description = "Canal para notificaciones de ejemplo"
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(canal)
        }

        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, canalId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Hola desde Android")
            .setContentText("Esta es una notificación de preuba")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(1, notification)
        }
    }

     fun mostrarGrafico(view: View) {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f,10f))
        entries.add(BarEntry(2f,15f))
        entries.add(BarEntry(3f,20f))

        val dataSet = BarDataSet(entries, "visitas")
        val barData = BarData(dataSet)
        binding.barChart.data = barData
        binding.barChart.invalidate()
    }



}