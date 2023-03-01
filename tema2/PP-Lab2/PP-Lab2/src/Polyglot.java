//import libraria principala polyglot din graalvm
import org.graalvm.polyglot.*;

import java.util.ArrayList;
import java.util.Arrays;

//clasa principala - aplicatie JAVA
class Polyglot {
    //metoda privata pentru conversie low-case -> up-case folosind functia toupper() din R
    private static String RToUpper(String token){
        //construim un context care ne permite sa folosim elemente din R
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //folosim o variabila generica care va captura rezultatul excutiei funcitiei R, toupper(String)
        //pentru aexecuta instructiunea I din limbajul X, folosim functia graalvm polyglot.eval("X", "I");
        Value result = polyglot.eval("R", "toupper(\""+token+"\");");
        //utilizam metoda asString() din variabila incarcata cu output-ul executiei pentru a mapa valoarea generica la un String
        String resultString = result.asString();
        // inchidem contextul Polyglot
        polyglot.close();

        return resultString;
    }

    //metoda privata pentru evaluarea unei sume de control simple a literelor unui text ASCII, folosind PYTHON
    private static int SumCRC(String token){
        //construim un context care ne permite sa folosim elemente din PYTHON
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //folosim o variabila generica care va captura rezultatul excutiei functiei PYTHON, sum()
        //avem voie sa inlocuim anumite elemente din scriptul pe care il construim spre evaluare, aici token provine din JAVA, dar va fi interpretat de PYTHON
        Value result = polyglot.eval("python", "sum((ord(ch)*ord(ch) + ord(ch) + 5)%256 for ch in '" + token + "')");
        //utilizam metoda asInt() din variabila incarcata cu output-ul executiei, pentru a mapa valoarea generica la un Int
        int resultInt = result.asInt();
        // inchidem contextul Polyglot
        polyglot.close();

        return resultInt;
    }
    private static int[] Random20()
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python",
        "from random import randint\n" +
                "[randint(-100,100) for i in range(20)]");
        int[] rez = result.as(int[].class);
        polyglot.close();
        return rez;
    }
    private static void afisareJS(int[] list)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        String lista = Arrays.toString(list);
        polyglot.eval("js",
                "for(let i = 0; i < " + list.length + "; ++i)" +
                        "{" +
                        "   console.log(" + lista + "[i]);" +
                        "}");
        polyglot.close();
    }
    private static double medie20_80(int[] list)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        String m = Arrays.toString(list);
        m = m.substring(1,m.length()-1);
        Value rez = polyglot.eval("R",
                "v <- c(" + m +")\n"+
                        "v <- sort(v)\n"+
                        "mean(v,0.2)"
        );
        return rez.asDouble();
    }

    //functia MAIN
    public static void main(String[] args) {
        //construim un context pentru evaluare elemente JS
        Context polyglot = Context.create();
        //construim un array de string-uri, folosind cuvinte din pagina web:  https://chrisseaton.com/truffleruby/tenthings/
        //Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\"]");//,\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\",\"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");
        //pentru fiecare cuvant, convertim la upcase folosind R si calculam suma de control folosind PYTHON
        /*for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            System.out.println(upper + " -> " + crc);
        }*/
        int[] list = Random20();
        for(int i = 0; i<list.length; ++i)
        {
            System.out.print(list[i] + " ");
        }
        System.out.println();
        //afisareJS(list);
        System.out.print(medie20_80(list));

        // inchidem contextul Polyglot
        polyglot.close();
    }
}

