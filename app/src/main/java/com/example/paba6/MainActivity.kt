package com.example.paba6

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var _tvAlamat : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val _btnImplisit1 = findViewById<Button>(R.id.btnImplisit1)
        _btnImplisit1.setOnClickListener {
            val _sendIntent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra("address", "08112345")
                putExtra("sms_body", "ISI SMS")
                type = "text/plain"
            }

            if (_sendIntent.resolveActivity(packageManager) != null) {
//                startActivity(_sendIntent)
                startActivity(Intent.createChooser(_sendIntent, "PILIH APLIKASI"))
            }
        }

        val _btnImplisit2 = findViewById<Button>(R.id.btnImplisit2)
        _btnImplisit2.setOnClickListener {
            val _alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "COBA ALARM")
                putExtra(AlarmClock.EXTRA_HOUR, 11)
                putExtra(AlarmClock.EXTRA_MINUTES, 40)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)

            }

            startActivity(_alarmIntent)
        }

        val _btnImplisit3 = findViewById<Button>(R.id.btnImplisit3)
        _btnImplisit3.setOnClickListener {
            val _timerIntent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "COBA TIMER")
                putExtra(AlarmClock.EXTRA_LENGTH, 40)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)

            }

            startActivity(_timerIntent)
        }

        _tvAlamat = findViewById<EditText>(R.id.tvAlamat)
        val _btnImplisit4 = findViewById<Button>(R.id.btnImplisit4)
        _btnImplisit4.setOnClickListener {
            val _webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://" + _tvAlamat.text.toString())
            )

            if (intent.resolveActivity(packageManager) != null){
                startActivity(_webIntent)
            }
        }

        val _WaktuAwal : Long = Calendar.getInstance().run {
            set(2023, 9, 19,7, 30)
            timeInMillis
        }
        val _WaktuAkhir : Long = Calendar.getInstance().run {
            set(2023, 9, 20,7, 30)
            timeInMillis
        }

        val _btnImplisit5 = findViewById<Button>(R.id.btnImplisit5)
        _btnImplisit5.setOnClickListener {
            val _eventIntent = Intent(Intent.ACTION_INSERT).apply {
                data = CalendarContract.Events.CONTENT_URI
                putExtra(CalendarContract.Events.TITLE, title)
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, _WaktuAwal)
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, _WaktuAkhir)

            }

            startActivity(_eventIntent)
        }

        val _btnImplisit6 = findViewById<Button>(R.id.btnImplisit6)
        _btnImplisit6.setOnClickListener {
            val _cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(_cameraIntent, pic_id)
        }


    }

    companion object{
        private const val pic_id = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val _tampilpic = findViewById<ImageView>(R.id.tampilpic)
        if (requestCode == pic_id && resultCode == Activity.RESULT_OK){
            val photo: Bitmap = data!!.extras!!.get("data") as Bitmap
            _tampilpic!!.setImageBitmap(photo)
        }
    }

}