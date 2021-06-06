package com.example.app3;

import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Film - Содержит информацию о фильме. Предназначен быть
 * одним элементом в наборе данных Адаптера Данных для списка
 * android.widget.ListView
 * ---------------------------------------------------------------------
 */
class	Film
{
    // ----- Class constants -----------------------------------------------
    /**
     * Название фильма
     */
    public					String	title;

    /**
     * Жанр фильма
     */
    public					String	genre;

    /**
     * Год выпуска фильма
     */
    public					int		year;

    // ----- Class methods -------------------------------------------------
    public	Film(String title, String genre, int year)
    {
        this.title	= title;
        this.genre	= genre;
        this.year	= year;
    }

    @Override
    public	String	toString()
    {
        return	"Title : " + this.title + ", Genre : " + this.genre + ", Year : " + this.year;
    }
}

public class MainActivity extends AppCompatActivity
{
    // ----- Class members -------------------------------------------------
    /**
     * Список фильмов
     */
    private					ListView	lvFilms;

    /**
     * Цвет фона не выбранного элемента списка
     */
    private 				int			nrmlColor	= Color.rgb(0xED, 0xE2, 0x75);

    /**
     * Цвет фона выбранного элемента списка
     */
    private					int			slctColor	= Color.rgb(0xE2, 0xA7, 0x6F);

    /**
     * Индекс выбранного элемента списка
     */
    private					int			curItem		= -1;

    /**
     * Ссылка на виджет текущего выбранного элемента списка
     */
    private					View		curView		= null;

    // ----- Class methods -------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----- Инициализация поля lvFilms ------------------------------------
        this.lvFilms	= (ListView)	this.findViewById(R.id.lvFilms);

        // ----- Создание коллекции фильмов ------------------------------------
        ArrayList<Film>		films	= new ArrayList<>();
		/*
		films.add(new Film("Марсианин",			"Фантастика",	2015));
		films.add(new Film("Стив Джобс",		"Биография",	2015));
		films.add(new Film("Выживший",			"Драма",		2015));
		films.add(new Film("Сестры",			"Комедия",		2016));
		films.add(new Film("Лес призраков",		"Ужасы",		2016));
		films.add(new Film("Телохранитель",		"Триллер",		2016));
		films.add(new Film("5-ая волна",		"Фантастика",	2016));
		films.add(new Film("Последний обряд",	"Ужасы",		2016));
		films.add(new Film("Новогодний отрыв",	"Комедия",		2015));
		films.add(new Film("Джой",				"Биография",	2016));
		films.add(new Film("Строго на запад",	"Вестерн",		2015));
		films.add(new Film("Статус: Свободен",	"Мелодрама",	2016));
		films.add(new Film("Кунг-Фу Панда 3",	"Мультфильм",	2016));
		films.add(new Film("Кукла",				"Ужасы",		2016));
		films.add(new Film("И грянул гром",		"Исторический",	2016));
		films.add(new Film("Защитник",			"Спорт",		2015));
		films.add(new Film("Затмение",			"Триллер",		2015));
		films.add(new Film("Дедпул",			"Приключения",	2016));
		films.add(new Film("Лазурный берег",	"Мелодрама",	2015));
		films.add(new Film("Запретная зона",	"Боевик",		2015));
		*/
        for (int i = 0; i < 50; i++)
        {
            films.add(new Film("Фильм " + (i + 1), "Жанр " + (i + 1), 2000 + i % 15));
        }

        // ----- Создание адаптера Данных и назначение его списку lvFilms ------
        ArrayAdapter<Film>	adapter	= new ArrayAdapter<Film>(this,R.layout.film_item, R.id.tvTitle, films)
        {
            @Override
            public	View	getView(int position, View convertView, ViewGroup parent)
            {
                View	view	= super.getView(position, convertView, parent);

                // ----- Получение ссылки на объект Film который отображатся в этом виджете
                Film	F		= this.getItem(position);

                // ----- Получение ссылок на виджеты TextView для отображения в них информации о фильме
                TextView	tvTitle		= (TextView)	view.findViewById(R.id.tvTitle);
                TextView	tvGenre		= (TextView)	view.findViewById(R.id.tvGenre);
                TextView	tvYear		= (TextView)	view.findViewById(R.id.tvYear);

                // ----- Запись в виджеты TextView информации о текущем фильме ----------
                tvTitle.setText(F.title);
                tvGenre.setText(F.genre);
                tvYear.setText(String.valueOf(F.year));

                // ----- Подсветка отмеченного элемента списка --------------------------
                if (position == MainActivity.this.curItem)
                {
                    view.setBackgroundColor(MainActivity.this.slctColor);
                }
                else
                {
                    view.setBackgroundColor(MainActivity.this.nrmlColor);
                }
                return	view;
            }
        };

        this.lvFilms.setAdapter(adapter);

        // ----- Назначение обработчика события клика по элементу списка -------
        this.lvFilms.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // ----- Снимаем выделение с предыдущего выделенного элемента ----------
                if (MainActivity.this.curItem != -1)
                {
                    MainActivity.this.curView.setBackgroundColor(MainActivity.this.nrmlColor);
                }

                // ----- Устанавливаем выделение на текущий элемент списка -------------
                MainActivity.this.curItem	= position;
                MainActivity.this.curView	= view;
                MainActivity.this.curView.setBackgroundColor(MainActivity.this.slctColor);
            }
        });
    }
}
