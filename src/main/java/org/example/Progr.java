package org.example;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Progr {

    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65;   //нижняя граница списка символов
    private static final int CHAR_BOUND_H = 90;   //верхняя граница списка символов
    private static final String TO_SEARCH = "HomeWork";




    public static void main(String[] args) throws IOException {
        System.out.println(generateSimbol(30));
        writeFileContents("test1.txt", 30);
        writeFileContents("test2.txt", 30, 1);
        concatenate("test1.txt", "test2.txt", "fileAll.txt");
        if (searchInFile("fileAll.txt", TO_SEARCH))
            System.out.printf("Файл %s содержит нужное нам слово %s\n", "fileAll.txt", TO_SEARCH);

        String[] fileNames = new String[10];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + ".txt";
            writeFileContents(fileNames[i], 30, 3);
            System.out.printf("Файл %s создан.\n", fileNames[i]);
        }

        List<String> result = searchMatch(new File("."), TO_SEARCH);
        for (String s : result) {
            System.out.printf("Файл %s содержит нужное нам слово %s\n", s, TO_SEARCH);
        }

    }

    private static List<String> searchMatch(File dir, String search) throws IOException{
        dir = new File(dir.getCanonicalPath()); //относительную директорию в абсолютную

        List<String> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files == null)
            return list;
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()){
                    continue;
            }
            if(searchInFile(files[i].getName(), search))
                list.add(files[i].getName());
        }
        return list;
    }

    /**
     * Метод генерации последовательности символов
     * @count количество символов для генерации
     * @return последовательность символов
     */
    public static String generateSimbol(int count){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }

    /**
     * Метод записи файла с последовательностью символов
     * @param fileName имя файла
     * @param length длина последовательности символов
     * @throws IOException
     */
    private static void writeFileContents(String fileName, int length) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            // fileOutputStream работает с потоками байт,
            // поэтому сгенерированный список нужно привести к массиву байт
            fileOutputStream.write(generateSimbol(length).getBytes());
        }
    }
    private static void writeFileContents(String fileName, int length, int i) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            if(random.nextInt(i) == 0){
                fileOutputStream.write(TO_SEARCH.getBytes());
            }
            fileOutputStream.write(generateSimbol(length).getBytes());
        }
    }

    private static void concatenate(String nameFile1, String nameFile2, String nameFileOut) throws IOException{
        //запись
        try(FileOutputStream fileOutputStream = new FileOutputStream(nameFileOut)){

            int c;
            //на чтение
            try(FileInputStream fileInputStream = new FileInputStream(nameFile1)){
                //считывает побайтово(если -1, то дошёл до конца файла
                while ( (c = fileInputStream.read()) != -1)
                    fileOutputStream.write(c);
           }
            //на чтение
            try(FileInputStream fileInputStream = new FileInputStream(nameFile2)){
                //считывает побайтово(если -1, то дошёл до конца файла
                while ( (c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }

    /**
     * Определить, есть ли в файле искомое слово
     * @param fileName имя файла
     * @param search искомое слово
     * @return результат поиска
     * @throws IOException
     */
    private static boolean searchInFile(String fileName, String search) throws IOException{
        try(FileInputStream fileInputStream = new FileInputStream(fileName)) {
            byte[] serachByte = search.getBytes();
            int c;
            int i = 0;
            while ( (c = fileInputStream.read()) != -1){
                if(c == serachByte[i]){
                    i++;
                } else {
                    i = 0;
                    if(c == serachByte[i])
                        i++;
                    continue;
                }
                if(i == serachByte.length){
                    return true;
                }
            }
            return false;
        }
    }













}
