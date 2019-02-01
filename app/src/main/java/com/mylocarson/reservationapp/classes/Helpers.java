package com.mylocarson.reservationapp.classes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.regex.Pattern;


public class Helpers
{
    public static void toogleViews(int toggle , View ...views){
        switch (toggle){
            case 1 :
                for (View v:
                     views) {
                    v.setVisibility(View.VISIBLE);

                }
                break;
            case 0 :
                for (View v:
                     views) {
                    v.setVisibility(View.GONE);

                }
        }
    }

    public  static boolean isValid(EditText extended){
        if (extended.getText().toString().length() > 0 && !extended.getText().toString().isEmpty()){
            return true;
        }else{
            extended.setError("Required!!");
            return false;
        }

    }

    public static  boolean isValidEmail(EditText editText){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(editText.getText().toString()).matches();
    }

    public static  String string (EditText extendedEditText){
        return extendedEditText.getText().toString();
    }

    /** this method shows DatePicker
     * @param context
     * @param editText **/
    public  static void showDatePicker(Context context, final EditText editText){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month++;
                        editText.setText(day +"/"+ month  +"/" +year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /** this method shows TimePicker
     * @param context
     * @param editText **/
    public static void showTimePicker(Context context, final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                        editText.setText("" + hour + ":" + minutes);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }
}
