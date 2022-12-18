            package com.example.orion.pokemongo2

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.widget.Toast
import androidx.appcompat.widget.ActivityChooserView
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.orion.pokemongo2.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.lang.Exception
import java.util.ArrayList
import kotlin.concurrent.thread

            class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap_
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkperm()
        loadpok()

    }
var acc = 123
    fun checkperm(){
        if (Build.VERSION.SDK_INT>=23){
                if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),acc)
                }
        }
    getloc()
    }
    fun getloc() {

        Toast.makeText(this, "user location access on", Toast.LENGTH_LONG).show()
        var myLocation=mylocationl()
        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,2f,myLocation)
        var myThread=myThread()
        myThread.start()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            acc->{
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getloc()
                }
                else{
                    Toast.makeText(this, "user have bnot given permission", Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

    }
    var locat:Location?=null
    //get user location
    inner class mylocationl:LocationListener{

        constructor(){
            locat= Location("start")
            locat!!.longitude=0.0
            locat!!.latitude=0.0
        }
        override fun onLocationChanged(p0: Location) {
            locat=p0
        }


    }

    var oldloc:Location?=null

    inner class myThread:Thread{
        constructor():super()
            {
                oldloc= Location("start")
                oldloc!!.longitude=0.0
                oldloc!!.latitude=0.0

        }

        override fun run() {
            while (true){
                try {

                    if(oldloc!!.distanceTo(locat)==0f){
                        continue
                    }
                    oldloc=locat
                    //show me
                        runOnUiThread {
                            mMap!!.clear()
                            val sydney = LatLng(locat!!.latitude, locat!!.longitude)
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(sydney)
                                    .title("ME")
                                    .snippet("here is my location with power :" + playerPower )
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario))
                            )
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f))
                            // show pockemon
                            for (i in 0..listpok.size - 1) {
                                var newpok = listpok[i]
                                if (newpok.Iscatch == false) {
                                    val pokemonloc = LatLng(
                                        newpok.location!!.latitude,
                                        newpok.location!!.longitude
                                    )
                                    mMap.addMarker(
                                        MarkerOptions()
                                            .position(pokemonloc)
                                            .title(newpok.name)
                                            .snippet(newpok.dis + ", power:"+newpok.power)
                                            .icon(BitmapDescriptorFactory.fromResource(newpok.image!!))
                                    )
                                    if (locat!!.distanceTo(newpok.location) < 2) {
                                        newpok.Iscatch = true
                                        listpok[i] = newpok
                                        playerPower += newpok.power!!
                                        Toast.makeText(
                                            applicationContext,
                                            "You catch new pockemon your new pwoer is " + playerPower,
                                            Toast.LENGTH_LONG
                                        ).show()


                                    }
                                }
                            }}
                            Thread.sleep(1000)
                        }catch (ex:Exception){

                }
            }
        }
    }
        var playerPower=76.0
    var listpok=ArrayList<pokemons>()
    fun loadpok(){
        listpok.add(pokemons(R.drawable.charmander,
            "charmander","I split fire sometimes",55,37.7789994893035,-122.401846647263 ))
        listpok.add(pokemons(R.drawable.squirtle,
            "charmander","I split water sometimes",75,37.7949568502667,-122.410494089127 ))
        listpok.add(pokemons(R.drawable.bulbasaur,
            "charmander","I love leaf sometimes",85,37.7816621152613,-122.41225361824 ))
        }
}