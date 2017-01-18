package com.pereira.classificados.database;

/**
 * Created by Android on 18/01/2017.
 */

public class MyStore {

    static abstract class BaseTable{

        static final String GUID = "guid";

    }

    public static abstract class ItemAdTable extends BaseTable {

        static final String TABLE_NAME = "item_ad";

        static final String TITLE = "title";
        static final String DESCRIPTION = "description";
        // script para create table
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ( " +
                        GUID + " TEXT PRIMARY KEY," +
                        TITLE + " TEXT," +
                        DESCRIPTION + " TEXT"
                 + " )";
    }

    // ADD OTHER TABLES
}
