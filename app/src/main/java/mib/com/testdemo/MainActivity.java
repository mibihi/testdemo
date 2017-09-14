package mib.com.testdemo;

import mib.com.testdemo.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

public FragmentsManager myFragmentsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myFragmentsManager = new FragmentsManager(this,getSupportFragmentManager());
        try {
            myFragmentsManager.openHomeFragment(false,false);
        }catch(Exception e){e.printStackTrace();}
    }
}
