package com.mostefalmr.qr_project

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class ShowStudent : AppCompatActivity() {
    var cursorS : Cursor? =null
    var cursorP : Cursor? = null
    var dbManager: DBManager? = null
    var id =-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_student)
        supportActionBar?.hide()



        val tvfName: TextView = findViewById(R.id.tvNom)
        val tvLName: TextView = findViewById(R.id.tvPrenom)
        val tvNiveau: TextView = findViewById(R.id.tvAnnee)
        val tvSection: TextView = findViewById(R.id.tvSection)
        val tvGrp: TextView = findViewById(R.id.tvGroupe)
        val tvPresCount: EditText = findViewById(R.id.tfPresence)
        val tvNote: EditText = findViewById(R.id.tvNote)

        dbManager = DBManager(this, null)
        val bundle: Bundle? = intent.extras
        if(bundle!=null) id = bundle!!.getInt("id")
        cursorS  = dbManager!!.getStudent(id.toString())
        cursorP  = dbManager!!.getNote(id.toString())

        if(cursorS!=null && cursorS!!.count!=0){
            try {
                cursorS!!.moveToFirst()
                cursorP!!.moveToFirst()
                val id = cursorS!!.getInt(0)
                val nom = cursorS!!.getString(1)
                val prenom = cursorS!!.getString(2)
                val niveau = cursorS!!.getString(3)
                val section = cursorS!!.getString(4)
                val group = cursorS!!.getString(5)
                val Note = cursorS!!.getFloat(6)
                val presCount = cursorS!!.getInt(7)

                tvfName.text = nom
                tvLName.text = prenom
                tvNiveau.text = niveau
                tvSection.text = section
                tvGrp.text = group
                Toast.makeText(this, "nnn "+presCount.toString(),Toast.LENGTH_SHORT).show()
                tvPresCount.setText(presCount.toString())
                tvNote!!.setText(Note.toString())




            }catch (e: Exception){
                //do nothing
            } }


        var btn : Button = findViewById(R.id.save)
        btn.setOnClickListener {
            dbManager!!.updateCount(id,tvPresCount.getText().toString().toInt())
            dbManager!!.updateNote(id,tvNote.getText().toString().toFloat())
        }

    }
}