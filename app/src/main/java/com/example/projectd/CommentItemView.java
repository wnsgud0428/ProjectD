package com.example.projectd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentItemView extends LinearLayout {
    TextView userIdView;
    TextView lastedTimeView;
    RatingBar personalRatingView;
    TextView commentView;
    TextView recoCountView;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this, true); //comment_item_view 레이아웃이 this(CommentItemView 클래스)에 와서 붙는다.

        userIdView=(TextView) findViewById(R.id.userId);
        lastedTimeView=(TextView) findViewById(R.id.lastedTime);
        personalRatingView=(RatingBar) findViewById(R.id.personalRating);
        commentView=(TextView) findViewById(R.id.comment);
        recoCountView=(TextView) findViewById(R.id.recoCount);

    }

    public void setUserId(String userId){
        userIdView.setText(userId);
    }

    public void setLastedTimeView(String lastedTime) {
        lastedTimeView.setText(lastedTime);
    }

    public void setPersonalRatingView(float rating){
        personalRatingView.setRating(rating);
    }

    public void setCommentView(String comment){
        commentView.setText(comment);
    }

    public void setRecoCountView(int recoCount){
        recoCountView.setText(String.valueOf(recoCount)); //오류 발견....
        //int로 되어있는 recoCount를 setText해주려고 하니 오류가 났었다.
        //recoCount를 int-> string으로 변환시켜주니 오류 해결.
    }

}
