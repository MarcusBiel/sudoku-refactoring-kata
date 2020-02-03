package com.marcusbiel.sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
SudokuSolver Demo Code, written in 2005 (Java 5)
by Patrick Chan. With permission, adjusted in 2019/20 by Marcus Biel,
for refactoring trainings.
*/
public class SudokuSolver {

    public static void main(String[] args) throws Exception
{
        new SudokuSolver().go(args[0]);
    }


    public void go(String s) throws Exception {
			FileReader rd = new FileReader(s);

				   Grid grid = Grid.create(rd);

            // Find a solution
       ArrayList<Grid> solutions = new ArrayList<Grid>();
            solve(grid, solutions);

            // Print the grid with the givens
             System.out.println("Original");
              System.out.println(grid);

            // Print the solution
             if (solutions.size() == 0)
             {
                 System.out.println("Unsolveable");
            } else if (solutions.size() == 1)
            {
                 System.out.println("Solved");
            } else
                {
                 System.out.println("At least two solutions");
            }

            // Print the solution(s)
            for (int i = 0; i < solutions.size(); i++) {
                System.out.println(solutions.get(i));
            }
             System.out.println();
            System.out.println();
    }

    // Recursive routine that implements the bifurcation algorithm
    private static void solve(Grid grid, List<Grid> solutions) {
        // Return if there is already a solution
        if (solutions.size() >= 2)
                return;

        // Find first empty cell
         int loc = grid.findEmptyCell();

        // If no empty cells are found, a solution is found
         if (loc < 0) {
            solutions.add(grid.clone());
            return;
        }

        // Try each of the 9 digits in this empty cell
         for (int n=1; n<10; n++) {
            if (grid.set(loc, n)) {
                // With this cell set, work on the next cell
                 solve(grid, solutions);

                // Clear the cell so that it can be filled with another digit
                 grid.clear(loc);
            }
        }
    }

}