package ru.seminar02;

/*
    2. Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder.
    Данные для фильтрации приведены ниже в виде json строки. Если значение null, то параметр не должен попадать в запрос.
    Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
    Ответ: "select * from students where name = 'Ivanov' and country = 'Russia' and city = 'Moscow'" для приведенного примера
 */

import java.io.*;

public class Task02 {
    public static void main(String[] args) throws FileNotFoundException {
        File path = new File("D:\\JavaDev\\NewJava\\GeekBrains_java_basic\\src\\main\\java\\ru\\ru\\seminar02\\params.json");
        String data = fileReader(path);
        String[] array = extractParams(data);
        String getSql = sqlBuilder(array);
        System.out.println(getSql);
    }

    private static String sqlBuilder(String[] array) {
        StringBuilder builder = new StringBuilder("select * from students where ");
        builder.append(array[0]).append(" = ").append(array[1]).append(" and ").append(array[2]).append(" = ")
                .append(array[3]).append(" and ").append(array[4]).append(" = ").append(array[5]);
        if (array[array.length - 1].equalsIgnoreCase("null")){
            return builder.toString();
        } else {
            builder.append(" and ").append(array[array.length - 2]).append(" = ").append(array[array.length - 1]);
        }
        return builder.toString();
    }

    private static String[] extractParams(String data) {
        data = data.substring(1, data.length() - 1).replace(',', ':').replace('{', ':')
                .replace('}', ':').replace('"', ':');
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


    private static String fileReader(File path) throws FileNotFoundException {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            return bf.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "date not found";
    }
}
