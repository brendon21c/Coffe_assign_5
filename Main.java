package com.Brendon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args)  {

        try {


            BufferedReader importFile = new BufferedReader(new FileReader("coffee.txt"));

            String line = importFile.readLine();

            String[] temp = null;
            HashMap<String, Double> drinkCost = new HashMap<String, Double>(); // This is for the stores cost
            HashMap<String, Double> drinkCharge = new HashMap<String, Double>(); //  This is what they charge
            LinkedList sold = new LinkedList();
            LinkedList expenses = new LinkedList();
            LinkedList revenue = new LinkedList();
            LinkedList prifts = new LinkedList();


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
                drinkCost.put(drink,wholeCost);
                drinkCharge.put(drink,custCost);

                line = importFile.readLine();

            }

            for (int x = 0 ; x < drinkCost.size() ; x++) {

                String drink =

            }



            importFile.close();


        }
        catch (IOException IOE) {

            System.out.println("no file found");
            System.out.println(IOE);
        }

    }
}
