package com.ryusw.afreecatvteamproject.UI.Main;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryusw.afreecatvteamproject.Data.Entity.GitData;
import com.ryusw.afreecatvteamproject.Data.Entity.RepoData;
import com.ryusw.afreecatvteamproject.R;
import com.ryusw.afreecatvteamproject.UI.Main.Adapter.MainRecyclerAdapter;
import com.ryusw.afreecatvteamproject.UseCase.RepoUseCase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private RepoUseCase repoUseCase;
    private int count = 1;
    private RecyclerView recyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private ImageButton searchBtn;
    private TextView title, searchText;
    private ArrayList<RepoData> getList;
    private boolean pagingEnd, focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        inputText = findViewById(R.id.main_search_input_text);
        recyclerView = findViewById(R.id.recyclerView);
        searchBtn = findViewById(R.id.search_btn);
        title = findViewById(R.id.search_text_title);
        searchText = findViewById(R.id.search_text);

        repoUseCase = new RepoUseCase(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focus = true;
                } else {
                    focus = false;
                }
            }
        });

        searchBtn.setOnClickListener(v -> {
            //만약 커서가 있을때
            if (focus) {
                search(inputText.getText().toString());
            } else {
                inputText.setText("");
                inputText.setVisibility(View.VISIBLE);
                title.setVisibility(View.GONE);
                searchText.setVisibility(View.GONE);
                inputText.requestFocus();
                revealKeyboard();
            }
        });

        //스크롤 마지막 인지 감지하는 이벤트

        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int itemCount = layoutManager.getItemCount();
                int endItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if (endItem >= itemCount - 1) {
                    if(itemCount == 1){
                        pagingEnd = true;
                    }
                    else{
                        pagingEnd = false;
                    }
                }
                if (!pagingEnd) {
                    repoUseCase.searchRepo(inputText.getText().toString(), 10, count++);
                }
            }
        };

        recyclerView.addOnScrollListener(onScrollListener);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(v.getText().toString());
                    //데이터 전부 제거해야함
                    return true;
                }
                return false;
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void revealKeyboard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.showSoftInput(inputText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void getListData(GitData items) {
        //전체 개수로 데이터 마지막 까지 받을 수 있게
        if (items.getItems().size() == 0) {
            Toast.makeText(getApplicationContext(), "Repository does not exist", Toast.LENGTH_LONG).show();
        }
        if (getList != null) {
            mainRecyclerAdapter.dataUpdate(items.getItems());
        } else {
            getList = items.getItems();
            mainRecyclerAdapter = new MainRecyclerAdapter(getList);
            recyclerView.setAdapter(mainRecyclerAdapter);
        }
    }

    private void search(String str) {
        getList = null;
        count = 1;
        hideKeyboard();
        title.setVisibility(View.VISIBLE);
        searchText.setText(str);
        searchText.setVisibility(View.VISIBLE);
        repoUseCase.searchRepo(str, 10, count++);
        inputText.setVisibility(View.GONE);
    }
}