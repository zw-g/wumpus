import java.util.Arrays;
import java.util.List;

public class Map {

  //map
  private static final String[][] map = new String[5][5];
  private static int goldNum = 0;
  private static int wunmpNum = 0;

  //map input
  int[][] pit = {{0, 2}, {3, 0}, {3, 4}, {4, 1}};
  int[][] gold = {{3, 3}};
  int[][] wumpus = {{3, 2}};

  //map input 2
//  int[][] pit = {{0, 2}, {1, 3}, {3, 0}, {3, 4}, {4, 1}};
//  int[][] gold = {{4, 2}};
//  int[][] wumpus = {{2, 1}};

  Map() {
    generateMap();
  }

  public static void removeGold(int x, int y) {
    map[x][y] = map[x][y].replace("G", "");
    goldNum -= 1;
  }

  public static void removeWumpus(int x, int y) {
    map[x][y] = map[x][y].replace("W", "");
    if (x > 0 && map[x - 1][y].contains("S")) {
      map[x - 1][y] = map[x - 1][y].replace("S", "");
    }
    if (x < map.length - 1 && map[x + 1][y].contains("S")) {
      map[x + 1][y] = map[x + 1][y].replace("S", "");
    }
    if (y < map[0].length - 1 && map[x][y + 1].contains("S")) {
      map[x][y + 1] = map[x][y + 1].replace("S", "");
    }
    if (y > 0 && map[x][y - 1].contains("S")) {
      map[x][y - 1] = map[x][y - 1].replace("S", "");
    }

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j].contains("W")) {
          if (i > 0 && !map[i - 1][j].contains("S")) {
            map[i - 1][j] = map[i - 1][j] + "S";
          }
          if (i < map.length - 1 && !map[i + 1][j].contains("S")) {
            map[i + 1][j] = map[i + 1][j] + "S";
          }
          if (j < map[0].length - 1 && !map[i][j + 1].contains("S")) {
            map[i][j + 1] = map[i][j + 1] + "S";
          }
          if (j > 0 && !map[i][j - 1].contains("S")) {
            map[i][j - 1] = map[i][j - 1] + "S";
          }
        }
      }
    }
    wunmpNum -= 1;
  }

  public static void printMap(Hunter h) {
    //vic code here
    StringBuilder out = new StringBuilder();
    for (int x = 0; x < map.length; x++) {
      out.append(" ----");
    }
    out.append(" \n");
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        out.append("|");
        if (isInList(h.getPastRoute(), new int[]{i, j})) {
          if (h.position[0] == i && h.position[1] == j) {
            if (map[i][j].length() == 0) {
              out.append(" ").append(h.getFacingDirection()).append("  ");
            } else if (map[i][j].length() == 1) {
              out.append(" ").append(h.getFacingDirection()).append(map[i][j]).append(" ");
            } else if (map[i][j].length() == 2) {
              out.append(h.getFacingDirection()).append(map[i][j]).append(" ");
            } else if (map[i][j].length() == 3) {
              out.append(h.getFacingDirection()).append(map[i][j]);
            }
          } else {
            if (map[i][j].length() == 0) {
              out.append("    ");
            } else if (map[i][j].length() == 1) {
              out.append(" ").append(map[i][j]).append("  ");
            } else if (map[i][j].length() == 2) {
              out.append(" ").append(map[i][j]).append(" ");
            } else if (map[i][j].length() == 3) {
              out.append(map[i][j]).append(" ");
            }
          }
        } else {
          out.append("    ");
        }
      }
      out.append("|\n");
      if (i + 1 != map.length) {
        for (int x = 0; x < map.length; x++) {
          out.append(" ----");
        }
        out.append(" \n");
      }
    }
    for (int x = 0; x < map.length; x++) {
      out.append(" ----");
    }
    out.append(" ");
    System.out.println(out);
  }

  public static void printMap(AiHunter h) {
    //vic code here
    StringBuilder out = new StringBuilder();
    for (int x = 0; x < map.length; x++) {
      out.append(" ----");
    }
    out.append(" \n");
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        out.append("|");
        if (isInList(h.getPastRoute(), new int[]{i, j})) {
          if (h.position[0] == i && h.position[1] == j) {
            if (map[i][j].length() == 0) {
              out.append(" ").append(h.getFacingDirection()).append("  ");
            } else if (map[i][j].length() == 1) {
              out.append(" ").append(h.getFacingDirection()).append(map[i][j]).append(" ");
            } else if (map[i][j].length() == 2) {
              out.append(h.getFacingDirection()).append(map[i][j]).append(" ");
            } else if (map[i][j].length() == 3) {
              out.append(h.getFacingDirection()).append(map[i][j]);
            }
          } else {
            if (map[i][j].length() == 0) {
              out.append("    ");
            } else if (map[i][j].length() == 1) {
              out.append(" ").append(map[i][j]).append("  ");
            } else if (map[i][j].length() == 2) {
              out.append(" ").append(map[i][j]).append(" ");
            } else if (map[i][j].length() == 3) {
              out.append(map[i][j]).append(" ");
            }
          }
        } else {
          out.append("    ");
        }
      }
      out.append("|\n");
      if (i + 1 != map.length) {
        for (int x = 0; x < map.length; x++) {
          out.append(" ----");
        }
        out.append(" \n");
      }
    }
    for (int x = 0; x < map.length; x++) {
      out.append(" ----");
    }
    out.append(" ");
    System.out.println(out);
  }

  //to see if arraylist has the array
  public static boolean isInList(final List<int[]> list, final int[] candidate) {
    return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
  }

  public int getWunmpNum() {
    return wunmpNum;
  }

  public int getGoldNum() {
    return goldNum;
  }

  public void generateMap() {
    //JiaNing code here
    //by default, every grid in the map is now "null"
    //make every grid in the map empty " "
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        map[i][j] = "";
      }
    }

    //input pit
    for (int[] p : pit) {
      map[p[0]][p[1]] += "P";
    }

    //input gold
    for (int[] g : gold) {
      map[g[0]][g[1]] += "G";
      goldNum++;
    }

    //input wumpus
    for (int[] w : wumpus) {
      map[w[0]][w[1]] += "W";
      wunmpNum++;
    }

    //put in breeze and stench to grids surrounding pits and wumpus
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {

        //format: map[1][4] = "bgs"
        if (map[i][j].contains("W")) {
          if (i > 0 && !map[i - 1][j].contains("S")) {
            map[i - 1][j] = map[i - 1][j] + "S";
          }
          if (i < map.length - 1 && !map[i + 1][j].contains("S")) {
            map[i + 1][j] = map[i + 1][j] + "S";
          }
          if (j < map[0].length - 1 && !map[i][j + 1].contains("S")) {
            map[i][j + 1] = map[i][j + 1] + "S";
          }
          if (j > 0 && !map[i][j - 1].contains("S")) {
            map[i][j - 1] = map[i][j - 1] + "S";
          }
        }

        if (map[i][j].contains("P")) {
          if (i > 0 && !map[i - 1][j].contains("B")) {
            map[i - 1][j] = map[i - 1][j] + "B";
          }
          if (i < map.length - 1 && !map[i + 1][j].contains("B")) {
            map[i + 1][j] = map[i + 1][j] + "B";
          }
          if (j < map[0].length - 1 && !map[i][j + 1].contains("B")) {
            map[i][j + 1] = map[i][j + 1] + "B";
          }
          if (j > 0 && !map[i][j - 1].contains("B")) {
            map[i][j - 1] = map[i][j - 1] + "B";
          }
        }
      }
    }
  }

  public String getMap(int[] location) {
    return map[location[0]][location[1]];
  }

  public String[][] getMap() {
    return map;
  }
}
