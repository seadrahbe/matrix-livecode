import java.util.ArrayList;
import java.util.List;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     * @throws IllegalArgumentException if the enclosure does not contain a salamander
     */
    public static boolean canReach(char[][] enclosure) {
        int[] start = salamanderLocation(enclosure);
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(enclosure, visited, start);
    }

    public static boolean canReach(char[][] enclosure, boolean[][] visited, int[] current) {
        int curR = current[0];
        int curC = current[1];
        if(visited[curR][curC]) return false;
        if(enclosure[curR][curC] == 'f') return true;

        visited[curR][curC] = true;

        List<int[]> neighbors = possibleMoves(enclosure, current);
        for(int[] neighbor : neighbors) {
            if(canReach(enclosure, visited, neighbor)) {
                return true;
            }
        }

        return false;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] current) {
        int curR = current[0];
        int curC = current[1];

        int newR, newC;

        List<int[]> possible = new ArrayList<>();

        // up
        newR = curR - 1;
        newC = curC;
        if(newR >= 0 && enclosure[newR][newC] != 'W') {
            int[] newMove = {newR, newC};
            possible.add(newMove);
        } 

        // down
        newR = curR + 1;
        newC = curC;
        if(newR < enclosure.length && enclosure[newR][newC] != 'W') {
            possible.add(new int[]{newR, newC});
        } 

        // left
        newR = curR;
        newC = curC - 1;
        if(newC >= 0 && enclosure[newR][newC] != 'W') {
            possible.add(new int[]{newR, newC});
        } 

        // right
        newR = curR;
        newC = curC + 1;
        if(newC < enclosure[newR].length && enclosure[newR][newC] != 'W') {
            possible.add(new int[]{newR, newC});
        } 

        return possible;
    }

    public static int[] salamanderLocation(char[][] enclosure) {
        for (int row = 0; row < enclosure.length; row++) {
            for(int column = 0; column < enclosure[row].length; column++) {
                if('s' == enclosure[row][column]) {
                    return new int[] {row, column};
                }
            }
        }

        throw new IllegalArgumentException("No salamander present");
    }
}
