package com.example.listbuku

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var tambahlist: TextView
    private lateinit var tambahpenulis: EditText
    private lateinit var tambahjudul: EditText
    private lateinit var submit: Button
    private lateinit var bgblack: ImageView
    private lateinit var bginput: ImageView
    private lateinit var linear: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.Recycler)
        tambahlist = findViewById(R.id.plus)
        tambahpenulis = findViewById(R.id.inputpenulis)
        tambahjudul = findViewById(R.id.inputjudul)
        submit = findViewById(R.id.selesai)
        bgblack = findViewById(R.id.bgblack)
        bginput = findViewById(R.id.bginput)
        linear = findViewById(R.id.linearLayout)

        val data: ArrayList<buku> = ArrayList()
        data.addAll(getData())
        val adapter = adapter(this, data)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        tambahlist.setOnClickListener {
            bgblack.visibility = View.VISIBLE
            bginput.visibility = View.VISIBLE
            linear.visibility = View.VISIBLE


            submit.setOnClickListener {
                val judul = tambahjudul.text.toString()
                val penulis = tambahpenulis.text.toString()

                if (judul.isNotEmpty() && penulis.isNotEmpty()) {
                    val bukubacaan = buku()
                    bukubacaan.judul = judul
                    bukubacaan.penulis = penulis
                    data.add(bukubacaan)
                    adapter.notifyDataSetChanged()

                    tambahjudul.setText("")
                    tambahpenulis.setText("")

                    bgblack.visibility = View.GONE
                    bginput.visibility = View.GONE
                    linear.visibility = View.GONE
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Maaf Anda harus memasukkan judul buku dan nama penulis",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getData(): ArrayList<buku> {
        val data = ArrayList<buku>()
        val judul = resources.getStringArray(R.array.judul)
        val penulis = resources.getStringArray(R.array.penulis)
        for (i in judul.indices) {
            val bacaan = buku()
            bacaan.judul = judul[i]
            bacaan.penulis = penulis[i]
            data.add(bacaan)
        }
        return data
    }
}

