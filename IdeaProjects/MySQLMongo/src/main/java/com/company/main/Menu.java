package com.company.main;

import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static int dbMenu() {
        while(true) {
            System.out.println("SELECT DATABASE:  ");
            System.out.println("\t1. SQL");
            System.out.println("\t2. Mongo");
            System.out.println("\t-----------");
            System.out.println("\t0. Exit" );
            String database = sc.next();

            switch (database) {
                case "0":
                    break;
                case "1":
                    System.out.println("SQL");
                    return 1;
                case "2":
                    System.out.println("Mongo");
                    return 2;
                default:
                    System.out.println("Error! Select 0, 1 or 2!");
                    break;
            }
        }
    }

    public static int menu() {
        while(true) {
            System.out.println("MENU");
            System.out.println("\t----------------------");
            System.out.println("MOVIE");
            System.out.println("\t1. Insert movie");
            System.out.println("\t2. Show movie");
            System.out.println("\t3. Show all movies");
            System.out.println("\t4. Delete movie");
            System.out.println("\t5. Update movie");
            System.out.println("\t----------------------");
            System.out.println("\tACTOR");
            System.out.println("\t6. Insert actor");
            System.out.println("\t7. Show actor");
            System.out.println("\t8. Show all actors");
            System.out.println("\t9. Delete actor");
            System.out.println("\t10. Update actor");
            System.out.println("\t----------------------");
            System.out.println("\tRELATION");
            System.out.println("\t11. Insert relation");
            System.out.println("\t12. Delete relation");
            System.out.println("\t13. Query relation");
            System.out.println("\t14. Query all relations");
            System.out.println("\t----------------------");
            System.out.println("\t0. Exit");

            String action = sc.next();

            switch (action) {
                case "0":
                    break;
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                case "8":
                    return 8;
                case "9":
                    return 9;
                case "10":
                    return 10;
                case "11":
                    return 11;
                case "12":
                    return 12;
                case "13":
                    return 13;
                case "14":
                    return 14;

                default:
                    System.out.println("Error! Enter a valid number!");
                    break;
            }
        }
    }
}
