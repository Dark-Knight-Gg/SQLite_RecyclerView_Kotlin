package com.example.recyclerview_kotlin

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement
import java.sql.Statement

class DataBase : SQLiteOpenHelper {
    companion object {

        val Tag = "DatabaseHandler"
        val DBName = "ContactDB"
        val DBVersion = 1
        val Id = "Id"
        val Name = "Name"
        val Describe = "Describe"
        val Picture = "Picture"

    }

    var context: Context? = null
    var sqlObj: SQLiteDatabase

    constructor(context: Context) : super(context, DBName, null, DBVersion) {

        this.context = context;
        sqlObj = this.writableDatabase;
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    public fun Query(sql: String) {
        var sqlite: SQLiteDatabase = writableDatabase
        sqlite.execSQL(sql)
    }

    public fun Insert(name: String, describe: String, picture: ByteArray) {
        var sqlite: SQLiteDatabase = writableDatabase
        var sql = "INSERT INTO Job VALUES(null,?,?,?)"
        var statement: SQLiteStatement = sqlite.compileStatement(sql)
        statement.clearBindings()
        statement.bindString(1, name)
        statement.bindString(2, describe)
        statement.bindBlob(3, picture)
        statement.executeInsert()

    }

    public fun GetData(sql: String): Cursor {
        var sqlite: SQLiteDatabase = readableDatabase
        return sqlite.rawQuery(sql, null)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}