import java.util.Random;
import java.util.Scanner;

public class Uno {

    /**
     * nPlayers: Number of players in the game
     * player: Current Player
     * playerHand[][]: List of Players and their hands
     * currentCard: Keeps track of the card on top of the discard pile
     * CARDS_IN_DECK: Total cards in the deck
     * START_HAND: Initial hand size
     * direction: Direction of the play
     * winner: Stores the winner of game
     */
    public static int nPlayers;
    public static int currentPlayer;
    public static UnoCard playerHand[][];
    public static UnoCard currentCard;
    public static final int CARDS_IN_DECK = 112;
    public static final int START_HAND = 7;
    public static boolean direction = true; // true for regular direction, false for reverse direction
    public static int winner;


    /**
     *
     * @param nPlayers
     */
    public Uno(int nPlayers) {
        //TODO: implement
        this.playerHand = new UnoCard[nPlayers][CARDS_IN_DECK];
        this.currentPlayer = 0;
        this.nPlayers = nPlayers;
    }

    /**
     *
     */
    public static void distributeCards() {
        //TODO: implement
        for(int i = 0; i < nPlayers; i++) {
            for(int j = 0; j < START_HAND; j++) {
                playerHand[i][j] = new UnoCard();
            }
        }
    }

    /**
     *
     * @param player
     * @param index
     */
    public static void removeCardFromHand(int p, int idx) {
        //TODO: implement

        for(int i = 0; i < playerHand[p].length; i++) {
            if(i == idx) {
                for (int j = i; j < (playerHand[p].length - 1); j++) {
                    playerHand[p][j] = playerHand[p][j + 1];
                }
                break;
            }
        }
    }

    /**
     *
     * @param player
     */
    public static void printHandOfPlayer(int p) {
        //TODO: implement
        System.out.println("Player" + " " + (p+1) + "'s" + " " + "Hand" + ":");
        for(int i = 0; i < playerHand[p].length; i++) {
            if (playerHand[p][i] != null) {
                if (playerHand[p][i].card == CARD.WILD | playerHand[p][i].card == CARD.WILD_DRAW4) {
                    System.out.println((i + 1) + ":" + " " + playerHand[p][i].card);
                } else {
                    System.out.println((i + 1) + ":" + " " + playerHand[p][i].color + " " + playerHand[p][i].card);
                }
            }
        }
        System.out.println("0: Draw a card | Skip turn");
    }


    /**
     *
     * @param player
     * @return
     */
    public static int cardsLeft(int p) {
        //TODO: implement
        int counter = 0;
        for(int i = 0; i < playerHand[p].length; i++){
            if(playerHand[p][i] != null){
                counter += 1;
            }
        }
        return counter;
    }

    /**
     *
     * 
     */
    public static void handleWild(Scanner scanner) {
        //TODO: implement
        System.out.println("Played Wild Card.");
        System.out.println("Color changes to?");
        System.out.println("1. Red\n" + "2. Green\n" + "3. Yellow\n" + "4. Blue\n");
        int newColor;
        do {
            newColor = scanner.nextInt();
            switch (newColor) {
                case 1:
                    currentCard.color = COLOR.RED;
                    break;
                case 2:
                    currentCard.color = COLOR.GREEN;
                    break;
                case 3:
                    currentCard.color = COLOR.YELLOW;
                    break;
                case 4:
                    currentCard.color = COLOR.BLUE;
                    break;
                default:
                    System.out.println("Enter a number between 1 and 4");
                    break;
            }
        } while(newColor != 1 & newColor != 2 & newColor != 3 & newColor != 4);
        nextPlayer();
    }

    /**
     *
     */
    public static void nextPlayer() {
        //TODO: implement
        if (direction) {
                if (currentPlayer == nPlayers - 1) {
                    currentPlayer = 0;
                } else {
                    currentPlayer += 1;
                }
        }else {
                if (currentPlayer == 0) {
                    currentPlayer = nPlayers - 1;
                } else {
                    currentPlayer -= 1;
                }
        }
    }

    /**
     *
     * 
     */
    public static void handleWildDraw4(Scanner scanner) {
        //TODO: implement
        System.out.println("Played Wild Draw Four Card.");
        System.out.println("Color changes to?");
        System.out.println("1. Red\n" + "2. Green\n" + "3. Yellow\n" + "4. Blue\n");
        int newColor;
        do {
            newColor = scanner.nextInt();
            switch (newColor) {
                case 1:
                    currentCard.color = COLOR.RED;
                    break;
                case 2:
                    currentCard.color = COLOR.GREEN;
                    break;
                case 3:
                    currentCard.color = COLOR.YELLOW;
                    break;
                case 4:
                    currentCard.color = COLOR.BLUE;
                    break;
                default:
                    System.out.println("Enter a number between 1 and 4");
                    break;
            }
        } while(newColor != 1 & newColor != 2 & newColor != 3 & newColor != 4);

        nextPlayer();

        playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
        playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
        playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
        playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();

        nextPlayer();

    }
    
    /**
     *
     * 
     */
    public static void handleSkip() {
        //TODO: implement
        nextPlayer();
        System.out.println("Player " + (currentPlayer+1) + "'s" + " turn has been skipped!");
        nextPlayer();

    }    
    
    /**
     *
     * 
     */
    public static void handleReverse() {
        //TODO: implement
        System.out.println("The game has been reversed!");
        direction = !direction;

    }
    
    /**
     *
     * 
     */
    public static void handleDraw2() {
        //TODO: implement
        nextPlayer();
        System.out.println("Player " + (currentPlayer+1) + " has to draw 2 cards!");
        playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
        playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
        nextPlayer();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //TODO: implement
        Scanner s = new Scanner (System.in);
        System.out.println("How many Players are there?");
        nPlayers = s.nextInt();
        Uno uno = new Uno(nPlayers);
        distributeCards();
        System.out.println("\nShuffling and Adding cards to Hands...\n");
        System.out.println("First Card:");
        currentCard = new UnoCard();
        System.out.println(currentCard.color + " " + currentCard.card);
        UnoCard nextCard = new UnoCard();
        //currentPlayer = 0;
        //int count = 0;
        boolean zeroFirstTime = false;
        int cardPlayed;
        while(cardsLeft(currentPlayer)!=0) {
            do {
                printHandOfPlayer(currentPlayer);
                System.out.println("Your choice?");
                //s.hasNextInt();
                cardPlayed = s.nextInt();
                if (0 <= cardPlayed & cardPlayed <= cardsLeft(currentPlayer)) {
                    if (cardPlayed == 0) {
                        if(!zeroFirstTime) {
                            playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
                            zeroFirstTime = true;
                        } else {
                            nextPlayer();
                            zeroFirstTime = false;
                        }

                    } else {
                        zeroFirstTime = false;
                        nextCard = playerHand[currentPlayer][cardPlayed - 1];
                        if (currentCard.isPlayable(nextCard)) {
                            currentCard = nextCard;
                            removeCardFromHand(currentPlayer, cardPlayed - 1);
                            if (currentCard.isSkip()) {
                                handleSkip();
                            } else if (currentCard.isDraw2()) {
                                handleDraw2();
                            } else if (currentCard.isReverse()) {
                                handleReverse();
                            } else if (currentCard.isWild()) {
                                handleWild(s);
                            } else if (currentCard.isWildDraw4()) {
                                handleWildDraw4(s);
                            }
                            if (!currentCard.isSkip() & !currentCard.isReverse() & !currentCard.isDraw2()) {
                                System.out.println("Played " + currentCard.color + " " + currentCard.card);
                            }
                            if (cardsLeft(currentPlayer) != 0 & currentCard.card != CARD.SKIP & currentCard.card != CARD.DRAW_2
                                    & currentCard.card != CARD.REVERSE & currentCard.card != CARD.WILD & currentCard.card != CARD.WILD_DRAW4) {
                                nextPlayer();
                            }
                        } else {
                            System.out.println("INVALID CHOICE!");
                            System.out.println("Current Card:\n" + currentCard.color + " " + currentCard.card);
                        }
                    }
                }else {
                    System.out.println("INVALID CHOICE!");
                }
                }while (!currentCard.isPlayable(nextCard)) ;

            //count += 1;
        }
        winner = currentPlayer;
        System.out.println("Player " + (currentPlayer+1) + " wins!");
    }
    //errors - nextcard/ scanner/ first card thing.

}
