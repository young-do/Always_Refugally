package com.example.always_refugally.DB;

/**
 * Created by yd199 on 2016-11-26.
 */

public interface DBLiteral {
    String DATABASE_NAME = "Always Refugally";
    String USER_TABLE = "user";
    String PRODUCT_TABLE = "product";
    String STORE_TABLE = "store";
    String RELATION_TABLE = "relation";
    int DATABASE_VERSION = 1;

    String user_id_column = "user_id";
    String product_id_column = "product_id";
    String store_id_column = "store_id";
    String name_column = "name";
    String pw_column = "pw";
    String price_column = "price";
    String distance_column = "distance";
    String detail_column = "detail";
    String image_column = "image";
    String addr_columnn = "addr";
    String lat_column = "lat";
    String long_column = "long";

    String WHERE_USER_ID_EQUALS = user_id_column + "=?";

    String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " +
            USER_TABLE + "(" + user_id_column + " TEXT PRIMARY KEY, " +
            name_column + " TEXT, " +
            pw_column + " TEXT " +
            ");";

    String CREATE_STORE_TABLE = " CREATE TABLE IF NOT EXISTS " +
            STORE_TABLE + "(" + store_id_column + " INTEGER PRIMARY KEY, " +
            name_column + " TEXT, " +
            addr_columnn + " TEXT, " +
            image_column + " TEXT, " +
            lat_column + " DOUBLE, " +
            long_column + " DOUBLE " +
            ");";

    String CREATE_PRODUCT_TABLE = " CREATE TABLE IF NOT EXISTS " +
            PRODUCT_TABLE + "(" + product_id_column + " INTEGER PRIMARY KEY, " +
            name_column + " TEXT, " +
            addr_columnn + " TEXT, " +
            image_column + " TEXT, " +
            detail_column + " TEXT " +
            ");";

    String CREATE_RELATION_TABLE = " CREATE TABLE IF NOT EXISTS " +
            RELATION_TABLE + "(" + product_id_column + " INTEGER, " +
            store_id_column + " INTEGER, " +
            price_column + " INTEGER, " +
            distance_column + " INTEGER, " +
            "PRIMARY KEY (" + product_id_column + " , " + store_id_column + " ), " +
            " FOREIGN KEY (" + product_id_column +") REFERENCES " +
            PRODUCT_TABLE + "(" + product_id_column + ") ON DELETE CASCADE ON UPDATE CASCADE, " +
            " FOREIGN KEY (" + store_id_column +") REFERENCES " +
            STORE_TABLE + "(" + store_id_column + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ");";
}