package com.soft.afri_wifi.mes_classes;

import android.os.Environment;

import java.io.File;

public class createFolders {
    public static File databaseFolderExport(){
        String folder_main = "SSV_TAXE";

        File fd = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!fd.exists()) {
            fd.mkdirs();
        }
        File sd = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Database");
        if (!sd.exists()) {
            sd.mkdirs();
        }
        return  sd;

    }

    public static File folderRapports(){
        String folder_main = "SSV_TAXE";

        File fd = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!fd.exists()) {
            fd.mkdirs();
        }
        File sd = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Rapports");
        if (!sd.exists()) {
            sd.mkdirs();
        }
        return  sd;

    }
}
