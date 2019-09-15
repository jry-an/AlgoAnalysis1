package datagen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataGeneration
{
    /*
    Data sizes:
    Small:  2 * 10^1
    Medium: 2 * 10^3
    Large:  2 * 10^5
     */

    private static final int small = 10;
    private static  final int medium = ((int) Math.pow(10,3));
    private static final int large = ((int) Math.pow(10,5));

    private DataGeneration()  {
    }

    //print small dataset to file small.txt
    private void small() throws IOException {
        File file = new File("datagen/small.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        //print root node
        br.write("0 \n");
        // print out samples
        int leftChild = 1;
        int rightChild = 2;
        for (int i = 0; i < small; i++) {
            br.write(i + " ");
            br.write((leftChild) + " ");
            br.write((rightChild) + " ");
            br.write("\n");
            leftChild = leftChild + 2;
            rightChild = rightChild + 2;

        }
        br.close();
        fr.close();
    } // end of sampleWithReplacement()

    //print medium dataset to file medium.txt
    private void medium() throws IOException {
        File file = new File("datagen/medium.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        //print root node
        br.write("0 \n");
        // print out samples
        int leftChild = 1;
        int rightChild = 2;
        for (int i = 0; i < medium; i++) {
            br.write(i + " ");
            br.write((leftChild) + " ");
            br.write((rightChild) + " ");
            br.write("\n");
            leftChild = leftChild + 2;
            rightChild = rightChild + 2;

        }
        br.close();
        fr.close();
    } // end of sampleWithReplacement()

    //print large dataset to file large.txt
    private void large() throws IOException {
        File file = new File("datagen/large.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        //print root node
        br.write("0 \n");
        // print out samples
        int leftChild = 1;
        int rightChild = 2;
        for (int i = 0; i < large; i++) {
            br.write(i + " ");
            br.write((leftChild) + " ");
            br.write((rightChild) + " ");
            br.write("\n");
            leftChild = leftChild + 2;
            rightChild = rightChild + 2;

        }
        br.close();
        fr.close();
    } // end of sampleWithReplacement()


    private static void usage() {
        System.err.println("invalid input");
        System.exit(1);
    } // end of usage()


    public static void main(String[] args) {
        try {
            // data size
            String dataSize = args[0];

            DataGeneration gen = new DataGeneration();

            switch (dataSize) {
                case "small":
                    gen.small();
                    break;
                case "medium":
                    gen.medium();
                    break;
                case "large":
                    gen.large();
                    break;
                default:
                    System.err.println(dataSize + " is an illegal size. Options are 'small', 'medium' and 'large'");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            usage();
        }

    } // end of main()
} // end of class DataGenerator
