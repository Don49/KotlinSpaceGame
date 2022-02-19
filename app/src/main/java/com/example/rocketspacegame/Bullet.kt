package com.example.rocketspacegame

import android.graphics.drawable.Drawable

//Class for the bullet object inherits from GameObject Class

class Bullet( x:Int,  y:Int ,  dx:Int,  dy:Int,  image: Drawable, shootable:Boolean) : GameObject(x,y,dx,dy,image,shootable) {
    override var height = 200
    override var width = 50


}