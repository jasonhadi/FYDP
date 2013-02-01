public class Matrix {

 public static int[][] add1(int[][] inMatrix){
  
  for (int x = 0; x< 3;x++)
  {
   for (int y = 0; y < 3;y++)
   {
    inMatrix[x][y]++;
   }
  }
  
  return inMatrix;
  
 }
}