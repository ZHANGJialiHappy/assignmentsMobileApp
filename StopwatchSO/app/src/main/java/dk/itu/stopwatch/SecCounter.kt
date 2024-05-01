package dk.itu.stopwatch

class SecCounter(private val d: Display) {
  // Monitor because object is shared by several threads
  private var seconds= 0
  private var running= false

  @Synchronized
  fun setRunning(running: Boolean) {
    this.running= running
  }

  @Synchronized
  fun tick() {
    if (running) seconds= seconds + 1
    d.write(seconds)
  }

  @Synchronized
  fun reset() {
    seconds= 0
    running= false
    d.write(seconds)
  }
}
