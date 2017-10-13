package DessertShop;

import java.util.ArrayList;
import java.util.List;

public class Extra {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0){
            return result;
        }

        int leftBd = 0;
        int upBd = 0;
        int rightBd = matrix[0].length - 1;
        int bottomBd = matrix.length - 1;

        while(true){
            for (int i = leftBd; i <= rightBd ; i++){
                result.add(matrix[upBd][i]);
            }
            upBd++;
            if (leftBd > rightBd || upBd > bottomBd) break;

            for (int i = upBd; i <= bottomBd; i++){
                result.add(matrix[i][rightBd]);
            }
            rightBd--;
            if (leftBd > rightBd || upBd > bottomBd) break;

            for (int i = rightBd; i >= leftBd; i--){
                result.add(matrix[bottomBd][i]);
            }
            bottomBd--;
            if (leftBd > rightBd || upBd > bottomBd) break;

            for (int i = bottomBd; i >= upBd; i--){
                result.add(matrix[i][leftBd]);
            }
            leftBd++;
            if (leftBd > rightBd || upBd > bottomBd) break;
        }
        return result;
    }

}
