package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Backup {
    public static void main(String[] args) throws IOException {

        File dirOriginal = new File(".");
        File dirBackup = new File("C:\\Users\\Asus\\IdeaProjects\\untitled\\backup");
        backupDir(dirOriginal.getName(), dirBackup.getName());

    }
    private static void backupDir(String nameDirOriginal, String nameDirBackup) throws IOException {
        System.out.println(nameDirOriginal);
        System.out.println(nameDirBackup);
        File dirOriginal = new File(nameDirOriginal);
        if(!dirOriginal.exists()){
            System.out.println(">>>>>>>>");
            return;
        }

        File dirBackup = new File(nameDirBackup);
        if(!dirBackup.exists()){
            dirBackup.mkdir();
        }
        File[] files = dirOriginal.listFiles();
//        while (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    backupDir(file.getName(), dirBackup.getName());
                } else {
                    String NewNamefile = file.getName() + "-copy";
                    Files.copy(Path.of(file.getCanonicalPath()), Path.of(nameDirBackup,NewNamefile), REPLACE_EXISTING);
                }
            }
//        }
    }




}
