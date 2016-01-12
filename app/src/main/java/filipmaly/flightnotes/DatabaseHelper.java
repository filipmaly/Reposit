package filipmaly.flightnotes;

/**
 * Created by filipmaly on 17. 11. 2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //Tabulka 1 Baterky
    public static final String DATABASE_NAME = "Battery.db";
    public static final String TABLE_NAME = "battery_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "TYPE";
    public static final String COL_4 = "CELLS";
    public static final String COL_5 = "CAPACITY";
    public static final String COL_6 = "LASTLOG";
    public static final String COL_7 = "TIME";
    public static final String COL_8 = "CYCLE";

    //Tabulka 2 letecka data

   // public static final String DATABASE_NAMEA = "Airport.db";
    public static final String TABLE_NAMEA = "airport_table";
    public static final String COL_1A = "_id";
    public static final String COL_2A = "DATUM";
    public static final String COL_3A = "HOUR";
    public static final String COL_4A = "PLACE";
    public static final String COL_5A = "WEATHER";
    public static final String COL_6A = "TEMPERATURE";
    public static final String COL_7A = "MODEL1";
    public static final String COL_8A = "MODEL2";
    public static final String COL_9A = "MODEL3";
    public static final String COL_10A = "MODEL4";
    public static final String COL_11A = "MODEL5";
    public static final String COL_12A = "TIME";
    public static final String COL_13A = "WIND";



    //Tabulka 3 Modely letadel

    //public static final String DATABASE_NAMEP = "Plane.db";
    public static final String TABLE_NAMEP = "plane_table";
    public static final String COL_1P = "_id";
    public static final String COL_2P = "MODEL";


    // Přidání 2 tabulek přes string


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "  (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,TYPE TEXT,CELLS INTEGER,CAPACITY INTEGER,LASTLOG DATE,TIME STRING,CYCLE INTEGER )");
        db.execSQL("create table " + TABLE_NAMEA + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, DATUM TEXT,HOUR TEXT,PLACE TEXT,WEATHER TEXT,TEMPERATURE TEXT,MODEL1 TEXT,MODEL2 TEXT,MODEL3 TEXT,MODEL4 TEXT, MODEL5 INTEGER, TIME INTEGER, WIND TEXT )");
        db.execSQL("create table " + TABLE_NAMEP + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,MODEL TEXT )");

        db.execSQL("INSERT INTO " + TABLE_NAMEP + " VALUES (1 , 'žádný' )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEP);

        onCreate(db);
    }

    //Vkládání do databáze letiště

    public boolean insertDataAirport(String datum,String hour,String place,String weather,String temperature,String model1,String model2,String model3, String model4, String model5, String time, String wind) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2A,datum);
        contentValues.put(COL_3A, hour);
        contentValues.put(COL_4A, place);
        contentValues.put(COL_5A, weather);
        contentValues.put(COL_6A, temperature);
        contentValues.put(COL_7A, model1);
        contentValues.put(COL_8A, model2);
        contentValues.put(COL_9A, model3);
        contentValues.put(COL_10A, model4);
        contentValues.put(COL_11A, model5);
        contentValues.put(COL_12A, time);
        contentValues.put(COL_13A, wind);


        long result = db.insert(TABLE_NAMEA,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //Vkládání do databáze modely

    public boolean insertDataModel(String model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2P,model);

        long result = db.insert(TABLE_NAMEP,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //Vkládání do databáze Baterky

    public boolean insertData(String name,String type,String cells,String capacity,String lastlog,String time,String cycle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, cells);
        contentValues.put(COL_5, capacity);
        contentValues.put(COL_6, lastlog);
        contentValues.put(COL_7, time);
        contentValues.put(COL_8, cycle);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //Vypsání pouze jména modelů
    public Cursor getModelData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAMEP, null);
        return res;
    }

    //Vypsání Datumu a Místa

    public Cursor getDatePlace ()

    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAMEA + " where " + COL_11A + " = " + calendar_activity.monthnumber , null);
        return res;


    }

    //vypís jen 4 sloupců
    public Cursor getListData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
        //Vypsání pouze jmen modelů
        public Cursor getModelDataUpdate()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select _id,MODEL from " + TABLE_NAMEP + " where " + COL_1P + " = " + list_view_model.IdModelu , null);


            if (res != null) {
                res.moveToFirst();
            }
            return res;
        }



    public Cursor getIDData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = " + ListViewActivity.Idkliknuti , null);


        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
    //Filtrování podle ID
    public Cursor GetIdDate()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAMEA + " where " + COL_1A + " = " + calendar_activity.IdkliknutiCalendar , null);


        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    // Update databáze Baterky

    public boolean updateData(String id,String name,String type,String cells,String capacity,String lastlog,String time,String cycle )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, cells);
        contentValues.put(COL_5, capacity);
        contentValues.put(COL_6, lastlog);
        contentValues.put(COL_7, time);
        contentValues.put(COL_8, cycle);
        db.update(TABLE_NAME, contentValues, "_id = ?",new String[] {id });
        return true;

    }

    // Update databáze Letiště

    public boolean updateDataAirport(String id, String date,String hour,String place,String weather,String temperature,String model1,String model2,String model3, String model4, String model5, String time, String wind)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2A,date);
        contentValues.put(COL_3A, hour);
        contentValues.put(COL_4A, place);
        contentValues.put(COL_5A, weather);
        contentValues.put(COL_6A, temperature);
        contentValues.put(COL_7A, model1);
        contentValues.put(COL_8A, model2);
        contentValues.put(COL_9A, model3);
        contentValues.put(COL_10A, model4);
        contentValues.put(COL_11A, model5);
        contentValues.put(COL_12A, time);
        contentValues.put(COL_13A, wind);
        db.update(TABLE_NAMEA, contentValues, "_id = ?",new String[] {id });
        return true;

    }

    // Update databáze Modely

    public boolean updateDataModel(String id, String model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2P,model);
        db.update(TABLE_NAMEP, contentValues, "_id = ?",new String[] {id });
        return true;

    }


   //Mazání dat baterky
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?" , new String[]{id} );
    }
    //Mazání dat Letiště
    public Integer deleteDataAirport(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAMEA, "_id = ?" , new String[]{id} );
    }

    //Mazání dat Modely
    public Integer deleteDataModel(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAMEP, "_id = ?" , new String[]{id} );
    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAMEP;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    }

