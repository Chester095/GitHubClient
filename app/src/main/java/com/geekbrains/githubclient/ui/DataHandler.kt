package com.geekbrains.githubclient.ui

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHandler(context: Context) :
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
    fun insertContact(contact: Contact): Long {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()

        values.put(KEY_NAME, contact.login)

        val result = db.insert(TABLE_NAME, null, values)

        db.close()
        return result
    }

    // Reading all contacts from a database
    fun getAllContacts(): List<Contact> {
        val db: SQLiteDatabase = readableDatabase
        val contactList = ArrayList<Contact>()

        val selectAll = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(selectAll, null)

        if (cursor.moveToFirst()) {
            do {

                val contact = Contact(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    login = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                )
                contactList.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactList
    }

    // Updating a contact
    fun updateContact(contact: Contact): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, contact.login)

        // updating row
        val result = db.update(TABLE_NAME, contentValues, "$KEY_ID = ${contact.id}", null)

        db.close()
        return result
    }

    // Delete a contact
    fun deleteContact(contact: Contact): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, contact.id)

        // deleting row
        val result = db.delete(TABLE_NAME, "$KEY_ID = ${contact.id}", null)

        db.close()
        return result
    }

}