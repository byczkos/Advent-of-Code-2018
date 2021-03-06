
//    --- Day 1: Chronal Calibration ---
//    "We've detected some temporal anomalies," one of Santa's Elves at the Temporal Anomaly
//    Research and Detection Instrument Station tells you. She sounded pretty worried when she 
//    called you down here. "At 500-year intervals into the past, someone has been changing Santa's history!"
//
//    "The good news is that the changes won't propagate to our time stream for another 25 days, and"
//    + " we have a device" - she attaches something to your wrist - "that will let you fix the changes "
//    + "with no such propagation delay. It's configured to send you 500 years further into the past every"
//    + " few days; that was the best we could do on such short notice."
//
//    "The bad news is that we are detecting roughly fifty anomalies throughout time; the device will indicate"
//    + " fixed anomalies with stars. The other bad news is that we only have one device and you're the best"
//    + " person for the job! Good lu--" She taps a button on the device and you suddenly feel like you're falling.
//    To save Christmas, you need to get all fifty stars by December 25th.
//
//    Collect stars by solving puzzles. Two puzzles will be made available on each day in the advent calendar;
//    the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
//
//    After feeling like you've been falling for a few minutes, you look at the device's tiny screen.
//    "Error: Device must be calibrated before first use. Frequency drift detected. Cannot maintain destination lock."
//    Below the message, the device shows a sequence of changes in frequency (your puzzle input). 
//    A value like +6 means the current frequency increases by 6; a value like -3 means the current frequency decreases by 3.
//

//    --- Part One ---
//    For example, if the device displays frequency changes of +1, -2, +3, +1, then starting from a frequency of zero,
//    the following changes would occur:
//
//    Current frequency  0, change of +1; resulting frequency  1.
//    Current frequency  1, change of -2; resulting frequency -1.
//    Current frequency -1, change of +3; resulting frequency  2.
//    Current frequency  2, change of +1; resulting frequency  3.
//    In this example, the resulting frequency is 3.
//
//    Here are other example situations:
//
//    +1, +1, +1 results in  3
//    +1, +1, -2 results in  0
//    -1, -2, -3 results in -6
//    Starting with a frequency of zero, what is the resulting frequency after all of the changes in frequency have been applied?


//    --- Part Two ---
//    You notice that the device repeats the same frequency change list over and over. To calibrate the device, you need to find the
//    first frequency it reaches twice.
//
//    For example, using the same list of changes above, the device would loop as follows:
//
//    Current frequency  0, change of +1; resulting frequency  1.
//    Current frequency  1, change of -2; resulting frequency -1.
//    Current frequency -1, change of +3; resulting frequency  2.
//    Current frequency  2, change of +1; resulting frequency  3.
//    (At this point, the device continues from the start of the list.)
//    Current frequency  3, change of +1; resulting frequency  4.
//    Current frequency  4, change of -2; resulting frequency  2, which has already been seen.
//    In this example, the first frequency reached twice is 2. Note that your device might need to repeat its list of frequency 
//    changes many times before a duplicate frequency is found, and that duplicates might be found while in the middle of processing the list.
//
//    Here are other examples:
//
//    +1, -1 first reaches 0 twice.
//    +3, +3, +4, -2, -4 first reaches 10 twice.
//    -6, +3, +8, +5, -6 first reaches 5 twice.
//    +7, +7, -2, -7, -4 first reaches 14 twice.
//    What is the first frequency your device reaches twice?

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FrequencyCalculator {

    private static final List<Integer> INPUT_LIST = Arrays.asList(-7, 16, 5, 11, 18, -14, 11, 14, -2, 13, -12, 10, 1, 16, 17, 5, -8, 17, 15, -17, 7, -1, -3, -8, -12, -1, -14, -19, 2, -19, 5, 10, 1, -9, -18, -3, 8, 3, 1, 5, 7, -2, -21, -2, 11, 10, 19, 8, -15, 19, -3, 7, -1, -13, 5, 17, -18, 7, 9, 1, -6, 13, -3, 12, 17, 1, 10, 9, -17, -15, 14, 13, 15, 12, 2, -19, 11, -3, 10, 17, -6, 11, -2, 13, 17, 16, 4, -16, 14, -10, -2, -13, -11, -1, -5, -5, 15, 12, -1, -2, 6, 8, 2, 8, -17, -11, -17, -12, -4, -3, -1, -1, 13, 11, 10, -3, -9, 19, 19, 4, 15, 19, -14, -6, -8, -9, -9, 21, -1, 2, 15, -1, 3, 3, 14, 3, 3, -9, 4, 19, 6, -7, -15, 4, 3, 7, -5, 20, 13, -6, 19, 8, 15, 6, 9, 17, -7, -11, 7, -19, 16, 6, -8, -7, 18, 9, 16, 8, -5, -8, 10, 11, 3, -7, -18, -3, -9, 17, -18, -16, -18, -18, 16, -1, -1, -5, -16, -2, -3, 2, 5, 10, -13, -6, -15, 14, -12, -1, 17, -2, 12, 12, 19, 12, -15, 20, -6, 15, 11, 15, 14, -16, -2, -17, -2, -9, -30, -12, -14, 16, 12, 2, 15, 18, 24, 11, 18, -7, 6, 5, 15, -4, 10, 8, 4, -11, -14, -6, -19, 16, -2, -11, -15, -10, 19, -1, 5, 14, -8, -18, -13, 16, 10, 9, 13, 5, 1, 1, -13, -20, -23, -3, -20, 14, -15, 12, -28, 22, -23, -14, -16, 2, -6, -17, -18, -13, 4, 5, 9, 14, 1, 16, 8, 1, -23, -5, -3, -1, 3, -13, -11, -2, 16, 6, -23, 13, -4, -1, -20, -3, -6, 12, -11, -6, 8, 2, -19, 8, -20, 1, 17, 10, 14, 1, -37, 19, -20, -21, -2, -11, -7, 4, 7, 21, 19, -8, -14, 17, -8, 9, 11, 18, -16, 33, 5, 3, 43, 17, 39, 20, 3, 15, 17, 19, 19, -15, 5, -7, 15, 13, -15, -19, 12, 8, 13, 11, -20, -9, 10, -3, -4, -8, -17, 10, 14, -3, -18, -10, 8, 13, 19, -27, -36, 19, 19, -32, 10, -12, -3, 28, -11, -35, -7, -19, -13, -22, -21, 11, 13, 12, 26, 131, 7, 9, -10, 8, -19, 26, 10, 1, 21, 11, 10, 17, -16, 8, -4, 7, 8, -1, 19, -11, 4, -17, 18, -17, 1, -17, -11, 14, 8, -12, 5, 22, -1, -3, 13, -1, -11, -15, -1, -21, -14, 20, -2, -15, -18, 21, -11, 12, 6, -12, 20, -22, -32, -31, 9, 25, 14, 14, 86, -9, 13, 5, 10, -20, -3, -6, -2, 7, 15, 1, -24, -12, 19, -21, 80, -32, -5, 22, -18, 56, -30, 21, -111, -107, 424, 535, 64723, -2, 7, -14, 5, 11, -8, 17, 1, -8, -17, 3, 8, 16, -4, 6, 13, -6, 1, 4, -3, -3, 13, -18, 13, 2, 18, -3, -14, 10, 12, -9, 12, 11, 6, -8, -7, 2, 18, -12, -9, 15, 3, -4, 3, -15, -19, 21, -9, 2, -5, -14, 15, -18, 6, 8, -17, -4, -6, 5, 19, -7, -18, -13, -19, -10, -19, -19, -2, -7, -5, 8, -1, -1, -9, 5, -7, -15, -2, 8, 17, 6, 9, -14, -10, -9, 14, -12, -3, -9, 13, 8, -4, 9, 14, -5, 19, 3, 8, 4, 13, -9, -6, -14, 18, 9, -4, -2, 1, -13, -16, 22, -2, -16, -2, 1, 12, -5, 3, -8, 21, -9, -19, 3, -2, 7, -10, 22, -23, 14, 23, 6, -1, 5, -9, 12, 7, 6, 8, 6, -1, -3, -14, 7, -26, 15, -36, 10, 25, 10, -3, 6, 30, 18, -16, 3, 20, 1, 21, 3, 8, 3, 18, 5, -18, -18, 15, -12, -6, 9, 16, 3, 5, -18, 14, 9, 9, 18, -7, -10, 14, -5, 13, 9, -4, -14, 2, -12, 4, 5, 4, 5, 4, 15, 9, -17, 2, -10, 6, -2, 17, 10, 15, 9, -14, 16, 2, -10, -6, -15, -8, -6, -1, 5, -3, 14, 15, -5, -2, 17, 11, 16, 7, 18, 4, 16, 16, -5, 18, 10, 16, 4, -11, 4, 4, 18, -13, -12, 17, 13, -19, 16, 17, 9, -18, 3, 8, -10, -13, -5, -5, -20, -11, 10, -5, -3, -11, -8, 11, -10, 12, -14, 15, -10, -21, 8, -14, -12, -8, -5, 15, -16, -2, -4, -6, -12, -6, 10, 15, -16, -7, 6, -11, -10, -12, -12, 8, -15, 18, -9, -14, -1, -9, -3, -19, -9, -13, 15, 8, 14, -26, -9, -5, -17, 10, -6, 10, 16, -18, -5, 20, 22, 22, -15, -10, -21, 19, -10, 6, -19, -19, -8, -10, 13, -8, -17, 4, 15, -13, 9, -10, -35, 17, -4, 43, 15, -28, 3, 21, 17, 32, 9, 37, -8, 6, 5, 2, 21, -18, -12, -16, 5, 6, 27, -7, -1, -1, 11, 18, 3, 10, -18, -3, -16, 20, 18, 7, -19, 14, 16, -12, -16, -11, 14, -9, -14, 10, -8, 11, 25, 1, 18, 17, -18, -11, 13, 12, -7, 1, 18, -1, 11, -13, -7, 16, -19, 17, -20, -6, -16, 5, 2, 1, -21, 9, 28, 26, 8, 4, 2, 7, 15, 5, 2, 15, -9, 19, 5, -13, 2, -3, -2, -18, -6, -15, -16, -11, 24, 16, 17, -3, 6, -5, 4, 30, 4, -19, 17, -10, -18, -17, -15, -11, -22, -12, -6, -10, -20, 15, 49, 21, -7, -6, 12, -19, -39, -7, -7, 50, 29, 6, 36, -18, 11, 14, 8, 15, -40, 231, 56, -7, -4, -42, -7, -16, -15, 159, 471, -309, 65050, -5, -18, -17, 7, -11, 3, -18, 5, -8, -11, -14, 13, 3, -14, 16, 19, 8, -9, -10, 9, 14, 16, 1, 17, 17, 14, -4, -19, 13, 18, 11, 17, -14, 17, 14, -3, 19, -7, 12, 3, 2, -16, -14, 2, -5, -18, 6, -17, -5, 3, 17, 8, -14, -13, 17, 12, -11, -17, -15, -17, -10, -12, -1, -13, -5, -2, -2, -11, -6, -2, -131610);

    public static void main(String[] args) {
//        printing result of PART1  
        System.out.println("PART1: " + part1(INPUT_LIST));
//        printing result of PART2
        System.out.println("PART2: " + part2(INPUT_LIST));
    }

    private static int part1(List<Integer> list) {
        Integer result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        return result;
    }

    private static int part2(List<Integer> list) {
        Integer result = 0;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        boolean flag = true;
        int i = 0;
        while (flag) {
            if (i == list.size()) {
                i = 0;
            }
            result += list.get(i);
            if (!map.containsKey(result)) {
                map.put(result, 1);
            } else {
                map.put(result, map.get(i) + 1);
                if (map.get(result) == 2) {
                    return result;
                }
                flag = false;
            }
            i++;
        }
        return 0;
    }
}
