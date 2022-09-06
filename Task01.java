package ru.seminar02;

/*
    1. Напишите метод, который определит тип (расширение) файлов из текущей папки и выведет в консоль результат вида
    1 Расширение файла: txt
    2 Расширение файла: pdf
    3 Расширение файла:
    4 Расширение файла: jpg
 */

import java.io.File;

public class Task01 {
    public static void main(String[] args) {
        File dir = new File("D:\\JavaDev\\NewJava\\GeekBrains_java_basic\\src\\main\\java\\ru\\ru\\seminar02");

        getNameOfFiles(dir);
    }

    private static void getNameOfFiles(File dir) {
        String fileName;
        int count = 1;
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                fileName = item.getName();
                System.out.printf("%d Расширение файла: %s \n",count, getTypeOfFile(fileName));
                count++;
            }
        }
    }

    private static String getTypeOfFile(String nameFile) {
        String[] arr = nameFile.split("\\.");
        return arr[arr.length - 1];
    }
}
