package com.bikesale

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.bikesale.models.Bike
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bike_title_dialog.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mDatabase = FirebaseDatabase.getInstance().getReference();
//FirebaseRecyclerAdapter
        fab.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this@MainActivity)

            // Set the alert dialog title
            builder.setTitle("Enter bike details")

            // Display a message on alert dialog
//            builder.setMessage("Are you want to set the app background color to RED?")
//            val bikeName = EditText(this);
            var dialogView = this@MainActivity.layoutInflater.inflate(R.layout.bike_title_dialog, null);
            builder.setView(dialogView);
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Ok") { dialog, which ->
                // Do something when user press the positive button
                var bike = Bike();
                var bikeName = dialogView.findViewById<EditText>(R.id.bike_name);
                var price = dialogView.findViewById<EditText>(R.id.bike_price);
                bike.name =  bikeName.text.toString();
                bike.priceBought = price.text.toString().toDouble();
                mDatabase!!.child("bikes").child(bike.id).setValue(bike);
//                Toast.makeText(applicationContext, "Ok, we change the app background.", Toast.LENGTH_SHORT).show()

                // Change the app background color
//                            root_layout.setBackgroundColor(Color.RED)
            }


            // Display a negative button on alert dialog
            builder.setNegativeButton("Cancel") { dialog, which ->
//                Toast.makeText(applicationContext, "You are not agree.", Toast.LENGTH_SHORT).show()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
