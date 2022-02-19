package com.example.rocketspacegame

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.content.Context
import android.graphics.Rect
import android.view.SurfaceView

open class GameObject (var x:Int, var y:Int , var dx:Int, var dy:Int, var image: Drawable , var shootable: Boolean) {
    // Class for all the items that move and have collision in the game
    open var width:Int = 250
    open var height:Int = 300

    //moving the object on the canvas. dx and dy being the speed and directions for both axis
    open fun move(canvas: Canvas)
    {
        x+=dx
        y+=dy
        if(x>(canvas.width-width) || x< 0)
            dx= -dx
     /*   if(y>(canvas.height-height) || y< 0)
            dy= -dy*/
        image.setBounds(x,y,x+width, y+width)
        image.draw(canvas)

    }

    // Creates a rectangle around object that will be used to detect collisions
    open fun getCollision() : Rect {
        return Rect(x,y, x + width, y + height)

    }




}