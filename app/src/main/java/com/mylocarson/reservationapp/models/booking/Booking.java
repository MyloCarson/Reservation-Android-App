
package com.mylocarson.reservationapp.models.booking;

import com.google.gson.annotations.SerializedName;

public class Booking {

    @SerializedName("booking_date")
    private String mBookingDate;
    @SerializedName("booking_name")
    private String mBookingName;
    @SerializedName("booking_status")
    private String mBookingStatus;
    @SerializedName("booking_time")
    private String mBookingTime;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("table_id")
    private String mTableId;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("user_id")
    private String mUserId;

    public Booking(String bookingName, String bookingDate, String bookingTime, String table_id){
        this.mBookingName = bookingName;
        this.mBookingDate = bookingDate;
        this.mBookingTime = bookingTime;
        this.mTableId  = table_id;
    }

    public String getBookingDate() {
        return mBookingDate;
    }

    public void setBookingDate(String bookingDate) {
        mBookingDate = bookingDate;
    }

    public String getBookingName() {
        return mBookingName;
    }

    public void setBookingName(String bookingName) {
        mBookingName = bookingName;
    }

    public String getBookingStatus() {
        return mBookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        mBookingStatus = bookingStatus;
    }

    public String getBookingTime() {
        return mBookingTime;
    }

    public void setBookingTime(String bookingTime) {
        mBookingTime = bookingTime;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTableId() {
        return mTableId;
    }

    public void setTableId(String tableId) {
        mTableId = tableId;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "mBookingDate='" + mBookingDate + '\'' +
                ", mBookingName='" + mBookingName + '\'' +
                ", mBookingStatus='" + mBookingStatus + '\'' +
                ", mBookingTime='" + mBookingTime + '\'' +
                ", mCreatedAt='" + mCreatedAt + '\'' +
                ", mId=" + mId +
                ", mTableId='" + mTableId + '\'' +
                ", mUpdatedAt='" + mUpdatedAt + '\'' +
                ", mUserId='" + mUserId + '\'' +
                '}';
    }
}
