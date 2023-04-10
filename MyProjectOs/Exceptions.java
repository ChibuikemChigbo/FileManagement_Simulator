import java.util.*;
import java.io.*;
public class Exceptions{
    public static void main(String[] rk){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        int count=3;
        IllegalArgumenentException e =  new IllegalArgumentException();
        ArithmethicException d= new ArithmeticException();
        while(true){
            
        try{
            if(count<3){
                throw e;
            }
            if(a>99){
                throw d;
            }
        
            
        }
        catch(IllegalArgumentException | ArithmeticException r){
            System.out.println("Illegal Argunemt or Number Format");
        }
    }
}