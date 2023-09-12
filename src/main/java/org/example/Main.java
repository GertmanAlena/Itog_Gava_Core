package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        print(new File("."), "", true);
    }
    public static void print(File file, String indent, boolean isLast){
        System.out.println(indent);
        if(isLast){
            System.out.println("└─");
            System.out.println("  ");   //если у root есть ещё subdir, то добавим отступы
        }

    }
}