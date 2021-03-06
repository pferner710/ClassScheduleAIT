package com.example.michaeldruyan.classscheduleait.data;

/**
 * Created by Patrick on 5/16/18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Event.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract EventDAO eventDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                   AppDatabase.class, "place-database").build();
            //INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "place-database").addMigrations(MIGRATION_1_2).build();
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "event-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
