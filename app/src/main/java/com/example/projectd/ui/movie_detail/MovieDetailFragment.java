package com.example.projectd.ui.movie_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectd.CommentItem;
import com.example.projectd.CommentItemView;
import com.example.projectd.CommentWriteActivity;
import com.example.projectd.FragmentCallback;
import com.example.projectd.MainActivity;
import com.example.projectd.R;
import com.example.projectd.ViewAllCommentActivity;

import java.util.ArrayList;

public class MovieDetailFragment extends Fragment {
    MainActivity activity;
    FragmentCallback callback;


    //좋아요, 싫어요 버튼 관련
    Button likeButton;
    Button dislikeButton;
    TextView likeCountTextView;
    TextView dislikeCountTextView;
    int likeCount = 15;
    int dislikeCount = 1;
    boolean likeState = false;
    boolean dislikeState = false; //


    CommentAdapter adapter;
    ListView commentListView;


    @Override
    public void onAttach(@NonNull Context context) {
        Toast.makeText(getContext(),"onAttach", Toast.LENGTH_LONG).show();
        super.onAttach(context);

        activity = (MainActivity) getActivity();

        //추가~~~
        if(context instanceof FragmentCallback){
            callback = (FragmentCallback) context;
        }
        //~~~추가
    }

    @Override
    public void onResume() {
        //여기다가 adapter 최신화!!!!!!!!!!
        Toast.makeText(getContext(),"onResume", Toast.LENGTH_LONG).show();
        Bundle receivedBundle = getArguments();
        if(receivedBundle != null){
            Float rating = receivedBundle.getFloat("rating");
            String comment = receivedBundle.getString("comment");
            adapter.addItem(new CommentItem("새로운ID", "시간", rating, comment, 0));
            commentListView.setAdapter(adapter);
        }


        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;

        //추가~~~
        if(callback != null){
            callback = null;
        }
        //~~~추가
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie_detail_info, container, false);

        //좋아요, 싫어요 버튼 관련
        likeButton = (Button) root.findViewById(R.id.likeButton);
        dislikeButton = (Button) root.findViewById(R.id.dislikeButton);
        likeCountTextView = (TextView) root.findViewById(R.id.likeCountTextView);
        dislikeCountTextView = (TextView) root.findViewById(R.id.dislikeCountTextView);
        Button seeAllButton= (Button) root.findViewById(R.id.viewAllCommentButton);
        Button writeButton= (Button) root.findViewById(R.id.writeCommentButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressLikeButton();
            }

        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressDislikeButton();
            }
        });

        //comment list view 찾기
        commentListView = root.findViewById(R.id.commentListView);
        //adapter 설정
        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("qwerty0123", "10분전", 5,"재미있는 영화입니다.", 1));
        adapter.addItem(new CommentItem("qwerty0246", "15분전", 3.5f,"무난합니다", 2));
        adapter.addItem(new CommentItem("qwerty0579", "30분전", 2,"재미없네요.", 0));
        commentListView.setAdapter(adapter);

        //작성하기, 모두보기 버튼
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"작성하기 버튼 눌림",Toast.LENGTH_LONG).show();
                if(callback != null){
                    callback.showCommentWriteActivity();
                }
                //showCommentWriteActivity(); //잠시 주석 처리
            }
        });
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"모두보기 버튼 눌림",Toast.LENGTH_LONG).show();
                showViewAllCommentActivity();
            }
        });


        return root;
    }

    public class CommentAdapter extends BaseAdapter {
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
            CommentItemView view = new CommentItemView(getContext());

            CommentItem item = items.get(position); //item's' 에서 각position에 해당하는 item들을 선택

            view.setUserId(item.getUserId());
            view.setLastedTimeView(item.getLastedTime());
            view.setPersonalRatingView(item.getPersonalRating());
            view.setCommentView(item.getComment());
            view.setRecoCountView(item.getRecoCount());

            return view;
        }
    }

    //좋아요, 싫어요 버튼 관련 함수
    public void pressLikeButton(){
        if(likeState==false && dislikeState==false){ //초기상태에서 like눌리는 경우
            likeIsOrange();
        } else if (likeState==true && dislikeState==false){ //like취소 하는 경우
            likeIsWhite();
        } else if (likeState==false && dislikeState==true){ //dislike가 눌린 상태에서 like 누르는 경우
            dislikeIsWhite();
            likeIsOrange();
        }
        likeCountTextView.setText(String.valueOf(likeCount));
        dislikeCountTextView.setText(String.valueOf(dislikeCount));
    }
    public void pressDislikeButton(){
        if(likeState==false && dislikeState==false){ //초기상태에서 dislike눌리는 경우
            dislikeIsOrange();
        } else if (likeState==false && dislikeState==true){ //dislike취소 하는 경우
            dislikeIsWhite();
        } else if (likeState==true && dislikeState==false){ //like가 눌린 상태에서 dislike 누르는 경우
            likeIsWhite();
            dislikeIsOrange();
        }
        likeCountTextView.setText(String.valueOf(likeCount));
        dislikeCountTextView.setText(String.valueOf(dislikeCount));
    }
    public void likeIsOrange(){
        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        likeState = true;
        likeCount++;
    }
    public void likeIsWhite(){
        likeButton.setBackgroundResource(R.drawable.ic_thumb_up);
        likeState = false;
        likeCount--;
    }
    public void dislikeIsOrange(){
        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        dislikeState = true;
        dislikeCount++;
    }
    public void dislikeIsWhite(){
        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down);
        dislikeState = false;
        dislikeCount--;
    }

    //작성하기, 모두보기 버튼 관련 함수
    public void showCommentWriteActivity(){ //안쓰는 함수
        //activity.onCommand_CommentWriteActivity();
        Intent intent = new Intent(activity.getApplicationContext(), CommentWriteActivity.class);
        startActivityForResult(intent, 101);

    }
    public void showViewAllCommentActivity(){
        ArrayList<CommentItem> sendingArrayList = adapter.items;
        Intent intent = new Intent(getActivity().getApplicationContext(), ViewAllCommentActivity.class);
        intent.putParcelableArrayListExtra("adapterArrayList", sendingArrayList);
        startActivity(intent);
    }

}