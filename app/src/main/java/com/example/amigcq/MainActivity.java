package com.example.amigcq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.amigcq.model.DatabaseHelperNotifications;
import com.example.amigcq.model.NotificationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hpage);

        t1=(TextView)findViewById(R.id.info);

        t1.setMovementMethod(new ScrollingMovementMethod());



        //new


        ImageSlider imageSlider=findViewById(R.id.slider);

        List<SlideModel> slidemodels = new ArrayList<>();
        slidemodels.add(new SlideModel("https://lh5.googleusercontent.com/proxy/YY7xy8ezTQxAVfdHEGAzcUBR4vbDvzyiyLBIu8ENUU8hq347xniSzN4KI3ug_TuCtOpIfb7UAJi45L1c-LLSH4y_AA","College Entrance"));
        slidemodels.add(new SlideModel("https://storage.googleapis.com/ezap-prod/colleges/2777/142410455511.jpg","Campus"));
        slidemodels.add(new SlideModel("https://images.static-collegedunia.com/public/college_data/images/campusimage/14241045161.JPG?tr=w-205,h-130,c-force","Library"));
        slidemodels.add(new SlideModel("http://tsmistry.com/wp-content/uploads/2017/06/UNADJUSTEDNONRAW_thumb_2131.jpg","Auditorium"));
        slidemodels.add(new SlideModel("http://tsmistry.com/wp-content/uploads/2017/06/UNADJUSTEDNONRAW_thumb_2128.jpg","Lab"));



        imageSlider.setImageList(slidemodels,true);


        //floating button

        FloatingActionButton floatingActionButton=findViewById(R.id.lin);
        FloatingActionButton floatingActionButton1=findViewById(R.id.loginf_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,multiple_logins.class);
                startActivity(intent);   //multiple signup
            }
        });
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,multiple_logins.class);
                startActivity(intent);     //multiple signup
            }
        });
        //end

        //spinner

//        DatabaseHelperNotifications database = new DatabaseHelperNotifications(this);
//
//        ArrayList<NotificationModel> notifications = database.getAllNotifications();
//
//        for (int i=0; i< notifications.size(); i++)
//        {
//            System.out.println(notifications.get(i).getTitle());
//        }

    }




    public void process(View view) {
        if(view.getId()==R.id.launchmap){
            Intent intent= new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:15.226159,74.077560"));
            startActivity(intent);
        }
    }
}