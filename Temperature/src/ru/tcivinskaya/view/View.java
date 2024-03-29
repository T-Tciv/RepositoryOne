package ru.tcivinskaya.view;

import ru.tcivinskaya.controller.Controller;
import ru.tcivinskaya.main.Units;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class View {
    private Controller controller;
    private double finalTemperature;
    private DefaultComboBoxModel<String> initialListModel = new DefaultComboBoxModel<>(new String[]{"Цельсий", "Кельвин", "Фаренгейт"});
    private JComboBox<String> initialUnitsList = new JComboBox<>(initialListModel);
    private DefaultComboBoxModel<String> finalListModel = new DefaultComboBoxModel<>(new String[]{"Цельсий", "Кельвин", "Фаренгейт"});
    private JComboBox<String> finalUnitsList = new JComboBox<>(finalListModel);
    private JTextField initialTemperatureTextField;
    private JTextField finalTemperatureTextField;

    public View() {
        JFrame frame = new JFrame("Перевод температур");
        frame.setSize(600, 400);
        Dimension minimumSize = new Dimension(300, 200);
        frame.setMinimumSize(minimumSize);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());
        Font font = new Font("Verdana", Font.PLAIN, 18);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.25;
        constraints.fill = GridBagConstraints.BOTH;
        initialUnitsList.setFont(font);
        frame.add(initialUnitsList, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.25;
        constraints.fill = GridBagConstraints.BOTH;
        finalUnitsList.setFont(font);
        frame.add(finalUnitsList, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        initialTemperatureTextField = new JTextField();
        frame.add(initialTemperatureTextField, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        finalTemperatureTextField = new JTextField();
        finalTemperatureTextField.setEditable(false);
        frame.add(finalTemperatureTextField, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.weighty = 0.25;
        JButton button = new JButton("Перевести");
        button.setFont(new Font("Verdana", Font.PLAIN, 20));
        button.addActionListener(x -> controller.getFinalTemperature());
        frame.add(button, constraints);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public String getInitialUnit() {
        return (String) initialUnitsList.getSelectedItem();
    }

    public String getFinalUnit() {
        return  (String) finalUnitsList.getSelectedItem();
    }

    public double getInitialTemperature() {
        String inputLine = initialTemperatureTextField.getText();

        if (!isNumber(inputLine)) {
            throw new IllegalArgumentException("Температурой может быть только число");
        }

        return Double.parseDouble(inputLine);
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setFinalTemperature(double finalTemperature) {
        this.finalTemperature = finalTemperature;
    }

    public void showTemperature() {
        finalTemperatureTextField.setText(Objects.toString(finalTemperature));
    }
}
