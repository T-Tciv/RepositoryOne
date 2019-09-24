package ru.tcivinskaya.view;

import javax.swing.*;
import java.awt.*;

public class View {
    private double initialTemperature;
    private double finalTemperature;
    private DefaultComboBoxModel<String> initialListModel = new DefaultComboBoxModel<>(new String[] {"Цельсий", "Кельвин", "Фаренгейт"});
    private JComboBox<String> initialUnitsList = new JComboBox<>(initialListModel);
    private DefaultComboBoxModel<String> finalListModel = new DefaultComboBoxModel<>(new String[] {"Цельсий", "Кельвин", "Фаренгейт"});
    private JComboBox<String> finalUnitsList = new JComboBox<>(finalListModel);

    public View() {
        JFrame frame = new JFrame("Перевод температур");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3, 2));
        Font font = new Font("Verdana", Font.PLAIN, 18);

        JLabel label = new JLabel("Выберите начальные единицы измерения:");
        label.setFont(font);
        frame.add(label);

        initialUnitsList.setFont(font);
        frame.add(initialUnitsList);

        label = new JLabel("Выберите конечные единицы измерения:");
        label.setFont(font);
        frame.add(label);

        finalUnitsList.setFont(font);
        frame.add(finalUnitsList);

        label = new JLabel("Введите температуру в выбраных (начальных) единицах измерения:");
        label.setFont(font);
        frame.add(label);
    }

    public JComboBox<String> getInitialUnitsList() {
        return initialUnitsList;
    }

    public JComboBox<String> getFinalUnitsList() {
        return finalUnitsList;
    }
}
