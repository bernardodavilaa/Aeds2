import java.util.Random;

public class TP01Q04 {
    
    public static void main(String[] args){
        Random gerador= new Random();
        gerador.setSeed(4);
        String entrada="";
        char a, b;
        entrada=MyIO.readLine();
        while(!entrada.equals("FIM")){
            char [] charVet= new char[entrada.length()+1];
            charVet[entrada.length()]= '\0';
            for(int i=0; i<entrada.length();i++){
                charVet[i]= entrada.charAt(i);  
            }
            a=(char) ('a' +Math.abs(gerador.nextInt()));
            b=(char) ('a' +Math.abs(gerador.nextInt()));
            for(int i=0; i<entrada.length(); i++){
            if(charVet[i] == a){
                charVet[i]= b;
            }
        }
                System.out.println(charVet);
            
            entrada=MyIO.readLine();
        }
    }
}
