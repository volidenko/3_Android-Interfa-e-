package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvMonth=(ListView)this.findViewById(R.id.lvMonth);
        String[] arrMonth={
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };
        ArrayAdapter<String> lvAdapter1=new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,arrMonth
        );
        lvMonth.setAdapter(lvAdapter1);
        lvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this,String.valueOf(position)+ " = "+
                        parent.getAdapter().getItem(position),Toast.LENGTH_SHORT).show();
            }
        });
        ListView lvDaysOfweek=(ListView)this.findViewById(R.id.lvDayOfTheWeeks);
        String[]	arrDaysOfWeek	=
                {
                        "Понедельник", "Вторник", "Среда", "Четверг",
                        "Пятница", "Суббота", "Воскресенье"
                };
        ArrayAdapter<String> lvAdapter2=new ArrayAdapter<>(
                this,R.layout.my_listview_item,arrDaysOfWeek
        );
        lvDaysOfweek.setAdapter(lvAdapter2);
    }
}