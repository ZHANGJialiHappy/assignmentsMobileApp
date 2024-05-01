package dk.itu.stopwatch
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit
/* This example is inspired by the StopWatch app in Head First. Android Development
http://shop.oreilly.com/product/0636920029045.do

This version is used in the Mobile App Development course by the IT University of Copenhagen
The example is used to illustrate shared objects between the five threads from the abstract version (in the concurrency note)
Jørgen Staunstrup, February 2023*/

class StopwatchActivity: AppCompatActivity() {
  private lateinit var sC: SecCounter

  //implementation of stream clock
  private val t: Thread= object: Thread() {
    // Background Thread simulating a clock ticking every 1 second
    override fun run() {
      try {
        while (true) {
          TimeUnit.SECONDS.sleep(1)
          sC.tick()
        }
      } catch (_: InterruptedException) {
      }
    }
  }

  //Android code for initialization of stopwatch
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_stopwatch)
    val startButton: Button= findViewById(R.id.start_button)
    val stopButton: Button= findViewById(R.id.stop_button)
    val resetButton: Button= findViewById(R.id.reset_button)
    val timeView: TextView= findViewById(R.id.time_view)

    //start the display stream
    val d= Display(timeView)

    // The shared object
    sC= SecCounter(d)

    //start the clock stream
    t.start()

    // The three streams for the buttons
    startButton.setOnClickListener { sC.setRunning(true) }
    stopButton.setOnClickListener { sC.setRunning(false) }
    resetButton.setOnClickListener { sC.reset() }
  }
}
