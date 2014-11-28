package com.nurses.triage;

import android.content.Context;
import android.provider.BaseColumns;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.util.Log;
import android.database.Cursor;

import java.lang.String;

public class DataHandler {

    SQLiteDatabase db;
    DataHandlerHelper datahelper;
    Context context;

    public DataHandler(Context context) {
        this.context = context;
        datahelper = new DataHandlerHelper(context);
        this.datahelper = datahelper;
    }

    public static final String TABLE_PATIENTS = "patients";

    public static final String COLUMN_NAME_HEALTH = "healthNumber";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_BIRTHDATE = "birthdate";

    public static final String TABLE_CONDITIONS = "conditions";

    public static final String COLUMN_NAME_SYMPTOMS = "symptoms";
    public static final String COLUMN_NAME_BLOOD_SYSTOLIC = "systolic";
    public static final String COLUMN_NAME_BLOOD_DIASTOLIC = "diastolic";
    public static final String COLUMN_NAME_TEMPERATURE = "temperature";
    public static final String COLUMN_NAME_HEART_RATE = "heartRate";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_ARRIVAL_DATE = "arrival_date";
    public static final String COLUMN_NAME_SEEN_BY_DOCTOR = "seen_by_doctor";

    public static final String TABLE_PRESCRIPTIONS = "prescriptions";

    public static final String COLUMN_NAME_MEDICATION = "medication";
    public static final String COLUMN_NAME_INSTRUCTION = "instruction";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String DATABASE_NAME = "DataHandler";
    private static final String DATABASE_TABLE = "data";
    public static final String SQL_CREATE_TABLE_PATIENTS =
        "CREATE TABLE " + TABLE_PATIENTS + " (" + 
        COLUMN_NAME_HEALTH + " TEXT" + COMMA_SEP +
        COLUMN_NAME_NAME + " TEXT" + COMMA_SEP +
        COLUMN_NAME_BIRTHDATE + " TEXT"  + ")";

    public static final String SQL_CREATE_TABLE_CONDITIONS = 
        "CREATE TABLE " + TABLE_CONDITIONS + " (" + 
        COLUMN_NAME_HEALTH + " TEXT" + COMMA_SEP +
        COLUMN_NAME_SYMPTOMS + " TEXT" + COMMA_SEP +
        COLUMN_NAME_BLOOD_SYSTOLIC + " TEXT" + COMMA_SEP +
        COLUMN_NAME_BLOOD_DIASTOLIC + " TEXT" + COMMA_SEP +
        COLUMN_NAME_TEMPERATURE + " TEXT" + COMMA_SEP +
        COLUMN_NAME_HEART_RATE + " TEXT" + COMMA_SEP +
        COLUMN_NAME_TIME + " TEXT" +  COMMA_SEP +
        COLUMN_NAME_ARRIVAL_DATE + " TEXT" + COMMA_SEP +
        COLUMN_NAME_SEEN_BY_DOCTOR + " TEXT" + ")";

    public static final String SQL_CREATE_TABLE_PRESCRIPTIONS = 
        "CREATE TABLE " + TABLE_PRESCRIPTIONS + " (" + 
        COLUMN_NAME_HEALTH + " TEXT" + COMMA_SEP +
        COLUMN_NAME_MEDICATION + " TEXT" + COMMA_SEP +
        COLUMN_NAME_INSTRUCTION + " TEXT" + COMMA_SEP +
        COLUMN_NAME_TIME + " TEXT" + ")";



    private static final String SQL_DELETE_PATIENTS =
        "DROP TABLE IF EXISTS " + TABLE_PATIENTS;

    private static final String SQL_DELETE_CONDITIONS =
        "DROP TABLE IF EXISTS " + TABLE_CONDITIONS;
    
    private static final String SQL_DELETE_PERSCRIPTIONS =
        "DROP TABLE IF EXISTS " + TABLE_PRESCRIPTIONS;


    public static class DataHandlerHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 5;
        public static final String DATABASE_NAME = "DataHandler.db";

        public DataHandlerHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE_PATIENTS);
            db.execSQL(SQL_CREATE_TABLE_PRESCRIPTIONS);
            db.execSQL(SQL_CREATE_TABLE_CONDITIONS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_PATIENTS);
            db.execSQL(SQL_DELETE_CONDITIONS);
            db.execSQL(SQL_DELETE_PERSCRIPTIONS);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        };
    }

    public DataHandlerHelper getDB() throws SQLException {
        db = datahelper.getWritableDatabase();
        return datahelper;
    }

    // public void columns() {
    //     Cursor dbCursor = db.query(TABLE_NAME, null, null, null, null, null, null);
    //     String[] columnNames = dbCursor.getColumnNames();
    //     for(int k = 0; k < columnNames.length; k++) {
    //         System.out.println(columnNames[k]);
    //     }
    // }

    public boolean patientExists(String healthNumber){

        Cursor cursor = db.query(TABLE_PATIENTS, new String[] {COLUMN_NAME_HEALTH},
                        COLUMN_NAME_HEALTH + "=" + "'" + healthNumber + "'",
                        null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getCount() > 0;
    }

    public boolean conditionExists(String healthNumber, long time) {

        Cursor cursor = db.query(TABLE_CONDITIONS, new String[] {COLUMN_NAME_HEALTH,
                                 COLUMN_NAME_TIME}, COLUMN_NAME_HEALTH + "=" + "'" + healthNumber + "'" + " and " +
                                 COLUMN_NAME_TIME + "=" + "'" + time + "'",
                                 null, null, null, null, null);
        cursor.moveToFirst();
        System.out.println(cursor.getCount());
        return cursor.getCount() > 0;
    }

    public boolean prescriptionExists(String healthNumber, String medication, String time) {

        Cursor cursor = db.query(TABLE_PRESCRIPTIONS, new String[] {COLUMN_NAME_HEALTH, COLUMN_NAME_MEDICATION,
                                 COLUMN_NAME_TIME}, COLUMN_NAME_HEALTH + "=" + "'" + healthNumber + "'" + " and " +
                                 COLUMN_NAME_MEDICATION + "=" + "'" + medication + "'" + " and " +
                                 COLUMN_NAME_TIME + "=" + "'" + time + "'",
                                 null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getCount() > 0;
    }


    public void closeDB() {
        datahelper.close();
    }

    public String insertPatient(String healthNumber, String name, String birthdate) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_HEALTH, healthNumber);
        values.put(COLUMN_NAME_NAME, name);
        values.put(COLUMN_NAME_BIRTHDATE, birthdate);

        db.insert(TABLE_PATIENTS, null, values);

        return healthNumber;
    }

    public String insertCondition(String healthNumber, String symptoms,
                               int systolic, int diastolic, float temperature, int heartRate,
                               long time, String arrival_date, boolean seen_by_doctor) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_HEALTH, healthNumber);
        values.put(COLUMN_NAME_SYMPTOMS, symptoms);
        values.put(COLUMN_NAME_BLOOD_SYSTOLIC, systolic);
        values.put(COLUMN_NAME_BLOOD_DIASTOLIC, diastolic);
        values.put(COLUMN_NAME_HEART_RATE, heartRate);
        values.put(COLUMN_NAME_TEMPERATURE, temperature);
        values.put(COLUMN_NAME_TIME, time);
        values.put(COLUMN_NAME_ARRIVAL_DATE, arrival_date);
        values.put(COLUMN_NAME_SEEN_BY_DOCTOR, seen_by_doctor);

        db.insert(TABLE_CONDITIONS, null, values);

        return healthNumber;
    }

    public String insertPrescription(String healthNumber, String medication, String instruction, String time) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_HEALTH, healthNumber);
        values.put(COLUMN_NAME_MEDICATION, medication);
        values.put(COLUMN_NAME_INSTRUCTION, instruction);
        values.put(COLUMN_NAME_TIME, time);

        db.insert(TABLE_PRESCRIPTIONS, null, values);

        return healthNumber;
    }

    public Cursor getAllRecordsPatients() {
        return db.query(TABLE_PATIENTS, new String[] {COLUMN_NAME_HEALTH, COLUMN_NAME_NAME,
                        COLUMN_NAME_BIRTHDATE,
                        }, null, 
                        null, null, null, null);
    }

    public Cursor getAllRecordsConditions() {
        return db.query(TABLE_CONDITIONS, new String[] {COLUMN_NAME_HEALTH,
                        COLUMN_NAME_SYMPTOMS, COLUMN_NAME_BLOOD_SYSTOLIC, 
                        COLUMN_NAME_BLOOD_DIASTOLIC, COLUMN_NAME_HEART_RATE, COLUMN_NAME_TEMPERATURE,
                        COLUMN_NAME_TIME, COLUMN_NAME_ARRIVAL_DATE, COLUMN_NAME_SEEN_BY_DOCTOR}, null, 
                        null, null, null, null);
    }

    public Cursor getAllRecordsPrescriptions() {
        return db.query(TABLE_PRESCRIPTIONS, new String[] {COLUMN_NAME_HEALTH, COLUMN_NAME_MEDICATION,
                        COLUMN_NAME_INSTRUCTION, COLUMN_NAME_TIME}, null, null, null,
                        null, null);
    }
}
