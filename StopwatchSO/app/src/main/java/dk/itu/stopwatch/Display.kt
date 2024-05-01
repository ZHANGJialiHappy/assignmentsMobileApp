package dk.itu.stopwatch
import android.widget.TextView
import java.util.*

class Display(private val timeView: TextView) {
  fun write(seconds: Int) {
    val hours= seconds / 3600
    val minutes= seconds % 3600 / 60
    val secs= seconds % 60
    val time= String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
    //The call timeView.setText(time); must be posted to main thread
    timeView.post { timeView.text= time }
  }
}
