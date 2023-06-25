public class sudokuSolver {
    final static int mapSize = 9;
    public static void main(String[] args) {


        int[][] map = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}
        };

        if (solveMap(map)){
            System.out.println("Solved");
            printMap(map);
        }
        else{
            System.out.println("Invalid board");
        }

    }

    private static void printMap(int[][] map) {
        for (int row = 0; row < mapSize; row++){
            if(row % 3 == 0){
                System.out.println("---------");
            }
            for (int column = 0; column < mapSize; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(map[row][column]);
            }
            System.out.println();
        }
    }


    private static boolean checkRow(int number, int row, int[][] map){
        for (int i = 0; i < mapSize; i++){
            if (map[row][i] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean checkColumn(int number, int column, int map[][]){
        for (int i = 0; i < mapSize; i++){
            if (map[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean checkBox(int number, int[][] map, int row, int column){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++){
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if (map[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkPlacement(int number, int[][] map, int row, int column){

        return !checkRow(number, row, map) && !checkColumn(number, column, map) && !checkBox(number, map, row,column);
    }

    private static boolean solveMap(int[][] map){
        for (int row = 0; row < mapSize; row++){
            for (int column = 0; column < mapSize; column++){
                if (map[row][column] == 0){
                    for (int numberTry = 1; numberTry <= mapSize; numberTry++){
                        if(checkPlacement(numberTry, map, row, column)) {
                            map[row][column] = numberTry;

                            if(solveMap(map)){
                                return true;
                            }
                            else {
                                map[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}


