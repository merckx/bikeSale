package com.bikesale

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.bikesale.adapters.BikeAdapter
import com.bikesale.events.OpenBikeDetailsEvent
import com.bikesale.fragments.BikeDetailsActivity
import com.bikesale.models.Bike
import com.bikesale.repository.BikeDao
import com.firebase.ui.database.FirebaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//    private var mDatabase: DatabaseReference? = null
    private var mFirebaseAdapter: FirebaseRecyclerAdapter<*, *>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//FirebaseRecyclerAdapter
        fab.setOnClickListener { view ->
            createAddBikeDialog();
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        mFirebaseAdapter = BikeAdapter()
        bikesList.adapter = mFirebaseAdapter
        bikesList.layoutManager = LinearLayoutManager(this.applicationContext)

    }

    private fun createAddBikeDialog()
    {
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
            var bikeName = dialogView.findViewById<EditText>(R.id.bike_name);
            var price = dialogView.findViewById<EditText>(R.id.bike_price);
            var bike = Bike(bikeName.text.toString(), price.text.toString().toDouble());
            BikeDao.addBike(bike);

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

    override fun onDestroy() {
        super.onDestroy()
        mFirebaseAdapter!!.cleanup()
    }



    override fun onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public val BIKE_MESSAGE: String = "BIKE";

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: OpenBikeDetailsEvent) {
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, BikeDetailsActivity::class.java).apply {
            putExtra(BIKE_MESSAGE, event.bike);
        }
        startActivity(intent)
    }



}
