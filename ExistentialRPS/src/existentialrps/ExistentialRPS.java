package existentialrps;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * It's called Existential Rock Paper Scissors, because playing it might cause
 * you to have an existential crisis...
 *
 * --sans GUI--
 *
 * @author Tim Barber
 */
public class ExistentialRPS {

    public static DeepThought Computer = new DeepThought();
    public static Scanner keyboard = new Scanner(System.in);
    public static String[] prompts;
    public static Random random = new Random();
    public static RPSEngine engine = new RPSEngine();
    public static double winPercent = 1;
    public static int roundsPlayed = 0;
    public static int gamesWon = 0;
    public static DecimalFormat df = new DecimalFormat("##0.00");

    static {
        prompts = new String[4];
        prompts[0] = "Choose your weapon.";
        prompts[1] = "What is your choice?";
        prompts[2] = "Choose wisely.";
        prompts[3] = "Don't mess up...";
    }

    public static void main(String[] args) {
        System.out.println("\nTim Barber\tNov 2018\tAPCS\n"); //Header

        Computer.setDiffLevel(setDifficulty()); // difficulty level

        int amt = 0;
        while (amt < 1 || amt > 100) {
            System.out.print("How many times would you like to play? ");
            amt = keyboard.nextInt();
        }

        for (int i = 0; i < amt; i++) {

            engine.setP2(Computer.getMove()); // get computer's move
            System.out.println("\n-----\nComputer's move has been chosen.");

            engine.setP1(getPlayer()); // get player's move

            declareWinner();
        }
    }

    public static int setDifficulty() {
        int difficulty = 0;
        while (difficulty < 1 || difficulty > 10) {
            System.out.print("What is your preferred difficulty? (1 - 10): ");
            difficulty = keyboard.nextInt();
        }
        return difficulty;

    }

    public static String getPlayer() {
        String tempChoice = "";
        while (!tempChoice.equals("r") && !tempChoice.equals("p") && !tempChoice.equals("s")) {
            System.out.print(prompt() + " (r, p, s): ");
            tempChoice = keyboard.next().substring(0, 1).toLowerCase();
        }
        return tempChoice;
    }

    public static String prompt() {
        int choice = random.nextInt(prompts.length - 1);
        return prompts[choice];
    }

    public static void declareWinner() {
        System.out.println("\nYou chose: " + engine.getP1() + ". The computer chose " + engine.getP2() + ".");
        if (engine.getWinner() == 0) {
            System.out.println("Congratulations! You won this round!");
            gamesWon++;
            roundsPlayed++;
        } else if (engine.getWinner() == 1) {
            System.out.println("Oof. You lost this round to the computer.");
            roundsPlayed++;
        } else {
            System.out.println("Draw! Nobody wins. This is real life kids.");
           
        }
        winPercent = gamesWon / (double) roundsPlayed;
        System.out.println("Your win percentage: %" + df.format(winPercent * 100) + "\n");
        Computer.addPastMove(engine.getP1());
    }

}
/*
 * The MIT License
 *
 * Copyright (c) 2018 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
