import java.util.Random;

/**
 * Created by benjamin on 6/29/2016.
 */

public class Model {
    private Integer[][] grid;
    private int dimensions;

    public Model(int dimension) {
        this.grid = new Integer[dimension][dimension];
        this.dimensions = dimension;
        gridfiller(dimension);
        display();
}

    public int getDimensions() {
        return this.dimensions;
    }

    public int getData(int row, int column){
        return grid[row][column];
    }


    private void gridfiller(int dimension) {
        int count = 0;
        Random generator = new Random(System.currentTimeMillis());
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                if (generator.nextInt(100) < 50) {
                    this.grid[r][c] = 9;
                } else {
                    this.grid[r][c] = 0;
                }
            }
        }
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                if (this.grid[r][c] != 9) {
                    count = 0;
                    if (r == 0 && c == 0) {
                        if (this.grid[r + 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c + 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else if (r == 0 && c == dimension - 1) {
                        if (this.grid[r + 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c - 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else if (r == dimension - 1 && c == 0) {
                        if (this.grid[r - 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c + 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else if (r == dimension - 1 && c == dimension - 1) {
                        if (this.grid[r - 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c - 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else if (r == dimension - 1) {
                        if (this.grid[r - 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c - 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else if (r == 0) {
                        if (this.grid[r + 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c - 1] == 9) {
                            count += 1;
                        }

                        this.grid[r][c] = count;
                    } else if (c == 0) {
                        if (this.grid[r + 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c + 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else if (c == dimension - 1) {
                        if (this.grid[r + 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c - 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    } else {
                        if (this.grid[r + 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c - 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r - 1][c + 1] == 9) {
                            count += 1;
                        }
                        if (this.grid[r + 1][c + 1] == 9) {
                            count += 1;
                        }
                        this.grid[r][c] = count;
                    }

                }
            }
        }
    }


    public String display() {
        String display = "";
        display += "  ";
        for (int i = 0; i < dimensions; i++) {
            if (i >= 10) {
                int temp = i % 10;
                display += temp + " ";
            } else {
                display += i + " ";
            }
        }
        display += "\n";
        display += "  ";
        for (int c = 0; c < dimensions * 2 - 1; c++) {
            display += '-';
        }
        for (int row = 0; row < dimensions; row++) {
            display += "\n";
            if (row >= 10) {
                int temp = row % 10;
                display += temp + "";
                display += '|';
            } else {
                display += row + "";
                display += '|';
            }
            for (int column = 0; column < dimensions; column++) {
                display += this.grid[row][column] + " ";
            }
        }
        return display;
    }
}
