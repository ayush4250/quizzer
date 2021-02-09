package com.example.playearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.playearn.QuestionsActivity.FILE_NAME;
import static com.example.playearn.QuestionsActivity.KEY_NAME;

public class BookmarksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<QuestionsModel> bookmarklist;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.bookmarkrecycler);
        preferences=getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor=preferences.edit();
        gson=new Gson();

        getBookmarks();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<QuestionsModel> list = new ArrayList<>();



        BookmarkAdapter adapter = new BookmarkAdapter(bookmarklist);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        storeBookmarks();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBookmarks(){
        String json = preferences.getString(KEY_NAME,"");
        Type type = new TypeToken<List<QuestionsModel>>(){}.getType();
        bookmarklist = gson.fromJson(json,type);

        if(bookmarklist==null){
            bookmarklist=new ArrayList<>();
        }
    }



    private void storeBookmarks(){
        String json = gson.toJson(bookmarklist);

        editor.putString(KEY_NAME,json);
        editor.commit();
    }
}
