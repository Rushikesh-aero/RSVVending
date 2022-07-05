package com.company;

public class Constants {

    public static String EMPTY_STRING = "";
    public static int ITEM_ID = 1;
    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 500;
    public static String ADMIN_PANEL_LABEL = ":: Admin Panel ::";
    public static String UNLOCK_ADMIN_LABEL = "Unlock Admin";
    public static String ADMIN_PASSWORD = "rsvending";
    public static String RESTOCKING_ADMIN_LABEL = "<HTML>Please wait <br/> RESTOCKING IN PROGRESS !</HTML>";
    public static String ADMIN_REPORT_LABEL = ":: Admin Report ::";
    public static String ADMIN_REPORT_LABEL_CAN_AVAILABLE = "Total Cans Available: ";
    public static String ADMIN_REPORT_LABEL_CAN_SOLD      = "Total Cans Sold     : ";
    public static String ADMIN_REPORT_LABEL_PAYMENT_CASH  = "Total Cash Payment  : ";
    public static String ADMIN_REPORT_LABEL_PAYMENT_CARD  = "Total Card Payment  : ";
    public static String STOCK_RESET_BUTTON_LABEL  = "OK";


    public static String ADMIN_RESET_CANS = "Restock Cans ";
    public static String ADMIN_RESET_CANS_ADDED = " Cans Added To The Stock !";

    public static String WELCOME_LABEL = "<HTML>Welcome<br/>Please Choose one of the Drinks !</HTML>";
    public static String SELECTION_LABEL = "";
    public static DrinkItem SELECTED_ITEM = new DrinkItem();


    public static String CASH_BUTTON_LABEL = "Pay by Cash";
    public static String CARD_BUTTON_LABEL = "Pay by Card";
    public static double PAYMENT_CASH = 0;
    public static double PAYMENT_CARD = 0;


    public static String CAN_DELIVERY_LABEL = "<HTML>Hooray, Payment Successful<br/>Here is your Drink !</HTML>";
    public static int TOTAL_CAN_AVAILABLE=100;
    public static int TOTAL_CAN_SOLD=0;
}
