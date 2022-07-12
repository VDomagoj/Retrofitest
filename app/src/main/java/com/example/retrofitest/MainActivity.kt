package com.example.retrofitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()

        val button = findViewById<Button>(R.id.btnClick)
        button.setOnClickListener{
            call.enqueue(object : Callback<MutableList<PostModel>> {
                override fun onResponse(call: Call<MutableList<PostModel>>, response: Response<MutableList<PostModel>>) {
                    if (response.isSuccessful) {
                        Log.e("success",response.body().toString())
                    }
                }

                override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                }
        })
        }
    }
}