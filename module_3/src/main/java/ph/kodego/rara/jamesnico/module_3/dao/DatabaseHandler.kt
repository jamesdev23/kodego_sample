package ph.kodego.rara.jamesnico.module_3.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context) : SQLiteOpenHelper(context,DATABASENAME,null,DATABASEVERSION){

    companion object {
        private val DATABASEVERSION = 3
        private val DATABASENAME = "studentdatabase"

        val tableStudents = "student_table"
        val studentId = "id"
        val studentFirstName = "firstname"
        val studentLastName = "lastname"
        val yearstarted = "yearstarted"
        val course = "course"

        val tableContacts = "contact_table"
        val contactID = "contact_id"
        val studentcontactID = "student_id"
        val contactType = "contact_type"
        val contactDetails = "contact_details"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATESTUDENTSTABLE = "CREATE TABLE $tableStudents " +
                "($studentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentFirstName TEXT, " +
                "$studentLastName TEXT, " +
                "$yearstarted TEXT, " +
                "$course INTEGER)"
        db?.execSQL(CREATESTUDENTSTABLE)


        val CREATECONTACTSTABLE = "CREATE TABLE $tableContacts " +
                "($contactID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentcontactID INTEGER, " +
                "$contactType TEXT, " +
                "$contactDetails TEXT)"
        db?.execSQL(CREATECONTACTSTABLE)



        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Valmores', 'Marco')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Aragon', 'Janreign')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Yu', 'Victor')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Navor', 'Dave')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Rara', 'James Nico')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Sorian', 'JP')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Sumaya', 'Juan Carlos')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Leones', 'Pat Ivee')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Student Sample', 'One')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Student Sample', 'Two')")

        db?.execSQL("Insert into $tableContacts ($studentcontactID, $contactType, $contactDetails) values (1, 'FACEBOOK', 'Mac Valmores')")



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableStudents")
        db!!.execSQL("DROP TABLE IF EXISTS $tableContacts")
        onCreate(db)
    }

}