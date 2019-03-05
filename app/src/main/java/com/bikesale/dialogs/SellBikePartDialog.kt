package com.bikesale.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.bikesale.R
import com.bikesale.events.SaveBikePartEvent
import com.bikesale.models.Bike
import com.bikesale.models.BikePart


class SellBikePartDialog : DialogFragment() {

    private var part: BikePart? = null
    private var bike: Bike? = null

    private fun setPart(part: BikePart) {
        this.part = part
    }

    private fun setBike(bike: Bike) {
        this.bike = bike
    }

    override fun onStart() {
        super.onStart()
        dialog.window!!
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dialog = inflater!!.inflate(R.layout.dialog_sell_part_view, container, false)

        val part = dialog.findViewById<TextView>(R.id.part)
        part.text = this.part!!.name + ": " + this.part!!.model

        val sellPriceText = dialog.findViewById<EditText>(R.id.sell_price)

        val sellBtn = dialog.findViewById<Button>(R.id.sell_part_btn)
        sellBtn.setOnClickListener { l ->
            this.part!!.priceSold =  java.lang.Double.parseDouble(if (sellPriceText.text != null && sellPriceText.text.isNotEmpty())
                                                                        sellPriceText.text.toString()
                                                                    else
                                                                        "0")
            SaveBikePartEvent(this.bike, this.part).execute()
            dismiss()
        }
        return dialog
    }

    companion object {

        fun show(fragmentManager: FragmentManager, part: BikePart, bike: Bike) {
            val dialog = SellBikePartDialog()
            dialog.setPart(part)
            dialog.setBike(bike)
            dialog.show(fragmentManager, "sell_bike_part_dialog")
        }
    }
}
