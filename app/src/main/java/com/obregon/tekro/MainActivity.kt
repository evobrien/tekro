package com.obregon.tekro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.obregon.tekro.ui.list.UserListFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitialLayout()
    }

    private fun setInitialLayout(){
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.root,UserListFragment(),"UserListFragment")
            .commit()
    }
}