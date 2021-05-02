package com.example.projectd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAllCommentActivity extends AppCompatActivity {
    ListView commentListViewInMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_comment);

        commentListViewInMenu = findViewById(R.id.listView);

        //intent 처리
        processIntent2();

    }

    public void processIntent2(){
        Intent passedIntent = getIntent();
        ArrayList<CommentItem> receivedArrayList = passedIntent.getParcelableArrayListExtra("adapterArrayList");
        CommentAdapter newAdapter = new CommentAdapter();
        newAdapter.items = receivedArrayList;
        commentListViewInMenu.setAdapter(newAdapter);
    }

     public class CommentAdapter extends BaseAdapter{
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentItemView view = new CommentItemView(getApplicationContext());

            CommentItem item = items.get(position); //item's' 에서 각position에 해당하는 item들을 선택

            view.setUserId(item.getUserId());
            view.setLastedTimeView(item.getLastedTime());
            view.setPersonalRatingView(item.getPersonalRating());
            view.setCommentView(item.getComment());
            view.setRecoCountView(item.getRecoCount());

            return view;
        }

    }


}