package com.Brendon;

import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;

import java.io.*;
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
            LinkedList<Integer> sold = new LinkedList<Integer>();
            LinkedList<Double> expenses = new LinkedList<Double>();
            LinkedList<Double> revenue = new LinkedList<Double>();
            LinkedList<Double> profits = new LinkedList<Double>();


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
                drinkList.add(drink); // adds drinks name
                //System.out.println("Please enter how may " + drink + "'s were sold: ");

                while (true) { // This will verify input for units sold (whole numbers check)
                    System.out.println("Please enter how may " + drink + "'s were sold: ");
                    String test = scanner1.next();

                    try {
                        int input = Integer.parseInt(test); // adds number sold
                        sold.add(input);
                        break;

                    } catch (NumberFormatException NFE) {
                        System.out.println("That is not a correct entry");

                    }

                }
            }

            // this block adds up the expenses for each drink.
            for (int x = 0 ; x < drinkList.size(); x++) {

                String drinkTemp = drinkList.get(x).toString();
                Double drinkCostTemp = drinkCost.get(drinkTemp);
                int soldTemp = Integer.parseInt(sold.get(x).toString());
                Double drinkCostExpense = drinkCostTemp * soldTemp;
                String drinkCostExpense2 = String.format("%.2f", drinkCostExpense);
                Double drinkCostExpense3 = Double.valueOf(drinkCostExpense2);
                expenses.add(drinkCostExpense3);

            }

            // This block adds up the revenues from sales.
            for (int x = 0 ; x < drinkList.size(); x++) {

                String drinkTemp = drinkList.get(x).toString();
                Double drinkCostTemp = drinkCharge.get(drinkTemp);
                int chargeTemp = Integer.parseInt(sold.get(x).toString());
                Double drinkCharge1 = drinkCostTemp * chargeTemp;
                String drinkCharge2 = String.format("%.2f", drinkCharge1);
                Double drinkCharge3 = Double.valueOf(drinkCharge2);
                revenue.add(drinkCharge3);

            }

            // This adds ptofits to it's list.
            for (int x = 0 ; x < drinkList.size() ; x++) {

                Double rev = revenue.get(x);
                Double exp = expenses.get(x);

                Double profit = rev - exp;
                String profit2 = String.format("%.2f", profit);
                Double profit3 = Double.valueOf(profit2);
                profits.add(profit3);

            }

            // Totals for the day.
            int soldTotal = 0;
            Double expTotal = 0.00;
            Double revTotal = 0.00;
            Double profitTotal = 0.00;

            for (int x = 0 ; x < drinkList.size() ; x++) { // calculating totals

                soldTotal += sold.get(x);
                expTotal += expenses.get(x);
                revTotal += revenue.get(x);
                profitTotal += profits.get(x);

            }

            // This section creates a new file based on User input
            FileWriter writer = new FileWriter("sales-report.txt", false);
            BufferedWriter writer2 = new BufferedWriter(writer);



            //writting the main block
            for (int x = 0 ; x < drinkList.size() ; x++) {

                writer2.write(drinkList.get(x) + ": Sold " + sold.get(x) + ", Expenses $" +
                expenses.get(x) + ", Revenue $" + revenue.get(x) + ", Profit $" + profits.get(x) + "\n");

            }

            writer2.write("\n");

            writer2.write("Totals: Sold: " + soldTotal + ", Expenses: $" + String.format("%.2f", expTotal) +
            ", Revenue $" + String.format("%.2f",revTotal) + ", Profits $" + String.format("%.2f",profitTotal));


            importFile.close();
            writer2.close();
        }


            catch(IOException IOE){

                System.out.println("no file found");
                System.out.println(IOE);
            }

        }
    }
