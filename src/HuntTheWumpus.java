import java.util.Scanner;

/**
 * <h1>Hunt The Wumpus AI</h1>
 * <p>
 * we have designed the AI for solving Hun The Wumpus game
 * </p>
 * <p>
 * this program and game interface run form the HuntTheWumpus.java main method.
 * </p>
 *
 * @author Zhaowei Gu, Zheqian Zhang, Jianning Lu, Haoran Su
 * @version 1.0
 * @since 2020-11-29
 */

public class HuntTheWumpus {

  static Map newMap;
  static Scanner userIn = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Welcome to Hunt the Wumpus game");
    System.out.println("0. Human Play");
    System.out.println("1. AI play");
    System.out.print("please select an option: ");
    selectAnOption();
  }

  public static void selectAnOption() {
    newMap = new Map();
    int input = userIn.nextInt();
    if (input == 0) {
      game();
      userIn.close();
    } else if (input == 1) {
      AiGame();
    } else {
      System.out.print("wrong input please try again: ");
      selectAnOption();
    }
  }

  public static void game() {
    Hunter victor = new Hunter();
    while (gameContinueChecker(victor)) {
      Map.printMap(victor);
      victor.action(uberInput(), newMap);
    }
    Map.printMap(victor);
    if (victor.getPosition()[0] == 0 && victor.getPosition()[1] == 0 && newMap.getGoldNum() == 0
        && victor.gold == 0 && newMap.getWunmpNum() == 0) {
      System.out.println("You Win!!! ᕕ( ᐛ )ᕗ");
      System.out.println("with wumpus killed ∠( ᐛ 」∠)＿");
    } else if (victor.getPosition()[0] == 0 && victor.getPosition()[1] == 0
        && newMap.getGoldNum() == 0 && victor.gold == 0) {
      System.out.println("You Win!!! ᕕ( ᐛ )ᕗ");
    } else {
      System.out.println("You're Dead (▰︶︹︺▰)");
    }
  }

  /*
  这里是 AI 去玩游戏那一部分
  不需要做任何修改
  需要去修改 AiHunter.java
   */
  public static void AiGame() {
    AiHunter terrier = new AiHunter();
    while (gameContinueChecker(terrier)) {
      terrier.action(newMap);
    }
    Map.printMap(terrier);
    if (terrier.getPosition()[0] == 0 && terrier.getPosition()[1] == 0 && newMap.getGoldNum() == 0
        && terrier.gold == 0 && newMap.getWunmpNum() == 0) {
      System.out.println("AI Win!!! ∠( ᐛ 」∠)＿");
      System.out.println("with wumpus killed ᕕ( ᐛ )ᕗ");
    } else if (terrier.getPosition()[0] == 0 && terrier.getPosition()[1] == 0
        && newMap.getGoldNum() == 0 && terrier.gold == 0) {
      System.out.println("AI Win!!! ∠( ᐛ 」∠)＿");
    } else {
      System.out.println("AI is Dead (っ- ‸ – ς)");
    }
  }

  //up, down, left, right, enter, shoot
  static String uberInput() {
    System.out.print("please enter next move: ");
    String input = userIn.nextLine();
    if (!input.equals("up") && !input.equals("down") && !input.equals("left") && !input.equals(
        "right") && !input.equals("enter") && !input.equals("shoot")) {
      return uberInput();
    } else {
      return input;
    }
  }

  public static boolean gameContinueChecker(Hunter player) {
    //helen code here
    if (newMap.getMap()[player.getPosition()[0]][player.getPosition()[1]].contains("W")
        || newMap.getMap()[player.getPosition()[0]][player.getPosition()[1]].contains("P")) {
      return false;
    } else {
      return player.getPosition()[0] != 0 || player.getPosition()[1] != 0
          || newMap.getGoldNum() != 0 || player.gold != 0;
    }
  }

  public static boolean gameContinueChecker(AiHunter player) {
    //helen code here
    if (newMap.getMap()[player.getPosition()[0]][player.getPosition()[1]].contains("W")
        || newMap.getMap()[player.getPosition()[0]][player.getPosition()[1]].contains("P")) {
      return false;
    } else {
      return player.getPosition()[0] != 0 || player.getPosition()[1] != 0
          || newMap.getGoldNum() != 0 || player.gold != 0;
    }
  }
}