package com.happy.bookdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcView;

    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcView = findViewById(R.id.main_rcView);
        mDatas=new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mDatas.add("item"+i);
        }
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.addItemDecoration(new LinearItemDecoration()); //设置分割线样式
        MyAdapter adapter = new MyAdapter(this,mDatas);
        rcView.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(MainActivity.this,"点击了"+postion,Toast.LENGTH_SHORT).show();
            }
        });



    }
}
