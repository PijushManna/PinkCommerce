package com.blogspot.pinkdelivery.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.blogspot.pinkdelivery.models.PinkItems
import org.json.JSONException

class AllViewModel : ViewModel() {
    private val _allItems = MutableLiveData<List<PinkItems>>()
    val allitems:LiveData<List<PinkItems>> = _allItems
    private val products = arrayOf(
        "All",
        "New",
        "Fruits",
        "Grocery",
        "Stationary"
    )

    fun fetchData(context:Context,category:String="") {
        val url = "https://reqres.in/api/users?page=2"
        val jsonResponses: MutableList<PinkItems> = ArrayList()
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val jsonArray = response.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val name = jsonObject.getString("first_name") + " " + jsonObject.getString("last_name")
                    val id = jsonObject.getString("id")
                    val img = jsonObject.getString("avatar")
                    val price = (50..100).random().toDouble()
                    val pCategory = products.random()
                    jsonResponses.add(PinkItems(id,name,pCategory,img,price))
                    Log.i("JSON Response", jsonResponses[i].toString())
                }

                _allItems.value = jsonResponses
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        requestQueue.add(jsonObjectRequest)
    }
}