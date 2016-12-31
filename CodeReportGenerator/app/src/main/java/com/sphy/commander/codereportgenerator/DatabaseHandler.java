/*
  ------------------------------------------------------------------------------------------------------------------------------------------
  Project       : Code Report Generator
  File          : DatabaseHandler.java
  Description   : This file holds SQlite properties and Database information.
  Author        : Konstantinos Monahopoulos
  ------------------------------------------------------------------------------------------------------------------------------------------
*/

package com.sphy.commander.codereportgenerator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RecordsDB";

    // Contacts table name
    private static final String TABLE_NAME = "RTable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_FCODE = "fcode";
    private static final String KEY_ZCODE = "zcode";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CODES_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DATE + " TEXT,"
                + KEY_FCODE + " TEXT,"
                + KEY_ZCODE + " TEXT" + ")";
        db.execSQL(CREATE_CODES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addSQLinfo(SQLCodes sqlRecords) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, sqlRecords.getDate());   // Date
        values.put(KEY_FCODE, sqlRecords.getFcode()); // Fcode
        values.put(KEY_ZCODE, sqlRecords.getZcode()); // Zcode

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single row
    public String[] getSQLRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_DATE, KEY_FCODE, KEY_ZCODE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        String[] infos = {
                cursor.getString(0), // KEY_ID
                cursor.getString(1), // KEY_DATE
                cursor.getString(2), // KEY_FCODE
                cursor.getString(3)  // KEY_ZCODE
        };

        // return contact
        return infos;
    }

    public String[] getSQLString(String QueryStr) {

        SQLiteDatabase db = this.getReadableDatabase();
        

        Cursor cursor = db.query(
                TABLE_NAME, new String[]{KEY_ID, KEY_DATE, KEY_FCODE, KEY_ZCODE},
                KEY_DATE + "= ?", new String[] {QueryStr}, null, null, null);
            
               
        if (cursor != null)
            cursor.moveToNext();

        String[] infos = {
        cursor.getString(0), // return KEY_ID
        cursor.getString(1), // return KEY_DATE
        };

        // return contact
        return infos;
    }

    // Getting All Codes as a list
    public List<SQLCodes> getAllSQLRecords() {

        List<SQLCodes> codeList = new ArrayList<SQLCodes>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SQLCodes sqlRecords = new SQLCodes();
                sqlRecords.setID(Integer.parseInt(cursor.getString(0)));
                sqlRecords.setDate(cursor.getString(1));
                sqlRecords.setFcode(cursor.getString(2));
                sqlRecords.setZcode(cursor.getString(3));

                // Adding contact to list
                codeList.add(sqlRecords);
            } while (cursor.moveToNext());
        }

        // return contact list
        return codeList;
    }

    // Updating single record
    public int updateRecord (SQLCodes sqlRecord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_DATE, sqlRecord.getDate());
        values.put(KEY_FCODE, sqlRecord.getFcode());
        values.put(KEY_ZCODE, sqlRecord.getZcode());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(sqlRecord.getID()) });
    }

    // Deleting single row
    public void deleteContact(SQLCodes sqlRecord) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(sqlRecord.getID()) });
        db.close();
    }

    // Fill SQL database

    public void FillSQL_db(){

        /*Fill in the DB*/
		/**
		These random numbers as long as the strings are completely generated from the Java-File output.
         You can just copy and paste it here as is.
		*/

		addSQLinfo(new SQLCodes("01-01-2017", "721", "471"));
		addSQLinfo(new SQLCodes("02-01-2017", "496", "440"));
		addSQLinfo(new SQLCodes("03-01-2017", "699", "830"));
		addSQLinfo(new SQLCodes("04-01-2017", "971", "80"));
		addSQLinfo(new SQLCodes("05-01-2017", "45", "300"));
		addSQLinfo(new SQLCodes("06-01-2017", "421", "619"));
		addSQLinfo(new SQLCodes("07-01-2017", "330", "268"));
		addSQLinfo(new SQLCodes("08-01-2017", "980", "969"));
		addSQLinfo(new SQLCodes("09-01-2017", "516", "1"));
		addSQLinfo(new SQLCodes("10-01-2017", "335", "496"));
		addSQLinfo(new SQLCodes("11-01-2017", "625", "501"));
		addSQLinfo(new SQLCodes("12-01-2017", "587", "522"));
		addSQLinfo(new SQLCodes("13-01-2017", "722", "293"));
		addSQLinfo(new SQLCodes("14-01-2017", "882", "731"));
		addSQLinfo(new SQLCodes("15-01-2017", "837", "548"));
		addSQLinfo(new SQLCodes("16-01-2017", "478", "630"));
		addSQLinfo(new SQLCodes("17-01-2017", "768", "778"));
		addSQLinfo(new SQLCodes("18-01-2017", "548", "299"));
		addSQLinfo(new SQLCodes("19-01-2017", "718", "840"));
		addSQLinfo(new SQLCodes("20-01-2017", "715", "239"));
		addSQLinfo(new SQLCodes("21-01-2017", "391", "83"));
		addSQLinfo(new SQLCodes("22-01-2017", "19", "647"));
		addSQLinfo(new SQLCodes("23-01-2017", "47", "555"));
		addSQLinfo(new SQLCodes("24-01-2017", "770", "482"));
		addSQLinfo(new SQLCodes("25-01-2017", "240", "263"));
		addSQLinfo(new SQLCodes("26-01-2017", "633", "915"));
		addSQLinfo(new SQLCodes("27-01-2017", "475", "151"));
		addSQLinfo(new SQLCodes("28-01-2017", "686", "328"));
		addSQLinfo(new SQLCodes("29-01-2017", "386", "702"));
		addSQLinfo(new SQLCodes("30-01-2017", "803", "354"));
		addSQLinfo(new SQLCodes("31-01-2017", "713", "609"));
		addSQLinfo(new SQLCodes("01-02-2017", "667", "33"));
		addSQLinfo(new SQLCodes("02-02-2017", "371", "456"));
		addSQLinfo(new SQLCodes("03-02-2017", "269", "931"));
		addSQLinfo(new SQLCodes("04-02-2017", "713", "848"));
		addSQLinfo(new SQLCodes("05-02-2017", "529", "600"));
		addSQLinfo(new SQLCodes("06-02-2017", "885", "179"));
		addSQLinfo(new SQLCodes("07-02-2017", "15", "653"));
		addSQLinfo(new SQLCodes("08-02-2017", "373", "749"));
		addSQLinfo(new SQLCodes("09-02-2017", "447", "476"));
		addSQLinfo(new SQLCodes("10-02-2017", "170", "427"));
		addSQLinfo(new SQLCodes("11-02-2017", "931", "356"));
		addSQLinfo(new SQLCodes("12-02-2017", "784", "426"));
		addSQLinfo(new SQLCodes("13-02-2017", "832", "586"));
		addSQLinfo(new SQLCodes("14-02-2017", "968", "658"));
		addSQLinfo(new SQLCodes("15-02-2017", "979", "60"));
		addSQLinfo(new SQLCodes("16-02-2017", "521", "69"));
		addSQLinfo(new SQLCodes("17-02-2017", "697", "181"));
		addSQLinfo(new SQLCodes("18-02-2017", "526", "495"));
		addSQLinfo(new SQLCodes("19-02-2017", "167", "250"));
		addSQLinfo(new SQLCodes("20-02-2017", "233", "235"));
		addSQLinfo(new SQLCodes("21-02-2017", "616", "800"));
		addSQLinfo(new SQLCodes("22-02-2017", "444", "680"));
		addSQLinfo(new SQLCodes("23-02-2017", "533", "936"));
		addSQLinfo(new SQLCodes("24-02-2017", "376", "39"));
		addSQLinfo(new SQLCodes("25-02-2017", "238", "457"));
		addSQLinfo(new SQLCodes("26-02-2017", "943", "770"));
		addSQLinfo(new SQLCodes("27-02-2017", "751", "440"));
		addSQLinfo(new SQLCodes("28-02-2017", "3", "242"));
		addSQLinfo(new SQLCodes("01-03-2017", "126", "886"));
		addSQLinfo(new SQLCodes("02-03-2017", "482", "621"));
		addSQLinfo(new SQLCodes("03-03-2017", "488", "61"));
		addSQLinfo(new SQLCodes("04-03-2017", "321", "170"));
		addSQLinfo(new SQLCodes("05-03-2017", "835", "352"));
		addSQLinfo(new SQLCodes("06-03-2017", "373", "890"));
		addSQLinfo(new SQLCodes("07-03-2017", "742", "599"));
		addSQLinfo(new SQLCodes("08-03-2017", "528", "725"));
		addSQLinfo(new SQLCodes("09-03-2017", "125", "676"));
		addSQLinfo(new SQLCodes("10-03-2017", "489", "222"));
		addSQLinfo(new SQLCodes("11-03-2017", "209", "621"));
		addSQLinfo(new SQLCodes("12-03-2017", "716", "405"));
		addSQLinfo(new SQLCodes("13-03-2017", "780", "630"));
		addSQLinfo(new SQLCodes("14-03-2017", "41", "598"));
		addSQLinfo(new SQLCodes("15-03-2017", "354", "746"));
		addSQLinfo(new SQLCodes("16-03-2017", "801", "187"));
		addSQLinfo(new SQLCodes("17-03-2017", "715", "870"));
		addSQLinfo(new SQLCodes("18-03-2017", "802", "547"));
		addSQLinfo(new SQLCodes("19-03-2017", "53", "698"));
		addSQLinfo(new SQLCodes("20-03-2017", "371", "293"));
		addSQLinfo(new SQLCodes("21-03-2017", "206", "533"));
		addSQLinfo(new SQLCodes("22-03-2017", "531", "117"));
		addSQLinfo(new SQLCodes("23-03-2017", "286", "780"));
		addSQLinfo(new SQLCodes("24-03-2017", "764", "18"));
		addSQLinfo(new SQLCodes("25-03-2017", "371", "582"));
		addSQLinfo(new SQLCodes("26-03-2017", "405", "609"));
		addSQLinfo(new SQLCodes("27-03-2017", "671", "607"));
		addSQLinfo(new SQLCodes("28-03-2017", "910", "978"));
		addSQLinfo(new SQLCodes("29-03-2017", "84", "455"));
		addSQLinfo(new SQLCodes("30-03-2017", "252", "205"));
		addSQLinfo(new SQLCodes("31-03-2017", "896", "246"));
		addSQLinfo(new SQLCodes("01-04-2017", "476", "88"));
		addSQLinfo(new SQLCodes("02-04-2017", "827", "527"));
		addSQLinfo(new SQLCodes("03-04-2017", "644", "584"));
		addSQLinfo(new SQLCodes("04-04-2017", "549", "254"));
		addSQLinfo(new SQLCodes("05-04-2017", "741", "233"));
		addSQLinfo(new SQLCodes("06-04-2017", "525", "71"));
		addSQLinfo(new SQLCodes("07-04-2017", "93", "141"));
		addSQLinfo(new SQLCodes("08-04-2017", "452", "813"));
		addSQLinfo(new SQLCodes("09-04-2017", "561", "909"));
		addSQLinfo(new SQLCodes("10-04-2017", "599", "227"));
		addSQLinfo(new SQLCodes("11-04-2017", "87", "974"));
		addSQLinfo(new SQLCodes("12-04-2017", "579", "882"));
		addSQLinfo(new SQLCodes("13-04-2017", "737", "226"));
		addSQLinfo(new SQLCodes("14-04-2017", "219", "296"));
		addSQLinfo(new SQLCodes("15-04-2017", "782", "874"));
		addSQLinfo(new SQLCodes("16-04-2017", "85", "545"));
		addSQLinfo(new SQLCodes("17-04-2017", "274", "22"));
		addSQLinfo(new SQLCodes("18-04-2017", "196", "509"));
		addSQLinfo(new SQLCodes("19-04-2017", "230", "569"));
		addSQLinfo(new SQLCodes("20-04-2017", "926", "678"));
		addSQLinfo(new SQLCodes("21-04-2017", "802", "699"));
		addSQLinfo(new SQLCodes("22-04-2017", "349", "212"));
		addSQLinfo(new SQLCodes("23-04-2017", "535", "404"));
		addSQLinfo(new SQLCodes("24-04-2017", "858", "518"));
		addSQLinfo(new SQLCodes("25-04-2017", "769", "834"));
		addSQLinfo(new SQLCodes("26-04-2017", "877", "463"));
		addSQLinfo(new SQLCodes("27-04-2017", "937", "536"));
		addSQLinfo(new SQLCodes("28-04-2017", "881", "227"));
		addSQLinfo(new SQLCodes("29-04-2017", "153", "859"));
		addSQLinfo(new SQLCodes("30-04-2017", "101", "421"));
		addSQLinfo(new SQLCodes("01-05-2017", "993", "825"));
		addSQLinfo(new SQLCodes("02-05-2017", "86", "438"));
		addSQLinfo(new SQLCodes("03-05-2017", "63", "856"));
		addSQLinfo(new SQLCodes("04-05-2017", "639", "68"));
		addSQLinfo(new SQLCodes("05-05-2017", "626", "977"));
		addSQLinfo(new SQLCodes("06-05-2017", "715", "29"));
		addSQLinfo(new SQLCodes("07-05-2017", "719", "887"));
		addSQLinfo(new SQLCodes("08-05-2017", "457", "470"));
		addSQLinfo(new SQLCodes("09-05-2017", "228", "600"));
		addSQLinfo(new SQLCodes("10-05-2017", "693", "812"));
		addSQLinfo(new SQLCodes("11-05-2017", "810", "975"));
		addSQLinfo(new SQLCodes("12-05-2017", "768", "590"));
		addSQLinfo(new SQLCodes("13-05-2017", "953", "434"));
		addSQLinfo(new SQLCodes("14-05-2017", "630", "797"));
		addSQLinfo(new SQLCodes("15-05-2017", "235", "423"));
		addSQLinfo(new SQLCodes("16-05-2017", "165", "626"));
		addSQLinfo(new SQLCodes("17-05-2017", "330", "454"));
		addSQLinfo(new SQLCodes("18-05-2017", "917", "282"));
		addSQLinfo(new SQLCodes("19-05-2017", "37", "67"));
		addSQLinfo(new SQLCodes("20-05-2017", "339", "376"));
		addSQLinfo(new SQLCodes("21-05-2017", "373", "28"));
		addSQLinfo(new SQLCodes("22-05-2017", "255", "535"));
		addSQLinfo(new SQLCodes("23-05-2017", "415", "161"));
		addSQLinfo(new SQLCodes("24-05-2017", "326", "434"));
		addSQLinfo(new SQLCodes("25-05-2017", "98", "292"));
		addSQLinfo(new SQLCodes("26-05-2017", "778", "835"));
		addSQLinfo(new SQLCodes("27-05-2017", "127", "598"));
		addSQLinfo(new SQLCodes("28-05-2017", "660", "380"));
		addSQLinfo(new SQLCodes("29-05-2017", "105", "274"));
		addSQLinfo(new SQLCodes("30-05-2017", "68", "877"));
		addSQLinfo(new SQLCodes("31-05-2017", "442", "275"));
		addSQLinfo(new SQLCodes("01-06-2017", "95", "138"));
		addSQLinfo(new SQLCodes("02-06-2017", "53", "86"));
		addSQLinfo(new SQLCodes("03-06-2017", "47", "512"));
		addSQLinfo(new SQLCodes("04-06-2017", "18", "779"));
		addSQLinfo(new SQLCodes("05-06-2017", "209", "461"));
		addSQLinfo(new SQLCodes("06-06-2017", "139", "246"));
		addSQLinfo(new SQLCodes("07-06-2017", "400", "673"));
		addSQLinfo(new SQLCodes("08-06-2017", "774", "756"));
		addSQLinfo(new SQLCodes("09-06-2017", "807", "348"));
		addSQLinfo(new SQLCodes("10-06-2017", "483", "332"));
		addSQLinfo(new SQLCodes("11-06-2017", "731", "437"));
		addSQLinfo(new SQLCodes("12-06-2017", "797", "75"));
		addSQLinfo(new SQLCodes("13-06-2017", "406", "909"));
		addSQLinfo(new SQLCodes("14-06-2017", "261", "92"));
		addSQLinfo(new SQLCodes("15-06-2017", "928", "622"));
		addSQLinfo(new SQLCodes("16-06-2017", "210", "538"));
		addSQLinfo(new SQLCodes("17-06-2017", "376", "331"));
		addSQLinfo(new SQLCodes("18-06-2017", "317", "851"));
		addSQLinfo(new SQLCodes("19-06-2017", "537", "437"));
		addSQLinfo(new SQLCodes("20-06-2017", "417", "92"));
		addSQLinfo(new SQLCodes("21-06-2017", "728", "638"));
		addSQLinfo(new SQLCodes("22-06-2017", "360", "707"));
		addSQLinfo(new SQLCodes("23-06-2017", "153", "558"));
		addSQLinfo(new SQLCodes("24-06-2017", "432", "813"));
		addSQLinfo(new SQLCodes("25-06-2017", "121", "824"));
		addSQLinfo(new SQLCodes("26-06-2017", "780", "468"));
		addSQLinfo(new SQLCodes("27-06-2017", "253", "25"));
		addSQLinfo(new SQLCodes("28-06-2017", "127", "860"));
		addSQLinfo(new SQLCodes("29-06-2017", "970", "108"));
		addSQLinfo(new SQLCodes("30-06-2017", "171", "514"));
		addSQLinfo(new SQLCodes("01-07-2017", "590", "620"));
		addSQLinfo(new SQLCodes("02-07-2017", "696", "81"));
		addSQLinfo(new SQLCodes("03-07-2017", "308", "7"));
		addSQLinfo(new SQLCodes("04-07-2017", "256", "619"));
		addSQLinfo(new SQLCodes("05-07-2017", "613", "179"));
		addSQLinfo(new SQLCodes("06-07-2017", "926", "393"));
		addSQLinfo(new SQLCodes("07-07-2017", "449", "41"));
		addSQLinfo(new SQLCodes("08-07-2017", "911", "967"));
		addSQLinfo(new SQLCodes("09-07-2017", "326", "603"));
		addSQLinfo(new SQLCodes("10-07-2017", "393", "503"));
		addSQLinfo(new SQLCodes("11-07-2017", "295", "450"));
		addSQLinfo(new SQLCodes("12-07-2017", "345", "224"));
		addSQLinfo(new SQLCodes("13-07-2017", "805", "472"));
		addSQLinfo(new SQLCodes("14-07-2017", "593", "114"));
		addSQLinfo(new SQLCodes("15-07-2017", "147", "367"));
		addSQLinfo(new SQLCodes("16-07-2017", "701", "625"));
		addSQLinfo(new SQLCodes("17-07-2017", "211", "500"));
		addSQLinfo(new SQLCodes("18-07-2017", "70", "244"));
		addSQLinfo(new SQLCodes("19-07-2017", "790", "148"));
		addSQLinfo(new SQLCodes("20-07-2017", "512", "89"));
		addSQLinfo(new SQLCodes("21-07-2017", "913", "841"));
		addSQLinfo(new SQLCodes("22-07-2017", "750", "367"));
		addSQLinfo(new SQLCodes("23-07-2017", "387", "400"));
		addSQLinfo(new SQLCodes("24-07-2017", "302", "159"));
		addSQLinfo(new SQLCodes("25-07-2017", "370", "651"));
		addSQLinfo(new SQLCodes("26-07-2017", "294", "895"));
		addSQLinfo(new SQLCodes("27-07-2017", "454", "62"));
		addSQLinfo(new SQLCodes("28-07-2017", "133", "212"));
		addSQLinfo(new SQLCodes("29-07-2017", "861", "456"));
		addSQLinfo(new SQLCodes("30-07-2017", "767", "676"));
		addSQLinfo(new SQLCodes("31-07-2017", "182", "664"));
		addSQLinfo(new SQLCodes("01-08-2017", "424", "446"));
		addSQLinfo(new SQLCodes("02-08-2017", "713", "537"));
		addSQLinfo(new SQLCodes("03-08-2017", "506", "174"));
		addSQLinfo(new SQLCodes("04-08-2017", "627", "156"));
		addSQLinfo(new SQLCodes("05-08-2017", "18", "601"));
		addSQLinfo(new SQLCodes("06-08-2017", "855", "512"));
		addSQLinfo(new SQLCodes("07-08-2017", "500", "225"));
		addSQLinfo(new SQLCodes("08-08-2017", "376", "41"));
		addSQLinfo(new SQLCodes("09-08-2017", "63", "469"));
		addSQLinfo(new SQLCodes("10-08-2017", "451", "562"));
		addSQLinfo(new SQLCodes("11-08-2017", "138", "785"));
		addSQLinfo(new SQLCodes("12-08-2017", "481", "912"));
		addSQLinfo(new SQLCodes("13-08-2017", "294", "53"));
		addSQLinfo(new SQLCodes("14-08-2017", "403", "492"));
		addSQLinfo(new SQLCodes("15-08-2017", "286", "836"));
		addSQLinfo(new SQLCodes("16-08-2017", "102", "348"));
		addSQLinfo(new SQLCodes("17-08-2017", "992", "876"));
		addSQLinfo(new SQLCodes("18-08-2017", "342", "480"));
		addSQLinfo(new SQLCodes("19-08-2017", "824", "453"));
		addSQLinfo(new SQLCodes("20-08-2017", "598", "932"));
		addSQLinfo(new SQLCodes("21-08-2017", "226", "519"));
		addSQLinfo(new SQLCodes("22-08-2017", "109", "511"));
		addSQLinfo(new SQLCodes("23-08-2017", "932", "340"));
		addSQLinfo(new SQLCodes("24-08-2017", "188", "494"));
		addSQLinfo(new SQLCodes("25-08-2017", "310", "255"));
		addSQLinfo(new SQLCodes("26-08-2017", "580", "573"));
		addSQLinfo(new SQLCodes("27-08-2017", "274", "984"));
		addSQLinfo(new SQLCodes("28-08-2017", "69", "315"));
		addSQLinfo(new SQLCodes("29-08-2017", "790", "38"));
		addSQLinfo(new SQLCodes("30-08-2017", "737", "993"));
		addSQLinfo(new SQLCodes("31-08-2017", "512", "863"));
		addSQLinfo(new SQLCodes("01-09-2017", "416", "856"));
		addSQLinfo(new SQLCodes("02-09-2017", "153", "311"));
		addSQLinfo(new SQLCodes("03-09-2017", "484", "964"));
		addSQLinfo(new SQLCodes("04-09-2017", "658", "66"));
		addSQLinfo(new SQLCodes("05-09-2017", "532", "152"));
		addSQLinfo(new SQLCodes("06-09-2017", "747", "143"));
		addSQLinfo(new SQLCodes("07-09-2017", "709", "351"));
		addSQLinfo(new SQLCodes("08-09-2017", "347", "804"));
		addSQLinfo(new SQLCodes("09-09-2017", "508", "660"));
		addSQLinfo(new SQLCodes("10-09-2017", "337", "312"));
		addSQLinfo(new SQLCodes("11-09-2017", "881", "679"));
		addSQLinfo(new SQLCodes("12-09-2017", "9", "337"));
		addSQLinfo(new SQLCodes("13-09-2017", "734", "973"));
		addSQLinfo(new SQLCodes("14-09-2017", "458", "219"));
		addSQLinfo(new SQLCodes("15-09-2017", "205", "498"));
		addSQLinfo(new SQLCodes("16-09-2017", "892", "65"));
		addSQLinfo(new SQLCodes("17-09-2017", "175", "846"));
		addSQLinfo(new SQLCodes("18-09-2017", "727", "611"));
		addSQLinfo(new SQLCodes("19-09-2017", "296", "441"));
		addSQLinfo(new SQLCodes("20-09-2017", "406", "524"));
		addSQLinfo(new SQLCodes("21-09-2017", "832", "540"));
		addSQLinfo(new SQLCodes("22-09-2017", "522", "30"));
		addSQLinfo(new SQLCodes("23-09-2017", "381", "166"));
		addSQLinfo(new SQLCodes("24-09-2017", "236", "628"));
		addSQLinfo(new SQLCodes("25-09-2017", "540", "775"));
		addSQLinfo(new SQLCodes("26-09-2017", "298", "652"));
		addSQLinfo(new SQLCodes("27-09-2017", "438", "221"));
		addSQLinfo(new SQLCodes("28-09-2017", "595", "656"));
		addSQLinfo(new SQLCodes("29-09-2017", "623", "232"));
		addSQLinfo(new SQLCodes("30-09-2017", "501", "352"));
		addSQLinfo(new SQLCodes("01-10-2017", "866", "796"));
		addSQLinfo(new SQLCodes("02-10-2017", "531", "277"));
		addSQLinfo(new SQLCodes("03-10-2017", "528", "563"));
		addSQLinfo(new SQLCodes("04-10-2017", "645", "852"));
		addSQLinfo(new SQLCodes("05-10-2017", "711", "756"));
		addSQLinfo(new SQLCodes("06-10-2017", "447", "245"));
		addSQLinfo(new SQLCodes("07-10-2017", "727", "940"));
		addSQLinfo(new SQLCodes("08-10-2017", "267", "247"));
		addSQLinfo(new SQLCodes("09-10-2017", "584", "103"));
		addSQLinfo(new SQLCodes("10-10-2017", "891", "577"));
		addSQLinfo(new SQLCodes("11-10-2017", "127", "163"));
		addSQLinfo(new SQLCodes("12-10-2017", "654", "736"));
		addSQLinfo(new SQLCodes("13-10-2017", "215", "546"));
		addSQLinfo(new SQLCodes("14-10-2017", "668", "512"));
		addSQLinfo(new SQLCodes("15-10-2017", "348", "490"));
		addSQLinfo(new SQLCodes("16-10-2017", "885", "689"));
		addSQLinfo(new SQLCodes("17-10-2017", "475", "882"));
		addSQLinfo(new SQLCodes("18-10-2017", "291", "616"));
		addSQLinfo(new SQLCodes("19-10-2017", "214", "147"));
		addSQLinfo(new SQLCodes("20-10-2017", "824", "966"));
		addSQLinfo(new SQLCodes("21-10-2017", "891", "232"));
		addSQLinfo(new SQLCodes("22-10-2017", "865", "145"));
		addSQLinfo(new SQLCodes("23-10-2017", "972", "916"));
		addSQLinfo(new SQLCodes("24-10-2017", "394", "685"));
		addSQLinfo(new SQLCodes("25-10-2017", "225", "943"));
		addSQLinfo(new SQLCodes("26-10-2017", "500", "399"));
		addSQLinfo(new SQLCodes("27-10-2017", "561", "908"));
		addSQLinfo(new SQLCodes("28-10-2017", "577", "630"));
		addSQLinfo(new SQLCodes("29-10-2017", "152", "634"));
		addSQLinfo(new SQLCodes("30-10-2017", "112", "710"));
		addSQLinfo(new SQLCodes("31-10-2017", "498", "493"));
		addSQLinfo(new SQLCodes("01-11-2017", "52", "738"));
		addSQLinfo(new SQLCodes("02-11-2017", "686", "385"));
		addSQLinfo(new SQLCodes("03-11-2017", "511", "62"));
		addSQLinfo(new SQLCodes("04-11-2017", "494", "114"));
		addSQLinfo(new SQLCodes("05-11-2017", "988", "982"));
		addSQLinfo(new SQLCodes("06-11-2017", "314", "706"));
		addSQLinfo(new SQLCodes("07-11-2017", "25", "278"));
		addSQLinfo(new SQLCodes("08-11-2017", "556", "320"));
		addSQLinfo(new SQLCodes("09-11-2017", "451", "662"));
		addSQLinfo(new SQLCodes("10-11-2017", "102", "737"));
		addSQLinfo(new SQLCodes("11-11-2017", "20", "986"));
		addSQLinfo(new SQLCodes("12-11-2017", "495", "366"));
		addSQLinfo(new SQLCodes("13-11-2017", "759", "20"));
		addSQLinfo(new SQLCodes("14-11-2017", "333", "394"));
		addSQLinfo(new SQLCodes("15-11-2017", "396", "670"));
		addSQLinfo(new SQLCodes("16-11-2017", "933", "174"));
		addSQLinfo(new SQLCodes("17-11-2017", "783", "392"));
		addSQLinfo(new SQLCodes("18-11-2017", "886", "771"));
		addSQLinfo(new SQLCodes("19-11-2017", "315", "418"));
		addSQLinfo(new SQLCodes("20-11-2017", "907", "308"));
		addSQLinfo(new SQLCodes("21-11-2017", "380", "95"));
		addSQLinfo(new SQLCodes("22-11-2017", "999", "69"));
		addSQLinfo(new SQLCodes("23-11-2017", "642", "182"));
		addSQLinfo(new SQLCodes("24-11-2017", "762", "35"));
		addSQLinfo(new SQLCodes("25-11-2017", "201", "573"));
		addSQLinfo(new SQLCodes("26-11-2017", "706", "738"));
		addSQLinfo(new SQLCodes("27-11-2017", "126", "709"));
		addSQLinfo(new SQLCodes("28-11-2017", "357", "485"));
		addSQLinfo(new SQLCodes("29-11-2017", "372", "269"));
		addSQLinfo(new SQLCodes("30-11-2017", "202", "565"));
		addSQLinfo(new SQLCodes("01-12-2017", "19", "491"));
		addSQLinfo(new SQLCodes("02-12-2017", "56", "865"));
		addSQLinfo(new SQLCodes("03-12-2017", "306", "686"));
		addSQLinfo(new SQLCodes("04-12-2017", "614", "615"));
		addSQLinfo(new SQLCodes("05-12-2017", "76", "289"));
		addSQLinfo(new SQLCodes("06-12-2017", "874", "92"));
		addSQLinfo(new SQLCodes("07-12-2017", "486", "876"));
		addSQLinfo(new SQLCodes("08-12-2017", "125", "870"));
		addSQLinfo(new SQLCodes("09-12-2017", "896", "885"));
		addSQLinfo(new SQLCodes("10-12-2017", "627", "523"));
		addSQLinfo(new SQLCodes("11-12-2017", "410", "79"));
		addSQLinfo(new SQLCodes("12-12-2017", "648", "109"));
		addSQLinfo(new SQLCodes("13-12-2017", "534", "964"));
		addSQLinfo(new SQLCodes("14-12-2017", "998", "260"));
		addSQLinfo(new SQLCodes("15-12-2017", "947", "812"));
		addSQLinfo(new SQLCodes("16-12-2017", "924", "1000"));
		addSQLinfo(new SQLCodes("17-12-2017", "674", "706"));
		addSQLinfo(new SQLCodes("18-12-2017", "816", "986"));
		addSQLinfo(new SQLCodes("19-12-2017", "265", "447"));
		addSQLinfo(new SQLCodes("20-12-2017", "676", "694"));
		addSQLinfo(new SQLCodes("21-12-2017", "452", "542"));
		addSQLinfo(new SQLCodes("22-12-2017", "238", "536"));
		addSQLinfo(new SQLCodes("23-12-2017", "60", "835"));
		addSQLinfo(new SQLCodes("24-12-2017", "394", "903"));
		addSQLinfo(new SQLCodes("25-12-2017", "392", "969"));
		addSQLinfo(new SQLCodes("26-12-2017", "531", "818"));
		addSQLinfo(new SQLCodes("27-12-2017", "671", "806"));
		addSQLinfo(new SQLCodes("28-12-2017", "510", "982"));
		addSQLinfo(new SQLCodes("29-12-2017", "624", "597"));
		addSQLinfo(new SQLCodes("30-12-2017", "15", "873"));
		addSQLinfo(new SQLCodes("31-12-2017", "649", "56"));
		addSQLinfo(new SQLCodes("01-01-2018", "197", "902"));

		/*Fill in the DB - EODB*/
    }

}
