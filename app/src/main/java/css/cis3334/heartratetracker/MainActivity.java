package css.cis3334.heartratetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    HeartRateList heartRateList;        // The list of heart rate objects
    ArrayAdapter<HeartRate> hrAdapter;  // The custom array adapter for displaying the heart rates in the list view
    ListView lViewHeartRate;
    TextView textViewSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lViewHeartRate=(ListView) findViewById(R.id.lViewHeartRate);
        heartRateList = new HeartRateList();
        heartRateList.InitRandomElderly();
        textViewSelect=(TextView) findViewById(R.id.txtvwDisplayPulse);

        //This gets the heart rate list and sets it up for display
        hrAdapter = new HeartRateAdapter(this, R.layout.heart_rate_row, R.id.txtvwDisplayPulse, heartRateList);
        hrAdapter.setDropDownViewResource(R.layout.heart_rate_row);
        lViewHeartRate.setAdapter(hrAdapter);

        //This displays the information about the user's heart rate selection
        lViewHeartRate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                HeartRate hr = (HeartRate) parent.getItemAtPosition(position);

                //The app displays the range based on the user's selection
                if (hr.getRangeName()=="Resting")
                    textViewSelect.setBackgroundColor(getResources().getColor(R.color.colorResting));
                else if (hr.getRangeName()=="Moderate")
                    textViewSelect.setBackgroundColor(getResources().getColor(R.color.colorModerate));
                else if (hr.getRangeName()=="Endurance")
                    textViewSelect.setBackgroundColor(getResources().getColor(R.color.colorEndurance));
                else if (hr.getRangeName()=="Aerobic")
                    textViewSelect.setBackgroundColor(getResources().getColor(R.color.colorAerobic));
                else if (hr.getRangeName()=="Anaerobic")
                    textViewSelect.setBackgroundColor(getResources().getColor(R.color.colorAnaerobic));
                else textViewSelect.setBackgroundColor(getResources().getColor(R.color.colorRedzone));

                textViewSelect.setText("Age: "+ hr.getAge()+"\nYou selected: "+hr.toString());
            }
        });
    }
}
