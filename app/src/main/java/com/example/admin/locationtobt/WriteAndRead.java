package com.example.admin.locationtobt;

/**
 * Created by admin on 2018/3/6.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import android.os.Environment;
import java.io.File;

public class WriteAndRead {
    public static String readFileOnLine(){
        String LInfo="";
        String filePath="";
        boolean hasSDCard =Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (hasSDCard) {

            filePath =Environment.getExternalStorageDirectory().toString() + File.separator +"location.txt";

        } else

            filePath =Environment.getDownloadCacheDirectory().toString() + File.separator +"location.txt";

            File file = new File(filePath);

            if (!file.exists()) {return "";}

        try {
            InputStream instream = new FileInputStream(file);
            if (instream != null)
            {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);

                //分行读取
                String line;
                while (( line = buffreader.readLine()) != null) {
                    LInfo=line;
                }
                instream.close();
            }
        }
        catch (java.io.FileNotFoundException e)
        {
           // Log.d("TestFile", "The File doesn't not exist.");
        }
        catch (IOException e)
        {
            //Log.d("TestFile", e.getMessage());
        }
        return LInfo;
    }

    public static void getString(String str) {

        String filePath = null;

        boolean hasSDCard =Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (hasSDCard) {

            filePath =Environment.getExternalStorageDirectory().toString() + File.separator +"location.txt";

        } else

            filePath =Environment.getDownloadCacheDirectory().toString() + File.separator +"location.txt";



        try {

            File file = new File(filePath);

            if (!file.exists()) {

                File dir = new File(file.getParent());

                dir.mkdirs();

                file.createNewFile();

            }

            FileOutputStream outStream = new FileOutputStream(file,true);
            outStream.write("\r\n".getBytes());
            outStream.write(str.getBytes());

            outStream.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
