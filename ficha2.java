import java.util.Scanner;
/**
 * Write a description of class ficha2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ficha2
{
    private int[][] notasTurma;
    public int  min(int[] array){
       int min = array[0];
       for(int v:array){
        min=Math.min(v,min);
        }
        return min;
}

public int[] arrayEntre(int [] array, int i, int f){
     int[] res = new int[f-i+1];
     System.arraycopy(array,i,res,0,f-i+1);
     return res;
     
        
  }
  
  public int[] comuns(int[] array1,int[] array2){
  int[] res = new int[array1.length];
  int ind =0;
  for(int i=0;i<array1.length;i++){
     for(int j=0;j<array2.length;j++){
         if(array1[i] == array2[j]){
             res[ind] =array1[i];
             ind++;
            }
        }
    }
    return res;
 }

 
 public int soma(int[][] array,int n){
     int soma=0;
     for(int i=0;i<5;i++){
         for(int j=0;j<5;j++){
              if(j==n){
                soma+=array[i][j];
                }
            }
        }
        return soma;
    }
    
  public float media(int[][] array,int n){
     int soma=0;
     for(int i=0;i<5;i++){
         if(i==n){
            for(int j=0;j<5;j++){
                soma+=array[i][j];
            }
            }
        }
        return soma/5;
    }
    
   public float mediaUc(int[][] array,int n){
     int soma=0;
     for(int i=0;i<5;i++){
         for(int j=0;j<5;j++){
             if(n==j) soma+=array[i][j];
            }
        }
        return soma/5;
    }
    
    public int maior(int[][] array){
        int m=0;
        for(int i=0;i<5;i++){
         for(int j=0;j<5;j++){
             if(array[i][j]>m) m=array[i][j];
            }
        }
        return m;
    }
    
        public int menor(int[][] array){
        int m=maior(array);
        for(int i=0;i<5;i++){
         for(int j=0;j<5;j++){
             if(array[i][j]<m) m=array[i][j];
            }
        }
        return m;
    }

     public int[] mais(int[][] array,int N){
         int res[] = new int[array.length];
         int r=0;
         for(int i=0;i<5;i++){
             for(int j=0;j<5;j++){
                 if (array[i][j]>N){
                     res[r]=array[i][j];
                     r++;
                    }
                }
            }
            return res;
        }
       /* 
     public char[] converte(int[][] array){
         char res[] =new char[array.length];
             for(int i=0;i<5;i++){
             for(int j=0;j<5;j++){
                 res[l]=
                }
            }
     }
*/



}
