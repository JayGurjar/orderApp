package com.example.smsreader

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.order_item.view.toggleOrder
//import kotlinx.android.synthetic.main.order_item.view.tvOrderItem


class OrderAdapter (
    private val orders: MutableList<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(orderView: View) : RecyclerView.ViewHolder(orderView);
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.order_item,
                parent,
                false
            )
        )
    }

    fun addOrder(order: Order){
        orders.add(order);
        notifyItemInserted(orders.size-1);
    }

    fun removeOrders(){
        orders.removeAll { order ->
            order.isChecked
        }
        notifyDataSetChanged()
    }

    private fun strikeText(tvOrder : TextView, isChecked: Boolean){
        if(isChecked)
            tvOrder.paintFlags = tvOrder.paintFlags or STRIKE_THRU_TEXT_FLAG
        else
            tvOrder.paintFlags = tvOrder.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentOrder = orders[position]
        holder.itemView.apply {
            val tvOrderItem : TextView = findViewById(R.id.tvOrderItem);
            val toggleOrder : CheckBox = findViewById(R.id.toggleOrder);
            tvOrderItem.text = currentOrder.title
            toggleOrder.isChecked = currentOrder.isChecked
            strikeText(tvOrderItem, currentOrder.isChecked)

            toggleOrder.setOnCheckedChangeListener { _, b ->
                currentOrder.isChecked = !currentOrder.isChecked
                strikeText(tvOrderItem, currentOrder.isChecked)
            }
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}