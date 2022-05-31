package com.geekbrains.githubclient.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.geekbrains.githubclient.domain.Login

class DataHandlerLogin(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "contacts"
        const val TABLE_NAME = "contacts_table"

        const val KEY_ID = "id"
        const val KEY_NAME = "fullName"
    }

    // This method creates the database
    override fun onCreate(db: SQLiteDatabase?) {
        val createContactsTable = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
                + ")")

        db?.execSQL(createContactsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert contact
    fun insertContact(login: Login): Long {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()

        values.put(KEY_NAME, login.login)

        val result = db.insert(TABLE_NAME, null, values)

        db.close()
        return result
    }

    // Reading all contacts from a database
    fun getAllContacts(): List<Login> {
        val db: SQLiteDatabase = readableDatabase
        val loginList = ArrayList<Login>()

        val selectAll = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(selectAll, null)

        if (cursor.moveToFirst()) {
            do {

                val login = Login(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    login = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                )
                loginList.add(login)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return loginList
    }

    // Updating a contact
    fun updateContact(login: Login): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, login.login)

        // updating row
        val result = db.update(TABLE_NAME, contentValues, "$KEY_ID = ${login.id}", null)

        db.close()
        return result
    }

    // Delete a contact
    fun deleteContact(login: Login): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, login.id)

        // deleting row
        val result = db.delete(TABLE_NAME, "$KEY_ID = ${login.id}", null)

        db.close()
        return result
    }


}
