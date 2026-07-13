//tsa -> Task Scheduler Application

import java.lang.System;
import java.util.Scanner;
class tsa{
    public static void main(String[] args) {
        Scanner tsa = new Scanner(System.in);
        System.out.print("Hi I am your Task Scheduler Application.  ");
        System.out.print("Do you want more information about me? (yes/no):");   
        String response = tsa.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("I am a simple application that helps you schedule tasks and manage your time effectively.");
        } else if (response.equalsIgnoreCase("no")) {
            System.out.println("Alright! Let's get started with scheduling your tasks.");
        } else {
            System.out.println("Invalid response. Please enter 'yes' or 'no'.");
        }
        //System.out.println(response);
    }
}