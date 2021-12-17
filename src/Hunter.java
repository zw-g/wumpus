import java.util.ArrayList;

public class Hunter {

  //user position
  int[] position;

  //user facing direction
  String facingDirection;

  //arrow count
  int arrow;

  //gold count
  int gold = 0;

  //user past route
  ArrayList<int[]> pastRoute;

  Hunter() {
    position = new int[]{0, 0};
    facingDirection = "e";
    pastRoute = new ArrayList<>();
    pastRoute.add(new int[]{position[0], position[1]});
    arrow = 1;
  }

  public void action(String input, Map map) {
    switch (input) {
      case "up":
        if (facingDirection.equals("n") && position[0] > 0) {
          position[0] -= 1;
          //已经在走过的路线的话就不需要再加了
          if (!Map.isInList(pastRoute, new int[]{position[0], position[1]})) {
            pastRoute.add(new int[]{position[0], position[1]});
          }
        } else {
          facingDirection = "n";
        }
        break;
      case "left":
        if (facingDirection.equals("w") && position[1] > 0) {
          position[1] -= 1;
          if (!Map.isInList(pastRoute, new int[]{position[0], position[1]})) {
            pastRoute.add(new int[]{position[0], position[1]});
          }
        } else {
          facingDirection = "w";
        }
        break;
      case "right":
        if (facingDirection.equals("e") && map.getMap().length - 1 > position[1]) {
          position[1] += 1;
          if (!Map.isInList(pastRoute, new int[]{position[0], position[1]})) {
            pastRoute.add(new int[]{position[0], position[1]});
          }
        } else {
          facingDirection = "e";
        }
        break;
      case "down":
        if (facingDirection.equals("s") && map.getMap()[0].length - 1 > position[0]) {
          position[0] += 1;
          if (!Map.isInList(pastRoute, new int[]{position[0], position[1]})) {
            pastRoute.add(new int[]{position[0], position[1]});
          }
        } else {
          facingDirection = "s";
        }
        break;
      case "enter":
        //pick up gold
        if (map.getMap()[position[0]][position[1]].contains("G")) {
          gold += 1;
          Map.removeGold(position[0], position[1]);
        }
        //send gold home
        if (position[0] == 0 && position[1] == 0 && gold != 0) {
          gold = 0;
        }
        break;
      case "shoot":
        switch (facingDirection) {
          case "w":
            for (int x = position[1]; x >= 0; x--) {
              int[] tempP = new int[]{position[0], x};
              if (map.getMap(tempP).contains("W")) {
                Map.removeWumpus(tempP[0], tempP[1]);
                break;
              }
            }
            break;
          case "e":
            for (int x = position[1]; x < map.getMap().length; x++) {
              int[] tempP = new int[]{position[0], x};
              if (map.getMap(tempP).contains("W")) {
                Map.removeWumpus(tempP[0], tempP[1]);
                break;
              }
            }
            break;
          case "n":
            for (int x = position[0]; x >= 0; x--) {
              int[] tempP = new int[]{x, position[1]};
              if (map.getMap(tempP).contains("W")) {
                Map.removeWumpus(tempP[0], tempP[1]);
                break;
              }
            }
            break;
          case "s":
            for (int x = position[0]; x < map.getMap().length; x++) {
              int[] tempP = new int[]{x, position[1]};
              if (map.getMap(tempP).contains("W")) {
                Map.removeWumpus(tempP[0], tempP[1]);
                break;
              }
            }
            break;
        }
        break;
      default:
        break;
    }
  }

  public String getFacingDirection() {
    return facingDirection;
  }

  public int[] getPosition() {
    return position;
  }

  public ArrayList<int[]> getPastRoute() {
    return pastRoute;
  }
}
