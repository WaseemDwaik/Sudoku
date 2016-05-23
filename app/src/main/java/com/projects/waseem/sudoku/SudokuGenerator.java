package com.projects.waseem.sudoku;

import java.util.*;
/**
 * Created by Waseem on 5/22/2016.
 */
public class SudokuGenerator
{
    private static final int BOARD_WIDTH = 9;
    private static final int BOARD_HEIGHT = 9;
    private static final int DEF=40;
    private int[][] board;

    public SudokuGenerator() {
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
    }
    public int[][] nextBoard()
    {
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        backTrackSudoku(0, 0);
        deleteNums(DEF);
        return board;

    }
    public boolean backTrackSudoku(int x, int y)
    {
        int x2 = x;
        int y2 = y;
        int[] nums = {1,2,3,4,5,6,7,8,9};
        Random r = new Random();
        int tmp = 0;
        int current = 0;
        int top = nums.length;

        for(int i=top-1;i>0;i--)
        {
            current = r.nextInt(i);
            tmp = nums[current];
            nums[current] = nums[i];
            nums[i] = tmp;
        }

        for(int i=0;i<nums.length;i++)
        {
            if(checkAns(x, y, nums[i]))
            {
                board[x][y] = nums[i];
                if(x == 8)
                {
                    if(y == 8)
                        return true;
                    else
                    {
                        x2 = 0;
                        y2 = y + 1;
                    }
                }
                else
                {
                    x2 = x + 1;
                }
                if(backTrackSudoku(x2, y2))
                    return true;
            }
        }
        board[x][y] = 0;
        return false;
    }

    private boolean checkAns(int x, int y, int current) {
        for(int i=0;i<9;i++) {
            if(current == board[x][i])
                return false;
        }
        for(int i=0;i<9;i++) {
            if(current == board[i][y])
                return false;
        }
        int x3 = 0;
        int y3 = 0;
        if(x > 2)
            if(x > 5)
                x3 = 6;
            else
                x3 = 3;
        if(y > 2)
            if(y > 5)
                y3 = 6;
            else
                y3 = 3;
        for(int i=x3;i<10 && i<x3+3;i++)
            for(int j=y3;j<10 && j<y3+3;j++)
                if(current == board[i][j])
                    return false;
        return true;
    }
    public void deleteNums(int holesToMake)
    {

        double totalNumberOfCells = 81;
        double toDelete = (double)holesToMake;

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                double randChance = toDelete/totalNumberOfCells;
                if(Math.random() <= randChance)
                {
                    board[i][j] = 0;
                    toDelete--;
                }
                totalNumberOfCells--;
            }
    }

    public void print(int[][] board)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
                System.out.print(board[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
    }

}