import org.graalvm.polyglot.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

class Polyglot {

    private static void reg_lin(String s,String name,String path,String color) throws IOException
    {
        File file = new File(s);
        BufferedReader r = new BufferedReader(new FileReader(file));
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value data = polyglot.eval("R","library(lattice)\n" +
                "x <-  c(" + r.readLine() + ")\n" +
                "y <-  c(" + r.readLine() + ")\n" +
                "model <- lm(y~x)\n" +
                "png(file = '" + path + name + ".png')\n" +
                "plot(x,y,col = '" + color + "')\n" +
                "abline(model)\n" +
                "dev.off()");
        polyglot.close();
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        FileWriter file = new FileWriter("/home/student/Laboratoare/Lab2/Ex2/dataset.txt");
        String s;

        System.out.print("Axa x:");
        s = r.readLine();
        file.write(s);
        file.write('\n');

        System.out.print("Axa y:");
        s = r.readLine();
        file.write(s);
        file.close();

        String numef = "Problema_dificila";
        String cale = "/home/student/Laboratoare/Lab2/Ex2/";
        String culoare = "blue";

        reg_lin("/home/student/Laboratoare/Lab2/Ex2/dataset.txt", numef, cale, culoare);
    }
}

