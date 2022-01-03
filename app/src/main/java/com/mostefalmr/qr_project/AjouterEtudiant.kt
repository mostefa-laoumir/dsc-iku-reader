package com.mostefalmr.qr_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AjouterEtudiant : AppCompatActivity() {
    var dbManager : DBManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_etudiant)
        supportActionBar?.hide()
         dbManager = DBManager(this,null)
        val addbtn : Button = findViewById(R.id.ajouterBtn)



        val idtf : EditText = findViewById(R.id.idtf)
        val nomtf : EditText = findViewById(R.id.nomtf)
        val prenomtf : EditText = findViewById(R.id.prenomtf)
        val datetf : EditText = findViewById(R.id.datetf)
        val anneetf : EditText = findViewById(R.id.anneetf)
        val sectiontf : EditText = findViewById(R.id.sectiontf)
        val grptf : EditText = findViewById(R.id.grptf)






        addbtn.setOnClickListener {
            storeBDD(idtf.text.toString().toInt(),
                nomtf.text.toString(),
                prenomtf.text.toString(),
                datetf.text.toString(),
                anneetf.text.toString(),
                sectiontf.text.toString(),
                grptf.text.toString()

                )
        }


    }
    fun storeBDD(id:Int, nom:String, prenom:String, date:String, annee:String,section:String, grp:String){
        dbManager!!.addStudent(id, nom, prenom,annee,section,grp)
    }
}