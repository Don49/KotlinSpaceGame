package com.example.rocketspacegame

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.navigation.NavController
//import com.example.rocketspacegame.databinding.FragmentSecondBlankBinding

import kotlin.collections.ArrayList

class mySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs) , Runnable , SensorEventListener {
     var gameOver: Boolean = false
    var paint = Paint()
   var paintText = Paint()
    var isRunning = true
    var myThread: Thread
    var count = 0.0
    var score = 0

    lateinit var sensorMan: SensorManager
    lateinit var accelSensor:Sensor



    lateinit var prefs: SharedPreferences

    lateinit var myViewModel: MyViewModel


    var player:Player

    val asteroid = context!!.resources.getDrawable(R.drawable.asteroid)
    val rocket = context!!.resources.getDrawable(R.drawable.rocket)
    val laser = context!!.resources.getDrawable(R.drawable.laser)

    private lateinit var navController : NavController

     var myHolder: SurfaceHolder
    var gameObjectsArray = ArrayList<GameObject>()
    var bulletArray = ArrayList<Bullet>()
   // var player = ArrayList<GameObject>()

// To add More Starting obstacles uncomment objects
    //var gameObject1 : GameObject
    var gameObject2 : GameObject
     // var gameObject3 : GameObject
    var gameObject4 : GameObject
    // var gameObject5 : GameObject

    init {


        // Initialising Starting Obstacles -----------------------------
         player = Player(this,700, 200 ,0 ,0, rocket , false)
      //  gameObject1 = GameObject(700,-3000,0,10,asteroid, true)
        gameObject2 = GameObject(250,-5000,10,20,asteroid, true)
      //  gameObject3 = GameObject(700,-5000,0,10,context!!.resources.getDrawable(R.drawable.satalite), false)
        gameObject4 = GameObject(50,-9040,5,20,asteroid, true)
      //  gameObject5 = GameObject(500,-3000,1,15,asteroid , true)

        //Initialising Obstacle Array-----------------------------------

       //  gameObjectsArray.add(gameObject1)
        gameObjectsArray.add(gameObject2)
        // gameObjectsArray.add(gameObject3)
        gameObjectsArray.add(gameObject4)
        // gameObjectsArray.add(gameObject5)


        if (context != null) {
            this.sensorMan = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        }

        if (context != null) {
            prefs = context.getSharedPreferences("game" , Context.MODE_PRIVATE)
        }

        accelSensor = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorMan.registerListener(this,accelSensor,SensorManager.SENSOR_DELAY_GAME)

        //Initialising Thread ------------------------------------------

        myThread = Thread(this)
        myThread.start()
        myHolder = holder

        //Initialising PaintText ---------------------------------------

        paintText.color = Color.MAGENTA;
        paintText.textSize = 100f


    }


  /*   This Function Randomizes Obstacle parameters
     It changes the x,y coordinates as well as the speed of the object
     It also makes the object a asteroid or satellite and stops satellites from being able to be shot by players*/
    private fun randomizeObstacle(gameObject: GameObject)
    {
         gameObject.x = (10..1200).random()
         gameObject.y = (-7000..-300).random()
         gameObject.dy = (5..35).random()

        gameObject.width = 100
        gameObject.height = 200

        val imageType = (1..2).random()
        if(imageType == 1)
        {
            gameObject.image = context!!.resources.getDrawable(R.drawable.asteroid)
            gameObject.shootable = true
        }
        if (imageType == 2)
        {
            gameObject.image = context!!.resources.getDrawable(R.drawable.satalite)
            gameObject.shootable = true
        }


    }

    // Run Method of the Thread
    override fun run() {
        while(isRunning)
        {

            if(!myHolder.surface.isValid)
                continue
            val canvas: Canvas = myHolder.lockCanvas()

            //Canvas is refreshed
            canvas.drawRect(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(),paint)
            //Game update is run where movement and scoring happens
        //   player.y =  (player.height+100) + canvas.height
           player.y = canvas.height - player.height
            if(player.y > canvas.height || player.y < 0)
            {player.y = canvas.height - player.height}
            Log.d("myTag" , "leftnexss : " + player.x);
            update(canvas)
            //Text is drawn last to be on top of other objects
            drawCanText(canvas)

            myHolder.unlockCanvasAndPost(canvas)

        }


    }
    // Draws Text
    private fun drawCanText(canvas: Canvas) {
        //If game is over then thread is stopped and end score is saved and displaed
        if(gameOver){
            isRunning = false
            saveHighScore()
            drawEndScreen(canvas)
        }
            var countInt = count.toInt()
        canvas.drawText( countInt.toString() + "million Km" , 400f, 200f, paintText)
        canvas.drawText("Score: $score" , 500f , 100f , paintText)

    }

    // Tapping the screen causes player to shoot
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event?.action == MotionEvent.ACTION_DOWN)
    {
        player.shoot()
        Log.d("myTag" , "it down")
    }
        return super.onTouchEvent(event)

    }

    //Movement, planet spawning and collsions are detected here
    public fun update(canvas: Canvas)
    {
        count += 0.1
        player.move(canvas)

        if(player.x > canvas.width - player.width )
        {
            player.x = canvas.width -player.width

        }

        /*   if(x>(canvas.width-width) )
           {
               player.x = canvas.width
           }*/

        else if(player.x <10)
        {
            player.x = 11
        }


        //Log.d("myTag" , "count $count")

            // These if statments spawn planets at the same place they would appear in real life

        // Venus --------------------------------------------------------------------
        if(count > 50.3 && count < 50.4)
        {
                val venusPic = context!!.resources.getDrawable(R.drawable.venus)
                var venus = GameObject(-100,-1000,0,10,venusPic, false)
            venus.width = 800
            venus.height = 800
            gameObjectsArray.add(venus)
        }
        // Earth --------------------------------------------------------------------
        if(count > 91.7 && count < 91.8)
        {


                val earthPic = context!!.resources.getDrawable(R.drawable.earth)
                var earth = GameObject(900,-1000,0,10,earthPic, false)
            earth.width = 800
            earth.height = 800
            gameObjectsArray.add(earth)
        }
        // Mars --------------------------------------------------------------------
        if(count > 170.0 && count < 170.1)
        {

                val marsPic = context!!.resources.getDrawable(R.drawable.mars)
                var mars = GameObject(-100,-1000,0,10,marsPic, false)
            mars.width = 800
            mars.height = 800
            gameObjectsArray.add(mars)
        }
        // Jupiter --------------------------------------------------------------------
        if(count > 720.0 && count < 720.1)
        {

                val jupiterPic = context!!.resources.getDrawable(R.drawable.jupiter)
                var jupiter = GameObject(900,-1000,0,10,jupiterPic, false)
            jupiter.width = 800
            jupiter.height = 800
            gameObjectsArray.add(jupiter)
        }
        // Saturn --------------------------------------------------------------------
        if(count > 1374.0 && count < 1374.1)
        {

                val saturnPic = context!!.resources.getDrawable(R.drawable.saturn)
                var saturn = GameObject(-100,-1000,0,10,saturnPic, false)
            saturn.width = 800
            saturn.height = 800
            gameObjectsArray.add(saturn)
        }
        // Uranus --------------------------------------------------------------------
        if(count > 2813.0 && count <2813.1)
        {

                val uranusPic = context!!.resources.getDrawable(R.drawable.uranus)
                var uranus = GameObject(900,-1000,0,10,uranusPic, false)
            uranus.width = 800
            uranus.height = 800
            gameObjectsArray.add(uranus)
        }
        // Neptune --------------------------------------------------------------------
        if(count > 4435.0 && count < 4435.1)
        {

                val neptunePic = context!!.resources.getDrawable(R.drawable.neptune)
                var neptune = GameObject(-100,-1000,0,10,neptunePic, false)
            neptune.width = 800
            neptune.height = 800
            gameObjectsArray.add(neptune)
        }
        // Sadly there are no more planets

        // Checks bullet Collsion with Obsticals. Astroids add to score and positions are randomised
        for(bul in bulletArray)
        {
            bul.move(canvas)

            for(gameObject in gameObjectsArray)
            {

                if(Rect.intersects( gameObject.getCollision(), bul.getCollision()) && gameObject.shootable)
                {
                            randomizeObstacle(gameObject)
                            score++
                            bulletArray.remove(bul)
                            return

                }
            }
            // removes bullet once of screen as well as limits firerate
            if(bul.y < -3000)
            {

                bulletArray.remove(bul)
            }

        }

        // check collsions between player and Obsticals
        for(gameObject in gameObjectsArray)
        {
            gameObject.move(canvas)
            if(gameObject.y > 2700)
            {
                randomizeObstacle(gameObject)
            }
            if(Rect.intersects( gameObject.getCollision(), player.getCollision()))
            {
                gameOver = true
                Log.d("myTag", "player been hit")
                  return
            }
        }



    }


    // Creates the end scree when game is over displaying scores and previous high scores
    private fun drawEndScreen(canvas: Canvas) {

        var paintEnd = Paint()
        paintEnd.setColor(Color.DKGRAY)
        //Drawing Background ------------------------------------------------
        canvas.drawRect(200f, 400f , 1250f, 2300f, paintEnd)
        //Drawing Score -----------------------------------------------------
        canvas.drawText("Score: $score", 500f , 600f , paintText)
        canvas.drawText("Highest Score: " + prefs.getInt("highscore" , 0) , 350f , 900f , paintText)
        //Drawing Distance Measurement ---------------------------------------
        canvas.drawText("Distance: ${count.toInt()}", 400f , 1200f , paintText)
        canvas.drawText("Highest Distance: "  , 300f , 1500f , paintText)
        canvas.drawText(prefs.getInt("distanceScore" , 0).toString() , canvas.width/2f -  100, 1600f, paintText)


    }
    //Saves the high scorese in SharedPreference so they are saved even after the app is closed
    private fun saveHighScore() {
        val editor:SharedPreferences.Editor =  prefs.edit()
        //Saving highscore -------------------------------

        if(prefs.getInt("highscore",0) < score)
        {

            editor.putInt("highscore" , score)
            editor.apply()

        }
        //Saving distance ---------------------------------
        if(prefs.getInt("distanceScore" , 0) < count.toInt())
        {
            editor.putInt("distanceScore" , count.toInt())
            editor.apply()
        }
    }

    //Checks for change in accelerometer and moves player
    //Also stops player from exiting canvas
    override fun onSensorChanged(p0: SensorEvent?) {

        if(p0 == null)
        {
            return
        }
        if(p0.sensor.type == Sensor.TYPE_ACCELEROMETER )
        {
            var x = p0.values[0]
            // y,z values not needed
            var y = p0.values[1]
            var z = p0.values[2]

            if(x > 1  )
            {
                player.dx = -15
            }
            else if(x < -1  )
            {
                player.dx = 15
            }
            else
            {
                player.dx = 0
            }


        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    //Creates and moves new bullet when called
    //Only one bullet can exist at a time to avoid array memory leakage
    fun newShot() {

        var bullet = Bullet(player.x + 75, player.y - 50 , 0 , -50, laser , false)

        if(bulletArray.size < 1)
        {
            bulletArray.add(bullet)
        }

    }


}