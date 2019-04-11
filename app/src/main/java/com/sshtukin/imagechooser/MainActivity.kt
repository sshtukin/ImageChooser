package com.sshtukin.imagechooser

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri

/**
 * Activity which loads image from gallery and sets in [ivChosenImage] by pressing [btnOpenGallery]
 *
 * @author Sergey Shtukin
 */

class MainActivity : AppCompatActivity() {

    private var savedUri : Uri = Uri.EMPTY

    private fun setImage(uri: Uri){
        savedUri = uri
        ivChosenImage.setImageURI(savedUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenGallery.setOnClickListener {
              startActivityForResult(Intent(Intent.ACTION_PICK).setType(IMAGE_INTENT_TYPE), GALLERY_REQUEST_CODE)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setImage(savedInstanceState?.getParcelable(URI_KEY)?:Uri.EMPTY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let{
                setImage(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(URI_KEY, savedUri)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        private val GALLERY_REQUEST_CODE = 1001
        private val URI_KEY = "1002"
        private val IMAGE_INTENT_TYPE = "image/*"
    }
}
