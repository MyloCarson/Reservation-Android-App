package com.mylocarson.reservationapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.activities.MyBookingsActivity;
import com.mylocarson.reservationapp.activities.ProfileActivity;
import com.mylocarson.reservationapp.activities.ReservationActivity;

public class DrawerUtil {
    public static void getDrawer(final Activity activity, Toolbar toolbar) {

        PrimaryDrawerItem mybookingActivity= new PrimaryDrawerItem().withIdentifier(0).withName(R.string.my_bookings);

//        SecondaryDrawerItem reminderActivity = new SecondaryDrawerItem().withIdentifier(1).withName(R.string.title_activity_reminder);
        SecondaryDrawerItem profileActivity = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.profile);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .build();

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(activity)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        mybookingActivity,
                        new DividerDrawerItem(),
//                        reminderActivity,
//                        new DividerDrawerItem(),
                        profileActivity,
                        new DividerDrawerItem()

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 0 && !(activity instanceof MyBookingsActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MyBookingsActivity.class);
                            view.getContext().startActivity(intent);
                        }
//                        if (drawerItem.getIdentifier() == 1 && !(activity instanceof ProfileActivity) ){
//                            Intent intent = new Intent(activity, ProfileActivity.class);
//                            view.getContext().startActivity(intent);
//                        }
                        if (drawerItem.getIdentifier() == 2 && !(activity instanceof ProfileActivity)) {
                            Intent intent = new Intent(activity, ProfileActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
    }
}
