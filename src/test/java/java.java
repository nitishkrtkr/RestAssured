import java.util.Scanner;

public class java {


    public  static void main(String[] args){


        Scanner scn=new Scanner(System.in);
        int num=scn.nextInt();

                int sum=0;

                for(int i=1;i<=num/2;i++){

                    if(num%i==0){

                        sum=sum + i;

                    }
                }

                System.out.println(sum==num);

            }
        }

