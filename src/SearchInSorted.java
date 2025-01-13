import java.util.Arrays;

public class SearchInSorted {


    public static void main(String[] args) {
        int[][] arr = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println(Arrays.toString(search(arr,4)));
    }
    //search in row provided between the col provided

    static int[] simpleBSearch(int[][] matrix,int target,int row,int colStart,int colEnd) {
        while (colStart<=colEnd) {
            int mid = colStart+ (colEnd-colStart)/2;
            if(matrix[row][mid]== target){
                return new int[] {row,mid};
            }
            if(matrix[row][mid] < target) {
                  colStart = mid +1;
            }  else {
                 colEnd = mid -1;
            }
        }
            return new int[] {-1,-1};
    }
    // search in the row and col
    static int[] search(int[][] matrix,int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if(rows ==1) {
            return simpleBSearch(matrix,target,0,0,cols-1);
        }

        int rStart = 0;
        int rEnd = rows -1;
        int midCol = cols/2;
        while (rStart< (rEnd-1)) {  //this is true if u have more than 2 rows
            int midRow = rStart + (rEnd-rStart)/2;
            if(matrix[midRow][midCol]== target) {
                return new int[]{midRow,midCol};
            }
            if(matrix[midRow][midCol]< target) {
                rStart = midRow;
            } else {
                rEnd = midRow;
            }
        }

         //now u have 2 rows, check if the target is in col of 2 rows
        if(matrix[rStart][midCol] == target) {
            return  new int[] {rStart,midCol};
        }
        if (matrix[rStart+1][midCol]== target) {
            return  new int[]{rStart+1,midCol};
        }

     //other search in 1st half
        if(target <= matrix[rStart][midCol-1]) {
        return simpleBSearch(matrix,target,rStart,0,midCol-1);
        }
        //2nd half
        if(target >= matrix[rStart][midCol+1] && target <= matrix[rStart][cols-1]) {
            return simpleBSearch(matrix,target,rStart,midCol+1,cols-1);
        }
        //3rd half
        if(target <= matrix[rStart+1][midCol-1]) {
            return simpleBSearch(matrix,target,rStart+1,0,midCol-1);
        }
        //4th half
        if(target >= matrix[rStart+1][midCol+1]) {
            return simpleBSearch(matrix,target,rStart+1,midCol+1,cols-1);
        }

        return new int[]{-1,-1};
    }
}
