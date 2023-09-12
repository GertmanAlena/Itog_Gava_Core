package org.example;

import java.io.File;

public class Main {
    static final String START_END = "└─";
    static final String ADD_INDENT = "  ";
    static final String BRANCH = "├─";
    static final String BRANCH_INDENT = "│  ";


    public static void main(String[] args) {

        print(new File("."), "", true);
    }

    /**
     * метод отрисовки структуры дитектории
     * @param file переданный текущий файл
     * @param indent отступы или глубина диретории или файла
     * @param isLast коайняя директория
     */
    public static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if(isLast){
            System.out.print(START_END);
            indent += ADD_INDENT;   //если у root есть ещё subdir, то добавим отступы
        }
        else {
            System.out.print(BRANCH);   //промежуточная ветвь
            indent +=  BRANCH_INDENT;
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if(files == null){
            return;
        }

        int subDirTotal = 0;
        for (int i = 0; i <files.length ; i++) {
            if(files[i].isDirectory())
                subDirTotal++;
        }
        int subFileTotal = 0;
        for (int i = 0; i <files.length ; i++) {
            if(files[i].isFile())
                subFileTotal++;
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (int i = 0; i <files.length ; i++) {
            if(files[i].isDirectory()){
                subDirCounter++;
                print(files[i], indent, subDirCounter == subDirTotal);
            }
            if(files[i].isFile()){
                subFileCounter++;
                print(files[i], indent, subFileCounter == subFileTotal);
            }

        }
    }


}