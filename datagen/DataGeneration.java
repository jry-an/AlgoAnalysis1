package datagen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates collection of integers from sampling a uniform distribution.
 *
 * @author jkcchan
 */
public class DataGeneration
{
    /** Program name. */
    protected static final String progName = "DataGenerator";

    /** size of integer range to generate values from. */
    protected int range;

    static final int small = 10;
   static  final int medium = ((int) Math.pow(10,3));
   static final int large = ((int) Math.pow(10,5));

    private DataGeneration()  {
    }



    private void small() throws IOException {
        File file = new File("datagen/small.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write("0 \n");
        // print out samples
        for (int i = 0; i < small; i+=2) {
            br.write(i + " ");
            br.write((i+1) + " ");
            br.write((i+2) + " ");
            br.write("\n");

        }
        br.close();
        fr.close();
    } // end of sampleWithReplacement()

    private void medium() throws IOException {
        File file = new File("datagen/medium.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write("0 \n");
        // print out samples
        for (int i = 0; i < medium; i+=2) {
            br.write(i + " ");
            br.write((i+1) + " ");
            br.write((i+2) + " ");
            br.write("\n");

        }
        br.close();
        fr.close();
    } // end of sampleWithReplacement()

    private void large() throws IOException {
        File file = new File("datagen/large.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        
        br.write("0 \n");
        // print out samples
        for (int i = 0; i < large; i+=2) {
            br.write(i + " ");
            br.write((i+1) + " ");
            br.write((i+2) + " ");
            br.write("\n");

        }
        br.close();
        fr.close();
    } // end of sampleWithReplacement()



    /**
     * Error message.
     */
    private static void usage() {
        System.err.println("invalid input");
        System.exit(1);
    } // end of usage()


    /**
     * Main method.
     */
    public static void main(String[] args) {
        try {
            // sample size
            String samplingType = args[0];

            DataGeneration gen = new DataGeneration();

            switch (samplingType) {
                // sampling with replacement
                case "small":
                    gen.small();
                    break;
                // sampling without replacement
                case "medium":
                    gen.medium();
                    break;
                case "large":
                    gen.large();
                    break;
                default:
                    System.err.println(samplingType + " is an unknown sampling type.");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            usage();
        }

    } // end of main()
} // end of class DataGenerator
