import java.util.*;
import java.io.*;
import java.math.*;

public class main {

    public static void main(String[] args){

        Scanner scn=new Scanner(System.in);
        String s=scn.nextLine();
        Stack<String> st=new Stack<>();

        for(int i=0;i<s.length();i++){
            String str=Character.toString(s.charAt(i));

            if(str=="(" || str=="{" || str=="["){
                st.push(str);
            }
        }
    }
}
