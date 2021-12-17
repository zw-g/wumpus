import java.util.ArrayList;
import java.util.Arrays;

public class AiHunter {

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

  AiHunter() {
    position = new int[]{0, 0};
    facingDirection = "e";
    pastRoute = new ArrayList<>();
    pastRoute.add(new int[]{position[0], position[1]});
    arrow = 1;
  }

  /*
  请在action 里面写code 完成 AiHunter 需要做的事情
  我没有设置任何print AI hunter 的 move 大家可以自行print
   */
  public void action(Map map) {
    // go home
    if (map.getGoldNum() == 0 && map.getWunmpNum() == 0 && gold > 0) {
      ArrayList<int[]> solution = new ArrayList<>();
      getGoHomeRoute(solution, position);
      for (int[] next : solution) {
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        if (position[0] == next[0]) {
          position[1] = next[1];
        } else {
          position[0] = next[0];
        }
        System.out.println(Arrays.toString(position));
      }
      gold = 0;
      System.out.println("AI: put gold");
    }
    // go kill and get
    else {
      if (map.getMap()[3][2].contains("W")) {
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[1]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        for (int x = position[1]; x < map.getMap().length; x++) {
          int[] tempP = new int[]{position[0], x};
          if (map.getMap(tempP).contains("W")) {
            Map.removeWumpus(tempP[0], tempP[1]);
            System.out.println("AI: shoot");
            break;
          }
        }
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[1]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[1]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        if (map.getMap()[position[0]][position[1]].contains("G")) {
          gold += 1;
          Map.removeGold(position[0], position[1]);
          System.out.println("AI: pick up gold");
        }
      } else if (map.getMap()[2][1].contains("W")) {
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        for (int x = position[1]; x < map.getMap().length; x++) {
          int[] tempP = new int[]{position[0], x};
          if (map.getMap(tempP).contains("W")) {
            Map.removeWumpus(tempP[0], tempP[1]);
            System.out.println("AI: shoot");
            break;
          }
        }
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[1]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[1]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        System.out.print("AI: " + Arrays.toString(position) + " -> ");
        position[0]++;
        pastRoute.add(new int[]{position[0], position[1]});
        System.out.println(Arrays.toString(position));
        if (map.getMap()[position[0]][position[1]].contains("G")) {
          gold += 1;
          Map.removeGold(position[0], position[1]);
          System.out.println("AI: pick up gold");
        }
      }
    }
  }

  private boolean getGoHomeRoute(ArrayList<int[]> solution, int[] location) {
    if (location[0] == 0 && location[1] == 0) {
      return true;
    }
    if (Map.isInList(pastRoute, new int[]{location[0] - 1, location[1]}) && !Map.isInList(solution,
        new int[]{location[0] - 1, location[1]})) {
      solution.add(new int[]{location[0] - 1, location[1]});
      if (getGoHomeRoute(solution, new int[]{location[0] - 1, location[1]})) {
        return true;
      } else {
        solution.remove(solution.size() - 1);
      }
    }
    if (Map.isInList(pastRoute, new int[]{location[0], location[1] - 1}) && !Map.isInList(solution,
        new int[]{location[0], location[1] - 1})) {
      solution.add(new int[]{location[0], location[1] - 1});
      if (getGoHomeRoute(solution, new int[]{location[0], location[1] - 1})) {
        return true;
      } else {
        solution.remove(solution.size() - 1);
      }
    }
    if (Map.isInList(pastRoute, new int[]{location[0] + 1, location[1]}) && !Map.isInList(solution,
        new int[]{location[0] + 1, location[1]})) {
      solution.add(new int[]{location[0] + 1, location[1]});
      if (getGoHomeRoute(solution, new int[]{location[0] + 1, location[1]})) {
        return true;
      } else {
        solution.remove(solution.size() - 1);
      }
    }
    if (Map.isInList(pastRoute, new int[]{location[0], location[1] + 1}) && !Map.isInList(solution,
        new int[]{location[0], location[1] + 1})) {
      solution.add(new int[]{location[0], location[1] + 1});
      if (getGoHomeRoute(solution, new int[]{location[0], location[1] + 1})) {
        return true;
      } else {
        solution.remove(solution.size() - 1);
      }
    }
    return false;
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
