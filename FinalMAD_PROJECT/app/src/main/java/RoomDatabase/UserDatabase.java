package RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version=1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getDao();

    public static UserDatabase INSTANCE;

    public static UserDatabase getINSTANCE(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,UserDatabase.class,"UserDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
