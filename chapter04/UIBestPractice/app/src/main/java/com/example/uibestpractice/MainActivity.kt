package com.example.uibestpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()

    private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //     insets
        // }

        initMsg()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        findViewById<Button>(R.id.send).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            findViewById<Button>(R.id.send) -> {
                val inputext = findViewById<EditText>(R.id.inputText)
                val content = inputext.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SEND)
                    msgList.add(msg)
                    adapter.notifyItemInserted(msgList.size - 1)
                    findViewById<RecyclerView>(R.id.recyclerView).scrollToPosition(msgList.size -1)
                    inputext.setText("")
                }
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. who is that?", Msg.TYPE_SEND)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to youl.", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}