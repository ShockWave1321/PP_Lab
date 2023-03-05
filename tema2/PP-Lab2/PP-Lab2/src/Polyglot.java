import org.graalvm.polyglot.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Polyglot {
    private static String RToUpper(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("R", "toupper(\"" + token + "\");");
        String resultString = result.asString();
        polyglot.close();

        return resultString;
    }

    //metoda privata pentru evaluarea unei sume de control simple a literelor unui text ASCII, folosind PYTHON
    private static int SumCRC(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python", "\n"+
                "sum((ord(ch)*ord(ch) + ord(ch) + 5)%256 for ch in '" + token.substring(1,token.length()-1) + "')");
        int resultInt = result.asInt();
        polyglot.close();

        return resultInt;
    }
    private static int[] Random20()
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python","\n"+
                "from random import randint\n" +
                "[randint(-10,10) for i in range(20)]");
        int[] rez = new int[20];
        for(int i = 0; i<20; ++i)
        {
            rez[i] = result.getArrayElement(i).asInt();
        }
        polyglot.close();
        return rez;
    }
    private static void afisareJS(int[] list)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        String lista = Arrays.toString(list);
        polyglot.eval("js","console.log(" + lista + ");");
        polyglot.close();
    }
    private static double medie20_80(int[] list)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        String m = Arrays.toString(list);
        m = m.substring(1,m.length()-1);
        Value rez = polyglot.eval("R", "\n"+
                "v <- c(" + m +")\n"+
                "v <- sort(v)\n"+
                "mean(v,0.2)"
        );
        return rez.asDouble();
    }

    public static void main(String[] args) throws IOException {
        Context polyglot = Context.create();
        //construim un array de string-uri, folosind cuvinte din pagina web:  https://chrisseaton.com/truffleruby/tenthings/
        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\"]");//,\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\",\"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Suma de control cuvinte: ");
        String x = r.readLine();
        int val = Integer.parseInt(x);
        int ok = 0;
        for (int i = 0; i < array.getArraySize(); i++)
        {
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            if(crc == val)
            {
                ok = 1;
                System.out.println(upper + " -> " + crc);
            }
        }
        if(ok == 0)
        {
            System.out.printf("Nu sunt cuvinte cu suma de control %d\n", val);
        }

        int[] list = Random20();
        afisareJS(list);
        System.out.print(medie20_80(list));

        polyglot.close();
    }
}

