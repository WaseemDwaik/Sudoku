package com.projects.waseem.sudoku;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Waseem on 5/22/2016.
 */
public class SudokuSolver {
    final int row=9;
    final int column=9;
    int[][] matrix=new int[row][column];
    Context c;
    public SudokuSolver(Context c){
        this.c=c;

    }

    boolean backTrackSudoku(int[][] matrix,int Startrow,int Startcolumn){
        Startrow=-1;
        Startcolumn=-1;
        loop:
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                if(matrix[x][y]==0){
                    Startrow=x;
                    Startcolumn=y;
                    break loop;
                }
            }
        }if(Startrow==-1&&Startcolumn==-1)
            return true;
        for(int num=1;num<=9;num++){
            if(checkAll(matrix,Startrow,Startcolumn,num)){
                matrix[Startrow][Startcolumn]=num;
                if(backTrackSudoku(matrix,Startrow,Startcolumn))
                    return true;
                matrix[Startrow][Startcolumn]=0;
            }
        }
        return false;
    }boolean checkColumn(int[][] matrix,int currentColumn,int num){
        for(int y=0;y<row;y++)
            if(matrix[y][currentColumn]==num){
                return false;
            }
        return true;
    }boolean checkRow(int[][] matrix,int currentRow,int num){
        for(int y=0;y<column;y++)
            if(matrix[currentRow][y]==num){
                return false;
            }
        return true;
    }boolean checkBox(int matrix[][], int boxRow, int boxCol, int num){
        for (int rw = 0; rw < 3; rw++)
            for (int col = 0; col < 3; col++)
                if (matrix[rw+boxRow][col+boxCol] == num){
                    return false;}
        return true;
    }boolean checkBoxAns(int matrix[][], int boxRow, int boxCol, int num){
        int count=0;
        for (int rw = 0; rw < 3; rw++)
            for (int col = 0; col < 3; col++)
                if (matrix[rw+boxRow][col+boxCol] == num){
                    count++;
                }
        return count <= 1;
    }boolean checkColumnAns(int[][] matrix,int currentColumn,int num){
        int count=0;
        for(int y=0;y<row;y++)
            if(matrix[y][currentColumn]==num){
                count++;
            }
        return count <= 1;    }boolean checkRowAns(int[][] matrix,int currentRow,int num){
        int count=0;
        for(int y=0;y<column;y++)
            if(matrix[currentRow][y]==num){
                count++;
            }
        return count <= 1;
    }
    boolean checkAll(int matrix[][],int currentRow,int currentCol,int num){
        return checkBox(matrix,currentRow-currentRow%3,currentCol-currentCol%3,num)&&checkColumn(matrix,currentCol,num)&&checkRow(matrix,currentRow,num);
    }boolean checkAllAns(int matrix[][],int currentRow,int currentCol,int num){
        return checkBoxAns(matrix,currentRow-currentRow%3,currentCol-currentCol%3,num)&&checkColumnAns(matrix,currentCol,num)&&checkRowAns(matrix,currentRow,num);
    }
    boolean checkAnswer(int[][] matrix){
        for(int x=0;x<row;x++)
            for(int y=0;y<column;y++){
                if(!checkAllAns(matrix,x,y,matrix[x][y])){
                    Toast.makeText(c, "Invalid Input At '"+(x+1)+","+(y+1) +"'", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        return true;
    }boolean isNumber(String x){
        try{
            Integer.parseInt(x);
        }catch(Exception ex){
            return false;

        }return true;
    }
}
