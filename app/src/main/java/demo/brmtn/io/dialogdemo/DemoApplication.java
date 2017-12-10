package demo.brmtn.io.dialogdemo;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author by Bramengton
 * @date 01.12.17.
 */
public class DemoApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
