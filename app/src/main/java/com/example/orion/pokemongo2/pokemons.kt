package com.example.orion.pokemongo2

import android.location.Location

class pokemons {
    var name:String?=null
    var dis:String?=null
    var image:Int?=null
    var power:Int?=null
    var location:Location?=null
    var Iscatch:Boolean?=false
    constructor(image: Int, name: String, dis: String, power: Int, lat: Double, log: Double){
        this.name=name
        this.dis=dis
        this.image=image
        this.power= power
        this.location=Location(name)
        this.location!!.latitude=lat
        this.location!!.longitude=log
        this.Iscatch=false
    }

}