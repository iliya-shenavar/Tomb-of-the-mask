import java.util.Scanner;
import java.util.Random;

public class TombOfTheMaskGame {

    public static final char PLAYER_CHAR = '@';
    private static final char COIN_CHAR = '*';
    private static Random random;
    private static Scanner scanner;
    private static int playerRow;
    private static int playerCol;
    private static int newPlayerRow;
    private static int newPlayerCol;
    public static int coinRow;
    public static int coinCol;
    public static int life = 3;
    public static int step = 0;
    public static int tjump = 2;
    public static int tbre = 2;
    public static long start;
    public static long finish;

    public static void main(String[] args) {
        char map[][] = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', '!', '#', '#', ' ', '#', '#', '#', '#', '#', '!', '#', '#', '#', '#', '#', ' ', '#', '#', '!', '#'},
                {'#', ' ', '#', '#', ' ', ' ', ' ', '!', ' ', ' ', ' ', ' ', ' ', '!', ' ', ' ', ' ', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '!', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', '!', ' ', ' ', ' ', '#'},
                {'#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#'},
                {'#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '!', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        // Print player:
        playerRow = 19;
        playerCol = 4;
        map[playerRow][playerCol] = PLAYER_CHAR;


        // Place coins:
        placeCoins(map);
        // Menu
        System.out.println("Welcome to Tomb of the Mask!");
        System.out.println("1.PLAY GAME");
        System.out.println("2.LEADERBOARD");
        System.out.println("3.EXIT");
        Scanner menu = new Scanner(System.in);
        int men = menu.nextInt();

        if (men == 1) {

        } else if (men == 2) {
            System.out.println("We don't have Leaderboard now please come back later :)");
            System.exit(0);
        } else if (men == 3) {
            System.exit(0);
        } else {
            System.out.println("EROR");
            System.exit(0);
        }

        // Print map:


            for (int x = 0; x < 21; x++) {
                for (int y = 0; y < 21; y++) {
                    System.out.print(map[x][y]);
                }
                System.out.println();
            }






        random = new Random();
        scanner = new Scanner(System.in);
        start = System.currentTimeMillis();




        // Read player moves and update the map and life left
        while (true) {
            System.out.println("life: " + life);
            clearconsole();
            //  System.out.print("\033[H\033[2J");
       //     System.out.flush();
            char move = scanner.next().charAt(0);
            try {
                movePlayer(map, move);
            } catch (Exception e) {
                System.out.println("Out of area!");
            }
            // Print updated map
            for (int x = 0; x < 21; x++) {
                for (int y = 0; y < 21; y++) {

                    if(x-playerRow <= 4 && x >= playerRow -4 && y - playerCol <= 6 && y >= playerCol -6 ){
                        System.out.print(map[x][y]);
                    }
                    else {
                        System.out.print('?');
                    }

                }

                System.out.println();

            }

        }

    }

    public static void placeCoins(char[][] map) {
        random = new Random();

//        for (int i = 0; i < 1; i++) {
            coinRow = random.nextInt(21);
            coinCol = random.nextInt(21);

            // Check if the random position is not a wall or the player
            while (map[coinRow][coinCol] != ' ') {
                coinRow = random.nextInt(21);
                coinCol = random.nextInt(21);
            }

            map[coinRow][coinCol] = COIN_CHAR;
        }

  //  }

    private static void movePlayer(char[][] map, char move) {
        newPlayerRow = playerRow;
        newPlayerCol = playerCol;

        switch (move) {
            case 'W','w':
                newPlayerRow--;
                step++;
                break;
            case 'S','s':
                newPlayerRow++;
                step++;
                break;
            case 'A','a':
                newPlayerCol--;
                step++;
                break;
            case 'D','d':
                newPlayerCol++;
                step++;
                break;

        }
        // jump

            if (move == 'j' || move == 'J') {
                Scanner jump = new Scanner(System.in);
                char jum = jump.next().charAt(0);
                if (tjump != 0) {

                    switch (jum) {
                        case 'W', 'w':
                            newPlayerRow = newPlayerRow - 2;
                            if (map[newPlayerRow-1][newPlayerCol] == ' ') {
                                step++;
                            }
                            step++;
                            break;
                        case 'S', 's':
                            newPlayerRow = newPlayerRow + 2;
                            if (map[newPlayerRow+1][newPlayerCol] == ' ') {
                                step++;
                            }
                            step++;
                            break;
                        case 'A', 'a':
                            newPlayerCol = newPlayerCol - 2;
                            if (map[newPlayerRow][newPlayerCol-1] == ' ') {
                                step++;
                            }
                            step++;
                            break;
                        case 'D', 'd':
                            newPlayerCol = newPlayerCol + 2;
                            if (map[newPlayerRow][newPlayerCol+1] == ' ') {
                                step++;
                            }

                            step++;
                            break;

                    }
                    tjump--;
                } else {
                    System.out.println("You use your jump two times!");
                }
            }
            // break wall
        if (move == 'g' || move == 'G'){
            Scanner breakk = new Scanner(System.in);
            char bre = breakk.next().charAt(0);
            if (tbre != 0) {

                switch (bre) {
                    case 'W', 'w':
                        if (map[newPlayerRow-1][newPlayerCol] == '#') {


                            map[newPlayerRow-1][newPlayerCol] = ' ';
                            tbre--;
                        }
                        break;
                    case 'S', 's':
                        if (map[newPlayerRow+1][newPlayerCol] == '#') {


                            map[newPlayerRow+1][newPlayerCol] = ' ';
                            tbre--;
                        }
                        break;
                    case 'A', 'a':
                        if (map[newPlayerRow][newPlayerCol-1] == '#') {


                            map[newPlayerRow][newPlayerCol-1] = ' ';
                            tbre--;
                        }
                        break;
                    case 'D', 'd':
                        if (map[newPlayerRow][newPlayerCol+1] == '#') {


                            map[newPlayerRow][newPlayerCol+1] = ' ';
                            tbre--;
                        }
                        break;

                }

            } else {
                System.out.println("You use your break wall two times!");
            }
        }

        // Check if the new position is valid
        if (map[newPlayerRow][newPlayerCol] == ' ') {

            // Clear the previous position
           map[playerRow][playerCol] = ' ';
            // Update the player's position
            playerRow = newPlayerRow;
            playerCol = newPlayerCol;


            // Place the player in the new position
            map[playerRow][playerCol] = PLAYER_CHAR;
        }
        // check won and show time
        if (map[newPlayerRow][newPlayerCol] == '*') {
            System.out.println("YOU WON!");
            finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("Time:"+timeElapsed/1000+" second");
            System.out.println("steps: "+(step + (life-3)));
            System.exit(0);
        }
        if (map[newPlayerRow][newPlayerCol] == '#') {
            life--;
            if (life == 0) {
                System.out.println("YOU LOST!");
                finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("Time:" + timeElapsed / 1000 + " second");
                System.out.println("steps: " + (step + (life - 3)));
                System.exit(0);
            }
        }
        if (map[newPlayerRow][newPlayerCol] == '!') {


                life--;
                System.out.println("You hit the bomb!");
                if (life == 0) {
                    System.out.println("YOU LOST!");
                    System.exit(0);
                }

            map[playerRow][playerCol] = ' ';
                playerRow = 19;
                playerCol = 4;
            map[playerRow][playerCol] = PLAYER_CHAR;
            }



    }
        public final static void clearconsole()
        {
        try{
            final String os= System.getProperty("os.name");
            if(os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }   catch (final Exception e)
        {

        }
        }
    }






