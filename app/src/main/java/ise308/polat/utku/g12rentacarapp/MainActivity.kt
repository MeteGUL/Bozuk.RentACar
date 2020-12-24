package ise308.polat.utku.g12rentacarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var jsonsSerializer: JSONSerializer? = null
    private var carList: ArrayList<Cars>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonsSerializer = JSONSerializer("RentACar", applicationContext)

        try {
            carList = jsonsSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
        }

        initializeCars()

        val recyclerViewCars = findViewById<RecyclerView>(R.id.recyclerViewCars)
        val carsAdapter = carList?.let { CarsAdapter(it) }
        val layoutManager = LinearLayoutManager(this)
        recyclerViewCars.layoutManager = layoutManager
        recyclerViewCars.itemAnimator = DefaultItemAnimator()
        recyclerViewCars.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerViewCars.adapter = carsAdapter

    }

    private fun initializeCars() {
        carList = ArrayList<Cars>()
        carList!!.add(Cars("Opel Astra", "Hatchback", 2014, 150.0, true, "34 DD 2991"))
        carList!!.add(Cars("Ford Focus", "Sedan", 2010, 120.0, true, "06 AD 1267"))
        carList!!.add(Cars("Mercedes GLC", "SUV", 2020, 500.0, true, "38 UD 6958"))
        carList!!.add(Cars("Volkswagen Polo", "Hatchback", 2016, 90.0, true, "32 ZEY 1997"))
        carList!!.add(Cars("Audi A5", "Coupe", 2015, 250.0, false, "06 MET 235"))
    }

    private fun saveCars() {
        try {
            jsonsSerializer!!.save(this.carList!!)
        } catch (e: Exception) {
            //Log.e(TAG, "error loading notes :((")
        }
    }

    override fun onPause() {
        super.onPause()
        saveCars()
    }

}