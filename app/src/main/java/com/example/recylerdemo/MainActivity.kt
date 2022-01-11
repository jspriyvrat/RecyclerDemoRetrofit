package com.example.recylerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recylerdemo.MyInter.NewsService.myInstance
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyAdapter
    lateinit var recyler: RecyclerView
    lateinit var fname:String
    lateinit var lname:String
    lateinit var email:String
    lateinit var avatar:String


    var modelData= mutableListOf<MyModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyler=findViewById(R.id.myRecycler)

        getAllData()



    }

    private fun getAllData() {
        val  datas =myInstance.getHeadLines()
        datas.enqueue(object :Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {

                val responseData= Gson().toJson(response.body())
                val jsonObj= JSONObject(responseData)
                val myArray=jsonObj.getJSONArray("data")

                for (i in 0..myArray.length()-1)
                {
                    fname=myArray.getJSONObject(i).getString("first_name")
                    lname=myArray.getJSONObject(i).getString("last_name")
                    avatar=myArray.getJSONObject(i).getString("avatar")
                    email=myArray.getJSONObject(i).getString("email")
                    modelData.add(MyModel(fname,lname,email,avatar))
                }

                adapter = MyAdapter(this@MainActivity, modelData)
                recyler.adapter=adapter
                recyler.layoutManager= LinearLayoutManager(this@MainActivity)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {

            }

        })
    }
}