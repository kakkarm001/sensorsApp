package linkedmd.tempting;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mLight;
    private Sensor mProx;
    public TextView tempView;
    public TextView lightView;


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeSensor();
        registerSensor();

        setContentView(R.layout.activity_main);
        tempView = (TextView) findViewById(R.id.textView1);
        lightView = (TextView) findViewById(R.id.textView2);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.

    }

    public void initializeSensor(){
        //write all sensor code here(excluding sensor stopping method)
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    public void registerSensor(){
        mSensorManager.registerListener(lightSensorListener, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(proxSensorListener, mProx, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener lightSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float lux = event.values[0];
            if( event.sensor.getType() == Sensor.TYPE_LIGHT)
            {
                lightView.setText("value: " + lux + " lux");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener proxSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float prox = event.values[0];
            if( event.sensor.getType() == Sensor.TYPE_PROXIMITY)
            {
                tempView.setText("value: " + prox + " prox");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}