/* To add challenges, add them to the file located in src\main\assets\challenges.txt.
For example, if you wanted to make a drinking challenge, write the challenge in the form:

%s drink %d times.

%s will be substituted automatically for a random player, and %d will be replaced by a random number
between 2 and 5. If you want to include two players, simply add another %s. E.g.:

%s and %s, drink as many times as there are people between you (clockwise).

For simplicity, it is only possible to include two players in a challenge. This could be changed at a later stage.
It is also possible to include challenges like:

%s, do a handstand.

Or even:

Everyone in the room has to wash their hands thoroughly to prevent the spread of Covid.

Use your imagination and have fun!
 */

// todo - Read challenges from Google Sheets online if internet is available. Otherwise, read challenges from file.
// todo - Add menu at start to pick what you want to play: drinking game, romantic, dares, ...

package com.example.challengegame
import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.challengegame.R
import java.io.File
import java.io.InputStream
import kotlin.random.Random


var playerList: ArrayList<String> = ArrayList<String>()
var previousPlayer = ""
var player = ""
var player2 = ""

var challengeList : ArrayList<String> = ArrayList<String>()

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GameLoopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_loop)
        // Get the Intent that started this activity and extract the string
        playerList =  intent.getStringArrayListExtra(EXTRA_MESSAGE)
        // Read challenges from file "challenges.txt" located in src\main\assets
        val inputStream: InputStream = assets.open("challenges.txt")
        inputStream.bufferedReader().forEachLine {
            if ("%d" in it) {
                challengeList.apply { add(it.replace("%d", "${Random.nextInt(2, 5)}")) }
            }
            else {
                challengeList.apply { add(it) }
            }
        }
    }

    fun nextChallenge(view: View)
    {
        // If there are no more challenges
        if (challengeList.isEmpty() ) {
            var textView = findViewById<TextView>(R.id.textView).apply {
                text = "Game Over!"
                // todo - add play again button (brings you to main menu)
            }
        }
        else { // There are still challenges
            // If there are any names
            if (playerList.size > 0)
            {
                player = playerList.random()
            }
            // Make sure the challenges don't always target the same player
            while (player == previousPlayer && playerList.size > 1) {
                    player = playerList.random()
            }
            previousPlayer = player

            // If there are two players or more
            if (playerList.size > 1) {
                player2 = playerList.random()
                while(player2 == player){
                    player2 = playerList.random()
                }
            }

            var challenge = challengeList.random()
            var challengeAux = challenge

            challengeList.remove(challenge) // Remove it from the ArrayList before formatting
            challenge = challenge.format(player, player2)

            // Challenges that require a certain amount of players will not be used if that condition is not met
            while( ((player == "" && "%s" in challengeAux)  || player2 == "" &&  challengeAux.split("%s").dropLastWhile{it.isEmpty()}.toTypedArray().size - 1 == 2) && challengeList.size > 0 )
            {
                challenge = challengeList.random()
                challengeAux = challenge
                challengeList.remove(challenge)
                challenge = challenge.format(player,player2)
            }
            if (challengeList.isEmpty())
            {
                var textView = findViewById<TextView>(R.id.textView).apply {
                    text = "Game Over!"
                }
            }
            else {
                var textView = findViewById<TextView>(R.id.textView).apply {
                    text = challenge
                }
            }
        }
    }
}
