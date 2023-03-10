package com.example.diceroller

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
//import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var currentIndex : Int = 0
    //private var mSeekBarTime = findViewById<SeekBar>(R.id.seekBarTime)


    private val songs = ArrayList<Int>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val diceImage1: ImageView = findViewById(R.id.imageView)
        val rollButton: Button = findViewById(R.id.button)
        val aniRotate = AnimationUtils.loadAnimation(this,R.anim.clockwiserotate)
        // Do a dice roll when the app starts
        rollDice()
        rollButton.setOnClickListener {
            diceImage1.startAnimation(aniRotate)
            rollDice()
        }
        val play=findViewById<ImageView>(R.id.play)
        val prev=findViewById<ImageView>(R.id.prev)
        val next=findViewById<ImageView>(R.id.next)
        var mediaPlayer: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.gulabo )

        songs.add(0, R.raw.gulabo)
        songs.add(1, R.raw.highheels)
        songs.add(2, R.raw.jawaaniledoobi)
        songs.add(3, R.raw.kargayichull)
        songs.add(4, R.raw.lailamainlaila)
        songs.add(5, R.raw.nachangesaariraat)




        play.setOnClickListener {
            @Override

            if(mediaPlayer != null && mediaPlayer.isPlaying)
                {
                    mediaPlayer.pause()
                    play.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
                else
                {
                    mediaPlayer.start()
                    play.setImageResource(R.drawable.ic_baseline_pause_24)
                }
            songNames()
        }
        next.setOnClickListener {

            @Override
                if (mediaPlayer!= null) {
                    play.setImageResource(R.drawable.ic_baseline_pause_24)
                }
                if (currentIndex < songs.size - 1) {
                    currentIndex +=1
                }
                else{
                    currentIndex = 0
                }
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer = MediaPlayer.create(applicationContext, songs[currentIndex])
            songNames()
            mediaPlayer.start()

        }
        prev.setOnClickListener{
            @Override
                if (mediaPlayer != null) {
                    play.setImageResource(R.drawable.ic_baseline_pause_24)
                }
                if (currentIndex > 0) {
                    currentIndex -=1
                }
                else{
                currentIndex = songs.size - 1
                }
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer = MediaPlayer.create(applicationContext, songs[currentIndex])
            songNames()
            mediaPlayer.start()

        }
    }
    private fun songNames() {
        var songTitle : TextView = findViewById(R.id.musicTitle)
        if (currentIndex == 0) {
            songTitle.text = "Gulabo"
        }
        if (currentIndex == 1) {
            songTitle.text = "High heels"
        }
        if (currentIndex == 2) {
            songTitle.text = "Jawaani ledoobi"
        }
        if (currentIndex == 3) {
            songTitle.text = "Kargayi chull"
        }
        if (currentIndex == 4) {
            songTitle.text = "lailamainlaila"
        }
        if (currentIndex == 5) {
            songTitle.text = "Nachange saariraat"
        }
    }
    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        // generating a random value
        val diceRoll = (1..6).random()
        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()
    }

    //new
//    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//        @Override
//        fun onPrepared(MediaPlayer mp) {
//            mSeekBarTime.setMax(mMediaPlayer.getDuration());
//            mMediaPlayer.start();
//        }
//    });


}
