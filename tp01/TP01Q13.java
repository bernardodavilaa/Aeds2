public class TP01Q13{
    public static String cezar(String str){
        String resposta="";
        for(int i=0; i<str.length();i++){
            resposta += (char)(str.charAt(i) + 3);
        }
        return resposta;
    }
    public static void main(String[] args){
        String str1="";
        str1=MyIO.readLine();
        while(!(str1.equals("FIM"))){
            MyIO.println(cezar(str1));
            str1=MyIO.readLine();
        }
    }
}