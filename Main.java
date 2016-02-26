package com.Brendon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner scanner1 = new Scanner(System.in);
            BufferedReader importFile = new BufferedReader(new FileReader("coffee.txt"));


            String line = importFile.readLine();

            String[] temp = null;
            HashMap<String, Double> drinkCost = new HashMap<String, Double>(); // This is for the stores cost
            HashMap<String, Double> drinkCharge = new HashMap<String, Double>(); //  This is what they charge
            LinkedList drinkList = new LinkedList();
            LinkedList sold = new LinkedList();
            LinkedList expenses = new LinkedList();
            LinkedList revenue = new LinkedList();
            LinkedList profits = new LinkedList();


            /*
            This section of code runs through the document, separates the information and stores them in
            the appropriate Hashmap.
             */
            while (line != null) {

                temp = line.split(";");
                String drink = temp[0];
                String temp1 = temp[1];
                String temp2 = temp[2];
                Double wholeCost = Double.parseDouble(temp1);
                Double custCost = Double.parseDouble(temp2);
                drinkCost.put(drink, wholeCost);
                drinkCharge.put(drink, custCost);

                line = importFile.readLine();

            }

            for (String key : drinkCost.keySet()) {

                String drink = key;
                drinkList.add(drink);
                //System.out.println("Please enter how may " + drink + "'s were sold: ");

                while (true) { // This will verify input for units sold (whole numbers check)
                    System.out.println("Please enter how may " + drink + "'s were sold: ");
                    String test = scanner1.next();

                    try {
                        int input = Integer.parseInt(test);
                        sold.add(input);
                        break;

                    } catch (NumberFormatException NFE) {
                        System.out.println("That is not a correct entry");

                    }

                }
            }
            /*
            I know I could string fromat the decimal but I found this on stacksocial and wanted
            to try it.
             */
            NumberFormat round = DecimalFormat.getInstance();
            round.getMinimumFractionDigits(2);
            round.getMaximumFractionDigits(2);
            round.setRoundingMode(RoundingMode.DOWN);




            importFile.close();
        }


            catch(IOException IOE){

                System.out.println("no file found");
                System.out.println(IOE);
            }

        }
    }
