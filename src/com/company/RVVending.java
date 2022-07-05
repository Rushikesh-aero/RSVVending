package com.company;

import sun.font.TextLabel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class RVVending extends JFrame {

    JLabel selectionDisplayLabel;
    JPanel paymentButtonsPanel;
    JPanel canDeliveryPanel;
    JPanel resetAdminPanel;
    JPanel adminResetButtonPanel;
    JTextField numberOfCansField;

    public RVVending(String s) {
        super(s);
        JPanel mainContainerPanel = new JPanel();
        //mainContainerPanel.setBackground(Color.BLUE);
        mainContainerPanel.setLayout(new BoxLayout(mainContainerPanel, BoxLayout.Y_AXIS));
        this.add(mainContainerPanel);

        // Create panel of items to display
        JPanel itemPanel = new JPanel();
        itemPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT/5));
        itemPanel.setBackground(Color.decode("#DDD1c7"));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        itemPanel.setLayout(new GridLayout(3, 4));
        mainContainerPanel.add(itemPanel);

        //Add buttons to panel
        String[] items = new String[]{"Orange", "Lemon", "Vanilla", "Strawberry", "Pineapple", "Grape", "Apple", "Almond", "Cherry", "Lychee"};
        for(String item: items){
            JButton button = new DrinkItem(Constants.ITEM_ID++, item, 1.50);
            button.addActionListener(selectItem());
            itemPanel.add(button);
        }
        
        //Create panel to contain selection panel and admin panel
        JPanel selectionAdminContainerPanel = new JPanel();
        //selectionAdminContainerPanel.setBackground(Color.BLUE);
        selectionAdminContainerPanel.setLayout(new BoxLayout(selectionAdminContainerPanel, BoxLayout.X_AXIS));
        mainContainerPanel.add(selectionAdminContainerPanel);

        // Create panel to display selected Drink and price
        JPanel selectionDisplayPanel = new JPanel();
        selectionDisplayPanel.setBackground(Color.decode("#8db580"));
        selectionDisplayPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        selectionDisplayPanel.setLayout(new GridLayout(3, 4));
        selectionAdminContainerPanel.add(selectionDisplayPanel);

        selectionDisplayLabel = new JLabel(Constants.WELCOME_LABEL);
        selectionDisplayPanel.add(selectionDisplayLabel);

        //Payment buttons panel
        paymentButtonsPanel = new JPanel();
        //paymentButtonsPanel.setBackground(Color.GREEN);
        paymentButtonsPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        paymentButtonsPanel.setLayout(new GridLayout(1, 2));
        selectionDisplayPanel.add(paymentButtonsPanel);

        JButton cashPayButton = new JButton(Constants.CASH_BUTTON_LABEL);
        cashPayButton.addActionListener(makePayment());
        paymentButtonsPanel.add(cashPayButton);
        JButton cardPayButton = new JButton(Constants.CARD_BUTTON_LABEL);
        cardPayButton.addActionListener(makePayment());
        paymentButtonsPanel.add(cardPayButton);

        paymentButtonsPanel.setVisible(false);

        //Can Delivery panel
        canDeliveryPanel = new JPanel();
        //canDeliveryPanel.setBackground(Color.GREEN);
        canDeliveryPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        canDeliveryPanel.setLayout(new GridLayout(1, 2));
        selectionDisplayPanel.add(canDeliveryPanel);

        JLabel canDeliveryLabel = new JLabel(Constants.CAN_DELIVERY_LABEL);
        JButton deliveryItem = Constants.SELECTED_ITEM;
        canDeliveryPanel.add(canDeliveryLabel);
        canDeliveryPanel.add(deliveryItem);

        canDeliveryPanel.setVisible(false);

        // Create panel to display admin controls
        JPanel admincontrolPanel = new JPanel();
        admincontrolPanel.setBackground(Color.decode("#7E8987"));
        admincontrolPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        admincontrolPanel.setLayout(new GridLayout(6, 1));
        selectionAdminContainerPanel.add(admincontrolPanel);

        JLabel adminPanelLabel = new JLabel(Constants.ADMIN_PANEL_LABEL);
        admincontrolPanel.add(adminPanelLabel);

        JPanel unlockAdminPanel = new JPanel();
        //unlockAdminPanel.setBackground(Color.BLUE);
        unlockAdminPanel.setLayout(new BoxLayout(unlockAdminPanel, BoxLayout.X_AXIS));
        admincontrolPanel.add(unlockAdminPanel);

        JLabel unlockAdminLabel = new JLabel(Constants.UNLOCK_ADMIN_LABEL);
        unlockAdminPanel.add(unlockAdminLabel);
        JPasswordField adminPasswordField = new JPasswordField();
        adminPasswordField.addActionListener(checkPassword());
        unlockAdminPanel.add(adminPasswordField);

        resetAdminPanel = new JPanel();
        //resetAdminPanel.setBackground(Color.BLUE);
        resetAdminPanel.setLayout(new BoxLayout(resetAdminPanel, BoxLayout.X_AXIS));
        admincontrolPanel.add(resetAdminPanel);

        JLabel resetAdminLabel = new JLabel(Constants.ADMIN_RESET_CANS);
        resetAdminPanel.add(resetAdminLabel);
        numberOfCansField = new JTextField();
        numberOfCansField.addActionListener(resetStock());
        resetAdminPanel.add(numberOfCansField);

        adminResetButtonPanel = new JPanel();
        JButton resetButton = new JButton(Constants.STOCK_RESET_BUTTON_LABEL);
        resetButton.addActionListener(resetView());
        adminResetButtonPanel.add(resetButton);
        admincontrolPanel.add(adminResetButtonPanel);
        adminResetButtonPanel.setVisible(false);

        resetAdminPanel.setVisible(false);
    }

    private ActionListener selectItem() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Constants.TOTAL_CAN_AVAILABLE>0){
                    if(!selectionDisplayLabel.isVisible()){
                        selectionDisplayLabel.setVisible(true);
                    }
                    resetAdminPanel.setVisible(false);
                    canDeliveryPanel.setVisible(false);
                    DrinkItem btn=(DrinkItem) e.getSource();
                    Constants.SELECTION_LABEL = "<HTML>You have selected :: "+btn.getName()+" ::<br/> Please pay $"+btn.getPrice()+"</HTML>";
                    Constants.SELECTED_ITEM = btn;
                    selectionDisplayLabel.setText(Constants.SELECTION_LABEL);
                    paymentButtonsPanel.setVisible(true);
                    adminResetButtonPanel.setVisible(false);
                }
            }
        };
        return actionListener;
    }

    private ActionListener resetView() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionDisplayLabel.setText(Constants.WELCOME_LABEL);
                resetAdminPanel.setVisible(false);
                adminResetButtonPanel.setVisible(false);
                canDeliveryPanel.setVisible(false);
                numberOfCansField.setText(Constants.EMPTY_STRING);
            }
        };
        return actionListener;
    }

    private ActionListener checkPassword() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField passwordField=(JPasswordField) e.getSource();
                if(String.valueOf(passwordField.getPassword()).equals(Constants.ADMIN_PASSWORD)){
                    paymentButtonsPanel.setVisible(false);
                    canDeliveryPanel.setVisible(false);

                    selectionDisplayLabel.setText("<HTML>"+Constants.ADMIN_REPORT_LABEL+
                                                    "<br/>"+Constants.ADMIN_REPORT_LABEL_CAN_AVAILABLE+Constants.TOTAL_CAN_AVAILABLE+
                                                    "<br/>"+Constants.ADMIN_REPORT_LABEL_CAN_SOLD+Constants.TOTAL_CAN_SOLD+
                                                    "<br/>"+Constants.ADMIN_REPORT_LABEL_PAYMENT_CASH+Constants.PAYMENT_CASH+
                                                    "<br/>"+Constants.ADMIN_REPORT_LABEL_PAYMENT_CARD+Constants.PAYMENT_CARD);


                    resetAdminPanel.setVisible(true);
                    passwordField.setText(Constants.EMPTY_STRING);
                }
            }
        };
        return actionListener;
    }

    private ActionListener makePayment() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn=(JButton) e.getSource();
                if(btn.getText() == Constants.CASH_BUTTON_LABEL){
                    Constants.PAYMENT_CASH+=Constants.SELECTED_ITEM.getPrice();
                }else if(btn.getText() == Constants.CARD_BUTTON_LABEL){
                    Constants.PAYMENT_CARD+=Constants.SELECTED_ITEM.getPrice();
                }
                Constants.TOTAL_CAN_SOLD++;
                Constants.TOTAL_CAN_AVAILABLE--;
                paymentButtonsPanel.setVisible(false);
                selectionDisplayLabel.setText(Constants.EMPTY_STRING);
                ((JButton) canDeliveryPanel.getComponent(1)).setText(Constants.SELECTED_ITEM.getName());
                ((JButton) canDeliveryPanel.getComponent(1)).addActionListener(resetView());
                canDeliveryPanel.setVisible(true);
            }
        };
        return actionListener;
    }

    private ActionListener resetStock() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField field=(JTextField) e.getSource();
                if(Integer.parseInt(field.getText())>0){
                    Constants.TOTAL_CAN_AVAILABLE+=Integer.parseInt(field.getText());
                    Constants.PAYMENT_CASH=0;
                    Constants.PAYMENT_CARD=0;
                    Constants.TOTAL_CAN_SOLD=0;
                    selectionDisplayLabel.setText(field.getText()+Constants.ADMIN_RESET_CANS_ADDED);
                    adminResetButtonPanel.setVisible(true);
                }else{
                    //Display Error
                }

            }
        };
        return actionListener;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RVVending v = new RVVending("RV VENDING MACHINE");
        v.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
        v.setFocusable(true);
    }
}
