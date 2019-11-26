package com.patoguereque.dados;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final static String HEADER =
            " .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |  ________    | || |      __      | || |  ________    | || |     ____     | || |    _______   | |\n" +
            "| | |_   ___ `.  | || |     /  \\     | || | |_   ___ `.  | || |   .'    `.   | || |   /  ___  |  | |\n" +
            "| |   | |   `. \\ | || |    / /\\ \\    | || |   | |   `. \\ | || |  /  .--.  \\  | || |  |  (__ \\_|  | |\n" +
            "| |   | |    | | | || |   / ____ \\   | || |   | |    | | | || |  | |    | |  | || |   '.___`-.   | |\n" +
            "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  _| |___.' / | || |  \\  `--'  /  | || |  |`\\____) |  | |\n" +
            "| | |________.'  | || ||____|  |____|| || | |________.'  | || |   `.____.'   | || |  |_______.'  | |\n" +
            "| |              | || |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n" +
            "\n";
    
    private final static int DICE_AMOUNT = 2;
    private final static Dado[] DICE = new Dado[DICE_AMOUNT];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < DICE_AMOUNT; i++) {
            DICE[i] = new Dado();
        }

        clearScreen();
        if (!askToPlay(scanner, "¿Quieres jugar a los dados? (s/n): ")) {
            return;
        }

        do {
            int nextRoll = 1;
            int count = 2;
            int result = 0;
            String[][] diceOutput = new String[DICE_AMOUNT][4];
            for (int s = 1; s <= 55; s++) {
                clearScreen();
                System.out.print("Tirando dados");
                for (int i = 0; i < s % 3; i++) {
                    System.out.print(".");
                }
                System.out.println();

                if (s == nextRoll) {
                    result = 0;
                    for (int i = 0; i < DICE.length; i++) {
                        DICE[i].roll();
                        diceOutput[i] = DICE[i].getDie();
                        result += DICE[i].getResult();
                    }
                    nextRoll += count++;
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < DICE_AMOUNT; j++) {
                        System.out.print(diceOutput[j][i] + "   ");
                    }
                    System.out.println();
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Resultado: " + result);
            if (result == 7) {
                System.out.println("¡Eres un ganador!");
            } else {
                System.out.println("No has ganado. Suerte a la próxima.");
            }
            System.out.println();
        } while (askToPlay(scanner, "¿Quieres jugar de nuevo? (s/n): "));
    }

    private static boolean askToPlay(Scanner scanner, String message) {
        String in;
        do {
            System.out.println(message);
            in = scanner.next();
        } while (!in.equalsIgnoreCase("s") && !in.equalsIgnoreCase("n"));
        return in.equalsIgnoreCase("s");
    }

    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(HEADER);
    }
}
