package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        findViewById<Button>(R.id.createDatabase).setOnClickListener {
            dbHelper.writableDatabase
        }

        findViewById<Button>(R.id.addData).setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 666)
                put("price", 16.66)
            }
            db.insert("Book", null, values1)

            val values2 = ContentValues().apply {
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 218)
                put("price", 18)
            }
            db.insert("Book", null, values2)
        }

        findViewById<Button>(R.id.updateData).setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        findViewById<Button>(R.id.deleteData).setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }

        findViewById<Button>(R.id.queryData).setOnClickListener {
            val db = dbHelper.writableDatabase
            // val cursor = db.query("Book", null, null, null, null, null, null)
            val cursor = db.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.run { getColumnIndex("name") })
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))

                    val str = "Name: $name, Author: $author, Pages: $pages, Price $price"
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        findViewById<Button>(R.id.replaceData).setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book", null, null)
                // if (true) {
                //     // 手动抛出一个异常，让事务失败
                //     throw NullPointerException()
                // }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }
}