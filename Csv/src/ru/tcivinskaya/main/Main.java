package ru.tcivinskaya.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        createHtml("input.txt", "output.txt");
    }

    private static void createHtml(String csvFileName, String htmlFileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(csvFileName), "windows-1251"); PrintWriter writer = new PrintWriter(htmlFileName)) {
            writer.print("<table>");

            while (scanner.hasNextLine()) {
                writer.print("<tr><td>");
                boolean isCellBeginning = true;

                StringBuilder line = new StringBuilder();
                line.append(scanner.nextLine());
                int quotesCount = 0;
                int quotesCountInCell = 0;

                for (int i = 0; i < line.length(); ++i) {
                    char character = line.charAt(i);

                    if (character == '"') {
                        quotesCount += 1;
                        quotesCountInCell += 1;

                        if (isCellBeginning) {
                            quotesCountInCell -= 2;
                        } else if (quotesCountInCell % 2 == 1) {
                            writer.print("\"");
                        }
                    }

                    isCellBeginning = false;

                    if (i == line.length() - 1 && quotesCount % 2 == 1) {
                        line.append(scanner.nextLine());
                        writer.print(chooseLineToPrint(character) + "<br/>");
                    } else if (character == ',' && quotesCount % 2 == 0) {
                        quotesCountInCell = 0;
                        isCellBeginning = true;
                        writer.print("</td><td>");
                    } else {
                        writer.print(chooseLineToPrint(character));
                    }
                }

                writer.print("</td></tr>");
            }

            writer.print("</table>");
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл: " + csvFileName);
        } catch (Exception e) {
            System.out.println("Возникла ошибка при чтении из файла: " + e.getMessage());
        }
    }

    private static String chooseLineToPrint(char c) {
        if (c == '<') {
            return "&lt";
        }

        if (c == '>') {
            return "&gt";
        }

        if (c == '&') {
            return "&amp";
        }

        if (c == '"') {
            return "";
        }

        return Character.toString(c);
    }
}