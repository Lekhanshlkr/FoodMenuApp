package com.example.foodmenu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfFoods = ArrayList<Food>()
    var adapter:FoodAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load foods
        listOfFoods.add(Food("Coffee Pot","A container for storing Coffee",R.drawable.coffee_pot))
        listOfFoods.add(Food("Espresso","A type of coffee beverage. Contains Frothed Milk",R.drawable.espresso))
        listOfFoods.add(Food("French Fries","A common fast food. Sliced potatoes, deep fried and tossed with some salt",R.drawable.french_fries))
        listOfFoods.add(Food("Honey","Natural form of sugar. Made by Honey Bees",R.drawable.honey))
        listOfFoods.add(Food("Strawberry Ice Cream","Ice Cream containing Strawberry extract.",R.drawable.strawberry_ice_cream))
        listOfFoods.add(Food("Sugar Cubes","Refined sugar grains shaped in cubes",R.drawable.sugar_cubes))

        adapter = FoodAdapter(this,listOfFoods)
        gvListFood.adapter=adapter

    }

    inner class FoodAdapter:BaseAdapter{

        var listOfFoods = ArrayList<Food>()
        var context:Context?=null

        constructor(context: Context,listOfFoods:ArrayList<Food>){
            this.context=context
            this.listOfFoods=listOfFoods
        }
        override fun getCount(): Int {
            return listOfFoods.size
        }

        override fun getItem(p0: Int): Any {
            return listOfFoods[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val food = listOfFoods[p0]
            var inflator = context!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_ticket,null)

            foodView.ivFoodIm.setImageResource(food.image!!)
            foodView.tvName.text = food.name

            foodView.ivFoodIm.setOnClickListener {
                var intent = Intent(context,FoodDetails::class.java)
                intent.putExtra("name",food.name)
                intent.putExtra("desc",food.desc)
                intent.putExtra("image",food.image)
                context!!.startActivity(intent)
            }

            return foodView

        }

    }
}