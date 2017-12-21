import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by David on 7/24/2017.
 */
public class main {
    static String[] all = {
      "GB","AF", "HB", "GI", "AD", "GH", "GE", "JI", "JA", "JG", "DC", "BF", "HJ", "GA", "AD", "AA", "GD", "IJ",
            "JG", "HH", "AJ", "CJ", "JC", "HC", "JH", "EAD", "JE", "IB", "DJ", "IE", "JB", "DF", "AJ", "HF", "HA",
            "GC", "HG", "JA", "GJ", "DA", "GH", "HJ", "AF"
    };

    static String[] amr = {
            "JHBJFGDEGE", "JH", "HI"
    };

    static ArrayList<String> mapstrings = new ArrayList<>();
    static String asdf = "ABCDEFGHIJ";

    public static void main(String args[]) {
        permutation("", asdf);
        System.out.println("perm done");
        generateMaps(mapstrings);
        System.out.println("maps done");

    }

    public static double convert(String s, HashMap<Character, Integer> map) {
        char[] chars = s.toCharArray();
        String intString = "";
        for (int i = 0; i < s.length(); i++) {
            intString += Integer.toString(map.get(chars[i]));
        }
        return Double.parseDouble(intString);
    }

    public static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            mapstrings.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }

    public static void generateMaps(ArrayList<String> list) {
        for (String s:list) {
            HashMap<Character, Integer> map = new HashMap<>();
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                map.put(c[i], i);
            }

            double[] nums = new double[43];
            double[] amrvals = new double[3];
            for (int i = 0; i < all.length; i++) {
                nums[i] = convert(all[i], map);
            }
            amrvals[0] = average(nums);
            amrvals[1] = median(nums);
            amrvals[2] = range(nums);

            if (amrvals[1] != convert(amr[1], map)) {
                continue;
            }

            if (amrvals[2] != convert(amr[2], map)) {
                continue;
            }

            System.out.println(map);
            System.out.println(amrvals[0]);
            System.out.println(convert(amr[0], map));
        }
    }

    public static double average(double[] doubles) {
        double sum = 0;
        for (double d:doubles) {
            sum += d;
        }

        double total = sum/doubles.length;
        return total * Math.pow(10, 9);
    }

    public static double median(double[] doubles) {
        Arrays.sort(doubles);
        double median;
        if (doubles.length % 2 == 0) {
            median = (doubles[doubles.length/2] + doubles[doubles.length/2 - 1]) / 2 ;
        } else {
            median = doubles[doubles.length/2];
        }

        return median;
    }

    public static double range(double[] doubles) {
        double min = 999;
        double max = -1;
        for (double d: doubles) {
            if (d > max) {
                max = d;
            }

            if (d < min) {
                min = d;
            }
        }

        return max - min;
    }
}
