package com.example.recyclerviewtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initFruits()

        // // 用于指定 RecyclerView 的布局方式
        // val layoutManager = LinearLayoutManager(this)
        // // 横向滚动
        // layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // 瀑布流
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FruitAdapter(fruitList)

    }

    private fun initFruits() {
        repeat(2) {
            // fruitList.apply {
            //     add(Fruit("apple", R.drawable.apple_pic))
            // }
            // fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            // fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            // fruitList.add(Fruit("Orange", R.drawable.orange_pic))
            // fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic))
            // fruitList.add(Fruit("Pear", R.drawable.pear_pic))
            // fruitList.add(Fruit("Grape", R.drawable.grape_pic))
            // fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic))
            // fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic))
            // fruitList.add(Fruit("Cherry", R.drawable.cherry_pic))
            // fruitList.add(Fruit("Mango", R.drawable.mango_pic))

            // 瀑布流
            fruitList.add(Fruit(getRandomLengthString("Apple"),
                R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("Banana"),
                R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("Orange"),
                R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"),
                R.drawable.watermelon_pic))
            fruitList.add(Fruit(getRandomLengthString("Pear"),
                R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthString("Grape"),
                R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"),
                R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"),
                R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthString("Cherry"),
                R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthString("Mango"),
                R.drawable.mango_pic))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}