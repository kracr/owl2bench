package ABoxGen.InstanceGenerator;
import java.io.File;

class Test extends Thread
{
    static int sum;
    public void run()
    {
        for(int i = 0; i < 10; i++)
        {
            System.out.println("running" + i);
            sum += i;
            System.out.println("Sum = " + Test.sum);
        }

    }
}
public class ThreadTest
{
    public static void main(String args[])
    {
        Test test[] = new Test[5];
        for(int i = 0; i < test.length; i++)
        {
            test[i] = new Test();
            test[i].start();
        }
        System.out.println("Sum1 = " + Test.sum);
    }
}