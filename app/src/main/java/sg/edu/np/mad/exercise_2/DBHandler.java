package sg.edu.np.mad.exercise_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "username";
    private static final String COLUMN_DESCRIPTION = "userDescription";
    private static final String COLUMN_FOLLOWED = "userFollowed";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_FOLLOWED + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        addDefaultUser(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public void addDefaultUser(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        for (int i =1; i <21; i++){
            Random random = new Random();
            String name = "Name-" + random.nextInt(999999);
            String desc = "Description-" + random.nextInt(999999);
            values.put(COLUMN_ID, i);
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_DESCRIPTION,  desc);
            values.put(COLUMN_FOLLOWED, 0);
            db.insert(TABLE_USERS, null, values);
        }
    }
    public ArrayList<User> getUsers() {
        String query = "SELECT *  FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User queryData = null;
        ArrayList<User> users = new ArrayList<>();
        if (cursor.moveToFirst()){
            do { //iterates through every row the query returned
                queryData = new User();
                queryData.setId(cursor.getInt(0));
                queryData.setName(cursor.getString(1));
                queryData.setDescription(cursor.getString(2));
                if (cursor.getString(3).equals("0")) {
                    queryData.setFollowed(false);
                }
                else {
                    queryData.setFollowed(true);
                }
                users.add(queryData);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return users;
    }
    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        if (user.isFollowed() == true){
            values.put(COLUMN_FOLLOWED, 1);
        }
        else{
            values.put(COLUMN_FOLLOWED, 0);
        }
        db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
