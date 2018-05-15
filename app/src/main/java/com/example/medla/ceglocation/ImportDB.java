package com.example.medla.ceglocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImportDB{

    private final int BUFFER_SIZE = 400000;
    private static final String DB_NAME = "CEG.db"; //保存的数据库文件名
    private static final String PACKAGE_NAME = "com.example.medla.ceglocation";//工程包名
    private static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME+"/databases";  //在手机里存放数据库的位置
    private SQLiteDatabase database;
    private Context context;

    ImportDB(Context context) {
        this.context = context;
    }

    public void openDatabase() {
        this.database = this.copyDatabase(DB_PATH + "/" + DB_NAME);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    private SQLiteDatabase copyDatabase(String dbfile) {    // dbfile = DB_PATH + "/" + DB_NAME ;
        try {
            //执行数据库导入
            InputStream is = this.context.getResources().getAssets().open("CEG.db"); //欲导入的数据库
            FileOutputStream fos = new FileOutputStream(dbfile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();//关闭输出流
            is.close();//关闭输入流
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            return db;
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase() {
        this.database.close();
    }
}