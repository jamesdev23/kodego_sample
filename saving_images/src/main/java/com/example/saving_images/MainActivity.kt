package com.example.saving_images

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.saving_images.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawingBoard: TouchEventView

    private val pictureChosen = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == Activity.RESULT_CANCELED){
            toast("Cancelled choosing Image")
        }else{
            val data = result.data
            if(data == null){
                toast("No image was chosen")
            }else{
                val imageFileUri = result.data!!.data!!
                drawingBoard.loadImage(imageFileUri)
                Log.i("FILE","URI : ${imageFileUri}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawingBoard = findViewById<TouchEventView>(R.id.drawing_pad)
        binding.btnSave.setOnClickListener{
            drawingBoard.saveImage()
//            drawingBoard.saveImageByMediaStore("sample")
        }

        binding.btnOpen.setOnClickListener{
            drawingBoard.loadImage()
//            val choosePictureIntent = Intent(
//                Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            )
        }

        binding.btnCamera.setOnClickListener {
            var activityCamera = Intent(this, CameraActivity::class.java)
            startActivity(activityCamera)
        }
    }

    private fun toast(message: String){
        Toast.makeText(applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}