import java.util.Scanner;

/*You are in charge of choosing which of the n experimental gadgets G_1, G_2, ..., G_n are to be
 transported to the International Space Station. The gadgets' weights are w_1, w_2, ..., w_n, 
 and the future profits from the experiments conducted with the help of those gadgets are 
 estimated as p_1, p_2, ..., p_n respectively. Naturally, transporting goods to the ISS is very 
 expensive, and you have a weight limit W for the total weight of the gadgets you are allowed to
  put on the space shuttle. In this contest you are to write a program which given the gadgets' 
  profits p_1, p_2, ...., p_n, their weights w_1, w_2, ..., w_n, and the weight limit W, 
  determines which gadgets to take on the shuttle in order to maximize the future profit.*/

public class Solution3 {
    private static int heavyGadgets(int n, int profits[], int weights[], int W) {
        if (n == 0 || W == 0) {
            return 0;
        }

        if (W < weights[n - 1]) {
            return heavyGadgets(n - 1, profits, weights, W);
        }

        else {
            return Math.max(
                    profits[n - 1] + heavyGadgets(n - 1, profits, weights, W - weights[n - 1]),
                    heavyGadgets(n - 1, profits, weights, W));
        }
    }

    public static void main(String args[]) {
        Scanner scanner2 = new Scanner(System.in);

        String line1;
        String line2;
        String line3;
        String line4;

        line1 = scanner2.nextLine();
        line2 = scanner2.nextLine();
        line3 = scanner2.nextLine();
        line4 = scanner2.nextLine();

        int n = Integer.parseInt(line1);
        int W = Integer.parseInt(line4);

        String[] items = line2.replaceAll("\\s", "").split(",");
        String[] items2 = line3.replaceAll("\\s", "").split(",");

        int profits[] = new int[items.length];
        int weights[] = new int[items2.length];

        for (int i = 0; i < items.length; i++) {
            try {
                profits[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatException();
            }
        }

        for (int i = 0; i < items2.length; i++) {
            try {
                weights[i] = Integer.parseInt(items2[i]);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatException();
            }
        }
        System.out.println(heavyGadgets(n, profits, weights, W));
    }
}

