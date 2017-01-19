package com.pereira.classificados.database;

/**
 * Created by Android on 18/01/2017.
 */

public class MyStore {

    public static abstract class BaseTable{

        public static final String GUID = "guid";

    }

    public static abstract class ItemAdTable extends BaseTable {

        public static final String TABLE_NAME = "item_ad";

        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
        // script para create table
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ( " +
                        GUID + " TEXT PRIMARY KEY," +
                        TITLE + " TEXT," +
                        DESCRIPTION + " TEXT, " +
                        PRICE + " TEXT"
                 + " )";
    }

    // ADD OTHER TABLES
}
