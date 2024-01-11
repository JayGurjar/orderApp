package com.example.smsreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        orderAdapter = OrderAdapter(mutableListOf())
        val rvOrderList : RecyclerView = findViewById(R.id.rvOrderList);

        rvOrderList.adapter = orderAdapter
        rvOrderList.layoutManager = LinearLayoutManager(this);

        val btnAddItem : Button = findViewById(R.id.btnAddItem);
        val etOrderText : EditText = findViewById(R.id.etOrderText);
        btnAddItem.setOnClickListener {
            val orderTitle = etOrderText.text.toString()
            if(orderTitle.isNotEmpty()){
                val order = Order(orderTitle)
                orderAdapter.addOrder(order);
                etOrderText.text.clear();
            }
        }

        val btnDeleteItem : Button = findViewById(R.id.btnDeleteItem);
        btnDeleteItem.setOnClickListener {
            orderAdapter.removeOrders()
        }
    }
}