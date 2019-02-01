
package com.mylocarson.reservationapp.models.user;

import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id")
    private Long mId;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("username")
    private String mUsername;

//    public User(String username,String password){
//        this.mUsername = username;
//        this.mPassword = password;
//
//    }

    public User(String email, String password){
        this.mEmail = email;
        this.mPassword = password;
    }

    public User(String mEmail,String username,String password){
        this.mEmail = mEmail;
        this.mUsername = username;
        this.mPassword = password;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Object getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "mCreatedAt='" + mCreatedAt + '\'' +
                ", mDeletedAt=" + mDeletedAt +
                ", mEmail='" + mEmail + '\'' +
                ", mId=" + mId +
                ", mPassword='" + mPassword + '\'' +
                ", mUpdatedAt='" + mUpdatedAt + '\'' +
                ", mUsername='" + mUsername + '\'' +
                '}';
    }
}
