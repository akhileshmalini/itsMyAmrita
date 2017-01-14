package amrita.com.itsmyamrita;

import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

/**
 * Created by Akhilesh on 7/30/2016.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        OneSignal.startInit(this).init();


    }
}
