package com.example.projectd;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {
    public static final Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel in) {
            return new CommentItem(in);
        }

        @Override
        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(lastedTime);
        dest.writeFloat(personalRating);
        dest.writeString(comment);
        dest.writeInt(recoCount);
    }

    String userId;
    String lastedTime;
    float personalRating;
    String comment;
    int recoCount;

    public CommentItem(String userId, String lastedTime, float personalRating, String comment, int recoCount) {
        this.userId = userId;
        this.lastedTime = lastedTime;
        this.personalRating = personalRating;
        this.comment = comment;
        this.recoCount = recoCount;
    }

    protected CommentItem(Parcel in) {
        userId = in.readString();
        lastedTime = in.readString();
        personalRating = in.readFloat();
        comment = in.readString();
        recoCount = in.readInt();
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastedTime() {
        return lastedTime;
    }

    public void setLastedTime(String lastedTime) {
        this.lastedTime = lastedTime;
    }

    public float getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(float personalRating) {
        this.personalRating = personalRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRecoCount() {
        return recoCount;
    }

    public void setRecoCount(int recoCount) {
        this.recoCount = recoCount;
    }
}
