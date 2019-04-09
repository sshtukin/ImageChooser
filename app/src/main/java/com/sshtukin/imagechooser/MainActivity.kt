package com.sshtukin.imagechooser

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri


class MainActivity : AppCompatActivity() {
    val GALLERY_REQUEST_CODE = 1001
    val URI_KEY = "1002"
    var saved_uri : Uri? = null

    fun setImage(uri: Uri?){
        saved_uri = uri
        ivChosenImage.setImageURI(saved_uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenGallery.setOnClickListener {
              startActivityForResult(Intent(Intent.ACTION_PICK).setType("image/*"), GALLERY_REQUEST_CODE)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setImage(savedInstanceState?.getParcelable(URI_KEY))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) setImage(data?.data)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(URI_KEY, saved_uri)
        super.onSaveInstanceState(outState)
    }
}
