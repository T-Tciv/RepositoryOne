package ru.tcivinskaya.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ошибка при указании путей к файлам в аргументах программы");
        }

        createHtml(args[0], args[1]);
    }

    private static void createHtml(String csvFilePath, String htmlFilePath) {
        String csvCharsetName = "windows-1251";

        try (Scanner scanner = new Scanner(new FileInputStream(csvFilePath), csvCharsetName); PrintWriter writer = new PrintWriter(htmlFilePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            String htmlCharsetName = "utf-8";
            writer.println("<meta charset= \"" + htmlCharsetName + "\">");
            writer.println("<title>Задача 'CSV'</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=1>");

            while (scanner.hasNextLine()) {
                writer.print("<tr>" + System.lineSeparator() + "<td>");
                boolean isCellBeginning = true;

                StringBuilder line = new StringBuilder();
                line.append(scanner.nextLine());
                int quotesCount = 0;
                int quotesCountInCell = 0;

                for (int i = 0; i < line.length(); ++i) {
                    char character = line.charAt(i);

                    if (character == '"') {
                        ++quotesCount;
                        ++quotesCountInCell;

                        if (isCellBeginning) {
                            quotesCountInCell -= 2;
                        } else if (quotesCountInCell % 2 == 1) {
                            writer.print("\"");
                        }
                    }

                    isCellBeginning = false;

                    if (i == line.length() - 1 && quotesCount % 2 == 1) {
                        writer.print(getLineToPrint(character));

                        while (scanner.hasNextLine()) {
                            String newLine = scanner.nextLine();
                            writer.print("<br/>");

                            if (newLine.length() != 0) {
                                line.append(newLine);
                                break;
                            }
                        }
                    } else if (character == ',' && quotesCount % 2 == 0) {
                        quotesCountInCell = 0;
                        isCellBeginning = true;
                        writer.print("</td>" + System.lineSeparator() + "<td>");
                    } else {
                        writer.print(getLineToPrint(character));
                    }
                }

                writer.println("</td>" + System.lineSeparator() + "</tr>");
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл: " + csvFilePath);
        } catch (Exception e) {
            System.out.println("Возникла ошибка при чтении из файла: " + e.getMessage());
        }
    }

    private static String getLineToPrint(char c) {
        if (c == '<') {
            return "&lt;";
        }

        if (c == '>') {
            return "&gt;";
        }

        if (c == '&') {
            return "&amp;";
        }

        if (c == '"') {
            return "";
        }

        return Character.toString(c);
    }
}