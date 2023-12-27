package RoomDb;

import android.content.Context;
import android.os.AsyncTask;
import androidx.room.Room;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        userDao = db.userDao();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void getUser(String username, String password, OnUserFetchedListener listener) {
        new GetUserAsyncTask(userDao, listener).execute(username, password);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class GetUserAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;
        private OnUserFetchedListener listener;

        GetUserAsyncTask(UserDao userDao, OnUserFetchedListener listener) {
            this.userDao = userDao;
            this.listener = listener;
        }

        @Override
        protected User doInBackground(String... params) {
            return userDao.getUser(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(User user) {
            listener.onUserFetched(user);
        }
    }

    public interface OnUserFetchedListener {
        void onUserFetched(User user);
    }
}
