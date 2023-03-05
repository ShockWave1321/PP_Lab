import org.graalvm.polyglot.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

class Polyglot {

    private static int[] readPY()
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value data = polyglot.eval("python","\n" +
                "n = int(input('Numar de aruncari (n >= 1): '))\n" +
                "while n < 1:\n" +
                "   n = int(input('Reintroduceti n: '))\n" +
                "x = int(input('Numar maxim de pajura (1 <= x <= n): '))\n" +
                "while x > n or x < 1:\n" +
                "   x = int(input('Reintroduceti x: '))\n" +
                "[n,x]");
        int []numbers = new int[2];
        numbers[0] = data.getArrayElement(0).asInt();
        numbers[1] = data.getArrayElement(1).asInt();
        polyglot.close();
        return numbers;
    }
    private static void BinDist(int n,int x)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value data = polyglot.eval("R","\n" +
                "pbinom("+ x +", "+ n +", 0.5)"
        );
        System.out.println(data.asDouble());
        polyglot.close();
    }

    public static void main(String[] args) throws IOException
    {
        int []data = readPY();
        int n,x;
        n = data[0];
        x = data[1];

        BinDist(n,x);
    }
}

