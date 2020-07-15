package com.obregon.tekro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.obregon.tekro.ui.list.UserListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setInitialLayout()
    }
    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        //supportActionBar?.setLogo(R.mipmap.ic_launcher)
        //supportActionBar?.setDisplayUseLogoEnabled(true)
    }
    private fun setInitialLayout(){
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.content,UserListFragment(),"UserListFragment")
            .addToBackStack("UserListFragment")
            .commit()
    }
}