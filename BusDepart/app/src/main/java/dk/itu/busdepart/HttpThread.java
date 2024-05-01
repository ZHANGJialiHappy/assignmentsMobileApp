package dk.itu.busdepart;
import android.widget.TextView;

public class HttpThread implements Runnable {
  // to pass parameters to the thread
  String url;
  TextView ui;

  public HttpThread(String url, TextView ui) {
    this.ui= ui;  this.url= url;
  }

  public void run() {
    String result= new NetworkFetcher().fetchItems(url);
    ui.post(() -> ui.append(result));

    //Why not    ui.append(result)
  }
}