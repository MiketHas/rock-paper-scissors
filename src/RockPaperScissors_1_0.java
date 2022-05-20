import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors_1_0 {

    //Starting points
    static int playerPoints;
    static int systemPoints;

    // Counting how many times each of these were picked by the player. Zeroes after two picks in a row.
    static int userChoiceRock = 0;
    static int userChoicePaper = 0;
    static int userChoiceScissors = 0;

    public static void main(String[] args) {

        rock_paper_scissors_game();

    }

    public static void rock_paper_scissors_game() {

        Scanner input = new Scanner(System.in);

        System.out.println("--- Rock-Paper-Scissors ---\nYou know the rules!\nFirst one to get 6 points wins! You go first!");

        playerPoints = 0;
        systemPoints = 0;

        boolean gameON = true;

        // Loop - actual GAME
        while (gameON) {

            System.out.println();
            System.out.println("Pick (R)ock, (P)aper or (S)cissors.");

            // Player move:
            String player = input.nextLine();

            // Computer move:
            String computerChoice = computerChoice();

            if (!player.equalsIgnoreCase("r") && !player.equalsIgnoreCase("p") && !player.equalsIgnoreCase("s") && !player.equalsIgnoreCase("over")) {
                System.out.println("Hey! Type r/p/s! ...or \"over\" if you want to quit NOW, but you want to play, right?");
            } else if (player.equalsIgnoreCase("R")) { // player picked Rock
                chosenRock(computerChoice);
            } else if (player.equalsIgnoreCase("P")) { // player picked Paper
                chosenPaper(computerChoice);
            } else if (player.equalsIgnoreCase("S")) { // player picked Scissors
                chosenScissors(computerChoice);
            }

            // Game Over
            if (player.equalsIgnoreCase("over")) {
                gameON = false;
                System.out.println(playerPoints + ":" + systemPoints);
            } else if (playerPoints == 6) {
                System.out.println("..aaand it's GAME OVER! You won! " + playerPoints + ":" + systemPoints);
                gameON = false;
            } else if (systemPoints == 6) {
                System.out.println("..aaand it's GAME OVER! You lost! " + playerPoints + ":" + systemPoints);
                gameON = false;
            }
        }

        System.out.println("Thank you for playing! Type 1 to PLAY AGAIN or 2 to EXIT");

        boolean exit = false;
        while (!exit) {
            String ending = input.next();
            if (ending.equals("1")) {
                System.out.println("You chose WISELY! REMATCH!");
                System.out.println();
                rock_paper_scissors_game();
            } else if (ending.equals("2")) {
                exit = true;
                System.exit(0);
            } else {
                System.out.println("Type 1 or 2!");
            }
        }
    }

    // Methods counting player's choices.
    // After 2 picks of the same type in a row (ex.2xRock), Computer will automatically pick the winning scenario.
    public static void userChoseRock() {
        userChoiceRock++;
        userChoicePaper=0;
        userChoiceScissors=0;
    }

    public static void userChosePaper() {
        userChoicePaper++;
        userChoiceRock=0;
        userChoiceScissors=0;
    }

    public static void userChoseScissors() {
        userChoiceScissors++;
        userChoiceRock=0;
        userChoicePaper=0;
    }

    public static void chosenRock(String computerChoice) {
        switch (computerChoice) {
            case "Rock" -> {
                System.out.println(computerChoice);
                draw();
                userChoseRock();
            }
            case "Paper" -> {
                System.out.println(computerChoice);
                youLose();
                userChoseRock();
            }
            case "Scissors" -> {
                System.out.println(computerChoice);
                youWin();
                userChoseRock();
            }
        }
    }

    public static void chosenPaper(String computerChoice) {
        switch (computerChoice) {
            case "Scissors" -> {
                System.out.println(computerChoice);
                youLose();
                userChosePaper();
            }
            case "Rock" -> {
                System.out.println(computerChoice);
                youWin();
                userChosePaper();
            }
            case "Paper" -> {
                System.out.println(computerChoice);
                draw();
                userChosePaper();
            }
        }
    }

    public static void chosenScissors(String computerChoice) {
        switch (computerChoice) {
            case "Paper" -> {
                System.out.println(computerChoice);
                youWin();
                userChoseScissors();
            }
            case "Rock" -> {
                System.out.println(computerChoice);
                youLose();
                userChoseScissors();
            }
            case "Scissors" -> {
                System.out.println(computerChoice);
                draw();
                userChoseScissors();
            }
        }
    }

    // Round ending results:
    public static void youWin() {
        playerPoints++;
        System.out.println("You win! " + playerPoints + ":" + systemPoints);
    }

    public static void youLose() {
        systemPoints++;
        System.out.println("You lose! " + playerPoints + ":" + systemPoints);
    }

    public static void draw() {
        System.out.println("Draw! Nobody gets a point! " + playerPoints + ":" + systemPoints);
    }

    // Detrmining computer's pick
    public static String computerChoice() {

        String[] rpsTab = {"Rock", "Paper", "Scissors"};
        Random rpsSys = new Random();
        int n = rpsSys.nextInt(3);

        if (userChoiceRock == 2) {
            n = 1;
            userChoiceRock = 0;
        } else if (userChoiceScissors == 2) {
            n = 0;
            userChoiceScissors = 0;
        } else if (userChoicePaper == 2) {
            n = 2;
            userChoicePaper = 0;
        }
        return rpsTab[n];
    }
}


