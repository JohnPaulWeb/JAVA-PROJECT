import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Testing {


    // we use class for Character
    static class Character {
        String name;
        String description;

        // Declare of Name and description 
        Character(String name, String description) {
            this.name = name;
            this.description = description;
        }


        @Override
        public String toString() {
            return "Name: " + name + "\nDescription: " + description;
        }
    }

    public static void main(String[] args) {

        // This is the scanner of user input 
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Characters Setup 
        HashMap<Integer, Character> characters = new HashMap<>();
        characters.put(1, new Character("Carla", "A brave warrior with unmatched strength."));
        characters.put(2, new Character("Cruxia", "A cunning thief with a quick mind."));
        characters.put(3, new Character("JiPola", "A wise mage who commands the elements."));
        characters.put(4, new Character("Camilo", "A charismatic bard who inspires allies."));

        // Player Character Selection
        System.out.println("Choose your characters:");
        for (int i = 1; i <= characters.size(); i++) {
            System.out.println(i + ". " + characters.get(i).name + " - " + characters.get(i).description);
        }

        // We declare of your Player to Player2 
        // Choosing your Character to 1-4 
        
        System.out.print("Player 1, choose your character (1-4): ");
        int player1Choice = scanner.nextInt();
        System.out.print("Player 2, choose your character (1-4): ");
        int player2Choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // This is the Character of the Player1Choice to Player2Choice
        if (!characters.containsKey(player1Choice) || !characters.containsKey(player2Choice) || player1Choice == player2Choice) {
            System.out.println("Invalid or duplicate character choices. Exiting game.");
            return;
        }

        // Player1 to Choice 
        Character player1Character = characters.get(player1Choice);
        Character player2Character = characters.get(player2Choice);

        System.out.println("\nPlayer 1 chose:");
        System.out.println(player1Character);
        System.out.println("\nPlayer 2 chose:");
        System.out.println(player2Character);

        // Board Setup
        System.out.print("Enter the board size (e.g., 100 for a 10x10 board): ");
        int boardSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline


        // HashMap to read Character to User input
        HashMap<Integer, Integer> snakes = generateSnakes(boardSize, random);
        HashMap<Integer, Integer> ladders = generateLadders(boardSize, random);

        System.out.println("Generated Snakes: " + snakes);
        System.out.println("Generated Ladders: " + ladders);

        int player1Position = 0, player2Position = 0;
        boolean isPlayer1Turn = true;
        
        // This is the beginning of the game 
        System.out.println("\nLet the game begin!");
        System.out.println("First to reach position " + boardSize + " wins!");

        // Game Loop
        while (true) {
            int currentPlayerPosition = isPlayer1Turn ? player1Position : player2Position;
            System.out.println((isPlayer1Turn ? player1Character.name : player2Character.name) + ", press Enter to roll the dice.");
            scanner.nextLine();

            int diceRoll = random.nextInt(6) + 1;
            System.out.println("Rolled a " + diceRoll);

            int newPosition = currentPlayerPosition + diceRoll;

            // Check for overshooting the board
            if (newPosition > boardSize) {
                System.out.println("Roll exceeds the board size! Staying at position " + currentPlayerPosition);
            } else {
                // Check for snakes or ladders
                if (snakes.containsKey(newPosition)) {
                    System.out.println("Oh no! Snake! Sliding down to " + snakes.get(newPosition));
                    newPosition = snakes.get(newPosition);
                } else if (ladders.containsKey(newPosition)) {
                    System.out.println("Yay! Ladder! Climbing up to " + ladders.get(newPosition));
                    newPosition = ladders.get(newPosition);
                }

                System.out.println((isPlayer1Turn ? player1Character.name : player2Character.name) + " moved to position " + newPosition);

                if (isPlayer1Turn) {
                    player1Position = newPosition;
                } else {
                    player2Position = newPosition;
                }

                // Check for winning condition
                if (newPosition == boardSize) {
                    System.out.println((isPlayer1Turn ? player1Character.name : player2Character.name) + " wins the game!");
                    break;
                }
            }

            // Bonus turn if dice roll is 1-6 
            if (diceRoll == 6) {
                System.out.println("Rolled a 6! Bonus turn!");
                continue;
            }

            // Switch turns
            isPlayer1Turn = !isPlayer1Turn;
        }
    }


    // Generate random snakes on the board
    private static HashMap<Integer, Integer> generateSnakes(int boardSize, Random random) {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        int numberOfSnakes = boardSize / 10;

        // this is for do while 
        for (int i = 0; i < numberOfSnakes; i++) {
            int start, end;
            do {
                start = random.nextInt(boardSize - 10) + 11; // Avoid position 1-10
                end = random.nextInt(start - 1) + 1; // Ensure end is less than start
            } while (snakes.containsKey(start));
            snakes.put(start, end);
        }

        return snakes;
    }

    // Generate random ladders on the board
    private static HashMap<Integer, Integer> generateLadders(int boardSize, Random random) {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        int numberOfLadders = boardSize / 10;


        // we declare for  do while for this
        for (int i = 0; i < numberOfLadders; i++) {
            int start, end;
            do {
                start = random.nextInt(boardSize - 10) + 1; // Ensure ladder start is within range
                end = random.nextInt(boardSize - start) + start + 1; // Ensure end is greater than start
            } while (ladders.containsKey(start));
            ladders.put(start, end);
        }

        return ladders;
    }
}