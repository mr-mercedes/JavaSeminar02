package ru.seminar02;

/*
    3**** Дана json строка (можно сохранить в файл и читать из файла)
    [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
    Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
    Пример вывода:
    Студент Иванов получил 5 по предмету Математика.
    Студент Петрова получил 4 по предмету Информатика.
    Студент Краснов получил 5 по предмету Физика.
 */

import java.io.*;

public class Task03 {
    public static void main(String[] args) throws FileNotFoundException {
        File path = new File("D:\\JavaDev\\NewJava\\GeekBrains_java_basic\\src\\main\\java\\ru\\ru\\seminar02\\dateForTask03.json");
        String data = fileReader(path);
        String[] array = extractParams(data);
        String[] finalString = buildString(array);
        printArray(finalString);
    }

    private static void printArray(String[] finalString) {
        for (String str : finalString) {
            System.out.println(str);
        }
    }

    private static String[] buildString(String[] array) {
        StringBuilder builder = new StringBuilder();
        String[] result = new String[3];
        int count = 0;
        for (int i = 0; i < array.length; i+=6) {
            builder.append("Студент ").append(array[i+1]).append(" получил ").append(array[i+3]).append(" по предмету ").append(array[i+5]);
            result[count++] = builder.toString();
            builder.setLength(0);
        }

        return result;
    }

    private static String fileReader(File path) throws FileNotFoundException {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            return bf.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "date not found";
    }

    private static String[] extractParams(String data) {
        data = data.substring(2, data.length() - 2).replace(',', ':').replace('{', ':').replace('}', ':').replace('"', ':');
        String[] tmp = data.split(":");
        String[] array = new String[tmp.length];
        int count = 0;
        int index = 0;
        for (String s : tmp) {
            if (s.equals("") || s.equals(" ")) {
                array[array.length - 1 - count] = s;
                count++;
            } else {
                array[index++] = s;
            }
        }
        String[] result = new String[index];
        System.arraycopy(array, 0, result, 0, index);

        return result;
    }
}
