package com.example.carinformationdashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "CarsInfo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_database";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CARMARKA = "car_marka";
    private static final String COLUMN_CARMODL = "car_modl";
    private static final String COLUMN_CARREGNOM = "car_regnom";
    private static final String COLUMN_CARDANAK = "car_danak";
    private static final String COLUMN_CARZASTRAHOVKA = "car_zastrahovka";
    private static final String COLUMN_CARPREGLED = "car_pregled";
    private static final String COLUMN_CARVINETKA = "car_vinetka";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CARMARKA + " TEXT, " +
                COLUMN_CARMODL + " TEXT, " +
                COLUMN_CARREGNOM + " TEXT, " +
                COLUMN_CARDANAK + " INTEGER, " +
                COLUMN_CARZASTRAHOVKA + " INTEGER, " +
                COLUMN_CARPREGLED + " INTEGER, " +
                COLUMN_CARVINETKA + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addCar(String marka, String modl, String regnom, int danak, int zastrahovka, int pregled, int vinetka) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CARMARKA, marka);
        cv.put(COLUMN_CARMODL, modl);
        cv.put(COLUMN_CARREGNOM, regnom);
        cv.put(COLUMN_CARDANAK, danak);
        cv.put(COLUMN_CARZASTRAHOVKA, zastrahovka);
        cv.put(COLUMN_CARPREGLED, pregled);
        cv.put(COLUMN_CARVINETKA, vinetka);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String marka, String modl, String regnom, String danak, String zastrahovka, String pregled, String vinetka){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CARMARKA, marka);
        cv.put(COLUMN_CARMODL, modl);
        cv.put(COLUMN_CARREGNOM, regnom);
        cv.put(COLUMN_CARDANAK, danak);
        cv.put(COLUMN_CARZASTRAHOVKA, zastrahovka);
        cv.put(COLUMN_CARPREGLED, pregled);
        cv.put(COLUMN_CARVINETKA, vinetka);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}