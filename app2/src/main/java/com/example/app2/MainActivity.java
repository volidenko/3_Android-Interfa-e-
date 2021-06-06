package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity
{
    // ----- Class constants -----------------------------------------------
    private	final	static	String	TAG	= "MainActivity";

    // ----- Class methods -------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----- Массив для списка ---------------------------------------------
        String[]	arrMonth	=
                {
                        "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                        "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
                };

        // ----- Создание списка android.widget.ListView одиночного выбора -----
		/*
		ListView	lvMonth		= (ListView) this.findViewById(R.id.lvMonth);

		lvMonth.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		ArrayAdapter<String>
				lvAdapter1		= new ArrayAdapter<>(
						this, android.R.layout.simple_list_item_single_choice, arrMonth);

		lvMonth.setAdapter(lvAdapter1);
		*/

        // ----- Создание списка android.widget.ListView множественного выбора -
        ListView	lvMonth		= (ListView) this.findViewById(R.id.lvMonth);

        lvMonth.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<String>
                lvAdapter1		= new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_multiple_choice, arrMonth);

        lvMonth.setAdapter(lvAdapter1);

        // ----- Обработка событий прокрутки списка android.widget.ListView ----
        lvMonth.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
                /*
                 *	SCROLL_STATE_IDLE = 0, список закончил прокрутку
                 *	SCROLL_STATE_TOUCH_SCROLL = 1, пользователь прокручивает список
                 *			и его палец находится на экране
                 *	SCROLL_STATE_FLING = 2, список «катнули», т.е. при прокрутке
                 *			отпустили палец и прокрутка дальше идет «по инерции»
                 */
                Log.d(TAG, "===== OnScrollStateChanged : " + scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                Log.d(TAG, "===== OnScroll : " +
                        firstVisibleItem + ":" + visibleItemCount + ":" + totalItemCount);
            }
        });

    }

    public void	btnClick(View v)
    {
        // ----- Определение выбранного элемента для списка одиночного выбора --
		/*
		ListView	lvMonth		= (ListView) this.findViewById(R.id.lvMonth);
		int			index		= lvMonth.getCheckedItemPosition();
		if (index != -1)
		{
			Toast.makeText(this,
					lvMonth.getAdapter().getItem(index).toString(), Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this,
					"Ничего не выбрано", Toast.LENGTH_SHORT).show();

		}
		*/

        // ----- Определение выбранных элементов для списка с множественным выбором
        ListView	lvMonth		= (ListView) this.findViewById(R.id.lvMonth);
        String		msg			= "";

        SparseBooleanArray sbArray = lvMonth.getCheckedItemPositions();
        for (int i = 0; i < sbArray.size(); i++)
        {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key))
            {
                msg	+= lvMonth.getAdapter().getItem(key).toString() + "\n";
            }
        }
        if (msg.isEmpty())
        {
            Toast.makeText(this,
                    "Ничего не выбрано", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this,
                    msg, Toast.LENGTH_SHORT).show();

        }
    }
}
