package com.example.rocketspacegame

import android.graphics.Canvas
import android.graphics.drawable.Drawable

//Class for the Player object inherits from GameObject Class. Also has the ability to shoot a bullet using newShot method of the surfaceView

class Player(surfaceView: mySurfaceView, x:Int,  y:Int ,  dx:Int,  dy:Int,  image: Drawable , shootable:Boolean) : GameObject(x,y,dx,dy,image,shootable) {
    var surfaceView: mySurfaceView
    override var width = 200
    override var height = 500


    init{
        this.surfaceView = surfaceView
    }

    // Lets player shoot when called
    public fun shoot()
    {
     surfaceView.newShot()
    }

}