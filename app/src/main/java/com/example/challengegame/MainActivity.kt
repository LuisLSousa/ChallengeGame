package com.example.challengegame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.challengegame.R
import kotlinx.android.synthetic.main.activity_main.*


const val EXTRA_MESSAGE = "com.example.ChallengeGame.MESSAGE"
lateinit var ll_main : LinearLayout
var numPlayer = 1

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numPlayer = 1 // When user taps the "return" button, reinitialize the variable
    }

    /** Called when the user taps the Play button */
    // https://developer.android.com/training/basics/firstapp/starting-activity
    fun play(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        var player = findViewById<EditText>(R.id.editText)
        val playerList: ArrayList<String> = ArrayList<String>()
        if (!player.text.toString().isNullOrEmpty()) {
            playerList.add(player.text.toString())
        }

        for (i in 2..numPlayer)
        {
            player = findViewById<EditText>(i)
            if (!player.text.toString().isNullOrEmpty()) {
                playerList.add(player.text.toString())
            }
        }

        //val intent = Intent(this, GameLoopActivity::class.java).apply{putExtra(EXTRA_MESSAGE, message)} // Pass only player1
        // Intent represents an app intent to do something - in this case, starts another activity (game loop)
        val intent = Intent(this, GameLoopActivity::class.java).apply{
            putStringArrayListExtra(EXTRA_MESSAGE, playerList)}
        startActivity(intent)
    }

    fun addPlayer(view: View) {
        numPlayer++
        ll_main = findViewById<LinearLayout>(R.id.linearLayout)  // or findViewById(R.id.linearLayout) as LinearLayout
        val edit_text = EditText(this)
        // setting layout_width and layout_height using layout parameters
        edit_text.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        edit_text.hint = "Player $numPlayer"
        edit_text.tag = "player$numPlayer"
        edit_text.textSize = 20.toFloat()
        edit_text.id = numPlayer
        edit_text.setTextColor(Color.parseColor("#FFFFFF"))
        edit_text.setHintTextColor(Color.parseColor("#DAD3D3"))
        edit_text.inputType = 8192 //96 = textPersonName; 8192 = textCapWords

        // Add the edit text at position "numplayer-1"
        ll_main.addView(edit_text, numPlayer-1)

        // Scroll to the last edit text added
        scrollLayout.post(fun(){
            scrollLayout.fullScroll(View.FOCUS_DOWN)
        })
    }
}
