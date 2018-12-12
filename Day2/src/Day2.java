//
//    --- Day 2: Inventory Management System ---
//    You stop falling through time, catch your breath, and check the screen on the device. "Destination reached. "
//    + "Current Year: 1518. Current Location: North Pole Utility Closet 83N10." You made it! Now, to find those anomalies.
//
//    Outside the utility closet, you hear footsteps and a voice. "...I'm not sure either. But now that so many people have "
//    + "chimneys, maybe he could sneak in that way?" Another voice responds, "Actually, we've been working on a new kind of "
//    + "suit that would let him fit through tight spaces like that. But, I heard that a few days ago, they lost the prototype"
//    + " fabric, the design plans, everything! Nobody on the team can even seem to remember important details of the project!"
//
//    "Wouldn't they have had enough fabric to fill several boxes in the warehouse? They'd be stored together, so the box IDs"
//    + " should be similar. Too bad it would take forever to search the warehouse for two similar box IDs..." They walk too far
//    away to hear any more.
//
//    Late at night, you sneak to the warehouse - who knows what kinds of paradoxes you could cause if you were discovered - 
//    and use your fancy wrist device to quickly scan every box and produce a list of the likely candidates (your puzzle input).
//
//    To make sure you didn't miss any, you scan the likely candidate boxes again, counting the number that have an ID containing 
//    exactly two of any letter and then separately counting those with exactly three of any letter. You can multiply those two counts 
//    together to get a rudimentary checksum and compare it to what your device predicts.
//
//    For example, if you see the following box IDs:
//
//    abcdef contains no letters that appear exactly two or three times.
//    bababc contains two a and three b, so it counts for both.
//    abbcde contains two b, but no letter appears exactly three times.
//    abcccd contains three c, but no letter appears exactly two times.
//    aabcdd contains two a and two d, but it only counts once.
//    abcdee contains two e.
//    ababab contains three a and three b, but it only counts once.
//    Of these box IDs, four of them contain a letter which appears exactly twice, and three of them contain a letter which appears
//    exactly three times. Multiplying these together produces a checksum of 4 * 3 = 12.
//
//    What is the checksum for your list of box IDs?

//    --- Part Two ---
//    Confident that your list of box IDs is complete, you're ready to find the boxes full of prototype fabric.
//
//    The boxes will have IDs which differ by exactly one character at the same position in both strings. For example, given the following box IDs:
//
//    abcde
//    fghij
//    klmno
//    pqrst
//    fguij
//    axcye
//    wvxyz
//    The IDs abcde and axcye are close, but they differ by two characters (the second and fourth). However, the IDs
//    fghij and fguij differ by exactly one character, the third (h and u). Those must be the correct boxes.
//
//    What letters are common between the two correct box IDs? (In the example above, this is found by removing the differing
//    character from either ID, producing fgij.)

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2 {

    private static final List<String> INPUT_LIST = Arrays.asList("pbopcmjeizuhxlqnwasfgtycdm", "pjokrmjeizuhxlqnfasfguycdv", "pbokrpjejkuhxlqnwasfgtycdv", "sbokrmjeizuhxaqnwastgtycdv", "pbokrmjeizuhxljnnasfgtycnv", "pbokrqjeizuhxbqndasfgtycdv", "bbokrmjeizuhxlqnwasfgtycfj", "pbokrmjeisuhxlqnwasfzdycdv", "pbokrmjefxuhxlqnwasfptycdv", "pqokrmjenzuhxlqnwasfgtygdv", "pbokrmjeizunklqnwassgtycdv", "pbokrmjeizghxvqnkasfgtycdv", "lboirmjeizuhxlqnwfsfgtycdv", "pbofrmjeizuhalqnwasfgtyddv", "pbokrmjeiguhplqcwasfgtycdv", "pbokrmjeizzhxlqnwavfgtyctv", "pbokrmjeizuhxlqnwaefgtycaj", "pbokzmjedzuhxlqnwasfgtlcdv", "pnokrmjegzuhxlbnwasfgtycdv", "pbojrmjeizuhtlqniasfgtycdv", "pbokxmiefzuhxlqnwasfgtycdv", "pbokrmoeizuhxlqnpasngtycdv", "abokrmjeezuhxlqnwasfdtycdv", "pbokrmyeizugxlqnwasfgtycda", "pbokdmjeizuhxlqnuatfgtycdv", "psokrmjeiauhxlqnwasxgtycdv", "pbokrmjeizuhxlqzwasfgtyzdy", "pboktmjeizuhxnqndasfgtycdv", "pbodrrjeizuhxlqnwasfgtycdb", "pbokrmjekzuhxljnwasfgtycuv", "pbokrmjnizuhllqnwawfgtycdv", "prmkrmjeiwuhxlqnwasfgtycdv", "pbokrmjeizkhxlenwajfgtycdv", "pbofrmjeizuixlqnwasfgoycdv", "gbhkrmjeizuhclqnwasfgtycdv", "pbokrmweizuwxlqnwasfgtycyv", "pbukrmjeizuhxlqnwasfgqhcdv", "pbokxmjeizuhxlqnwasfgtecdu", "pbokomjeizuhrlqnfasfgtycdv", "bbokymjeizuhxlqnpasfgtycdv", "pbodrmjeizuhxlqnwadfgtgcdv", "zbokrljeipuhxlqnwasfgtycdv", "pboermjeizuxxlqnwasfgtpcdv", "pqbkrmjeizuhxlqnjasfgtycdv", "pbokrmfeizuhxvqgwasfgtycdv", "pbokrmjeizuhzlqnjasfdtycdv", "rbofrmjeizkhxlqnwasfgtycdv", "pbokrmseizubxlqnwasfgtycdy", "pbocrmjeizuhxaqnwasfgtycda", "pbokrmjeizuhxlqndakfgtscdv", "pbokrrjeizuhxlqnwgshgtycdv", "pbokrajeizuhxpqnwasrgtycdv", "pbokrbjeizubxlqnwssfgtycdv", "pbokemjhizuhxlqnwazfgtycdv", "pbokrmjeizuhxlqntisfgtyrdv", "pbokrmjwinuhxlqnwasfgkycdv", "pypkrmjeizuhxlqtwasfgtycdv", "pbokrmjeizuhxlqniasfrpycdv", "pbokomjeizuhxlqnwasfgtgcdw", "pbokrmjeizusplqnwxsfgtycdv", "pbodrmueizxhxlqnwasfgtycdv", "pbokwmjeizurxlqnwasfgtycdi", "pbohrmjejzuhxlqnwasfgtycgv", "pbokrmtqizuhxlqnwasfitycdv", "ptozrmjeizuhylqnwasfgtycdv", "pbokrmjtizuhxlqfwasfgtykdv", "pbokrmpeizuhxnqmwasfgtycdv", "pbokrmjeizujxlynwtsfgtycdv", "dbokrmjeizuhxlqnwasngticdv", "pbskrmjeizuhxlqnrasfttycdv", "pbwkrmjerzuhxlqnwaslgtycdv", "pboyrmceizuhxlqnwssfgtycdv", "pbokpmjeizchxlqngasfgtycdv", "pbokrmjenzuhxlqnwcsfgxycdv", "pbxkrmjeizuhxlqnwadfgtyckv", "pbqkrmjeizuhxlqnwasdgdycdv", "pbokrmoeizdhxlqnwasfgtycqv", "pbokrmjejzuhxlqnwksfgtycwv", "pbfkrmjeieuhxlnnwasfgtycdv", "pbokrmjeiuuhxlqnpalfgtycdv", "pbokrmjeizunxyqnwasfgtdcdv", "pbokrmjeazuhxrqnwasogtycdv", "pbmkrmjeizuhxlqnwaufgtycdj", "xbskrmjeipuhxlqnwasfgtycdv", "tbokrujlizuhxlqnwasfgtycdv", "xbokvmjeizuhxyqnwasfgtycdv", "pbnhrmheizuhxlqnwasfgtycdv", "pboorajrizuhxlqnwasfgtycdv", "pbokrmjeizuhxminwusfgtycdv", "pboqrmjeizuhxlqnwaslgtscdv", "pgokrdjeizuhxlnnwasfgtycdv", "pbokrmjeizuhxiqnwasvttycdv", "pbokrmwnizuhzlqnwasfgtycdv", "pbokrmjlizmhjlqnwasfgtycdv", "pbwkrmjeizohxlqnwasfgtyzdv", "pbykrmjmizwhxlqnmasfgtycdv", "pbokrmjzizuhxeqnwasfgtpcdv", "pbokrmjewzuhxzqnwasfgtybdv", "pbokrmjeimupxlonwasfgtycdv", "pbokrmjvizuhxlqnuasfgtycqv", "pbokrmjeizjdxlqnwasfetycdv", "pbofrmjeizurxlqnwasfztycdv", "pbozrmjeizuhxxqpwasfgtycdv", "pbovtmjeizuhxlqnwapfgtycdv", "prokrmjeuzuhxlqnwasfgtycqv", "ubokrmjeizuhxljnwasfgtdcdv", "pboknmjepzuhxlqnwasogtycdv", "pbokrmjaizuaxljnwasfgtycdv", "pbdkrcjeizuhxlqnwasfgtvcdv", "pbokymjeizuhxlqnwaxfgtyfdv", "pbokrmjaizuhxlqnfasfgtyodv", "pdekrmmeizuhxlqnwasfgtycdv", "rbokrmjeizuuxlqnwasfgtycdj", "pbokrmneifuhxlqiwasfgtycdv", "pbokrmjeizlbxlunwasfgtycdv", "pbokrmjewzuhxxqnwasfgoycdv", "pbokrmjeizuhxlqtwasfgtzcdo", "pbokrmkeizzhxlqnwasfgtycmv", "pbokrmjeiquhxlqnywsfgtycdv", "xbokrmjeizjhxlqdwasfgtycdv", "pbokrmjeizahxzqnzasfgtycdv", "pbokrmjeizuhxmqmwasfgtytdv", "pbokrmheiluhxlqnwasfgoycdv", "rbokrmjeizuhxlqnwaslgtycqv", "pbbkzmjeizuhxvqnwasfgtycdv", "pbokrmjeizudxlznwgsfgtycdv", "pbokemjeizuhxlqnwascgtysdv", "pbokrmjdizuexlgnwasfgtycdv", "pbokzmjeizuhxlqnwnsfggycdv", "pbokrmjeizuhxtqnwasfgiycdy", "bbokrmjeizuhclunwasfgtycdv", "pbtkrmjeieuhxlqnwasfgtycrv", "pbokrmjeizutxlbnwasngtycdv", "pbokrmjevzumxlqnwasfgtyydv", "pbokrmjsizuhxlqowasfgtycyv", "pbssrmjeizuhxlqbwasfgtycdv", "pbokrmjeizuhflqnwxsfstycdv", "pbokimjeizuhxlqnwasfgtywdm", "pbokrmjbizuhxlqdwasfgtygdv", "pbokrmheizuhxlqxwasfgtycnv", "poakrmjeizuhylqnwasfgtycdv", "vbrkrmjeizuhxlqnwaszgtycdv", "pbokrmjeizuhxiqnudsfgtycdv", "pbokrldeizuhxlqnwasjgtycdv", "pbokrmjeizjhflqnwasfgtymdv", "pbokrmjeizuhxliawasfgtvcdv", "pbokrmjeisuhtoqnwasfgtycdv", "nbokrijeizuhxlqnwasfgtycdh", "pbokrmjeizrhxlqnwxsfztycdv", "pbotrmjeizuhxlcnwasfgtyvdv", "pbokrmjewzuhxlquwasfgtjcdv", "pbosrmjeipuhxlqnwasfgtvcdv", "pbokrmjebzurxlunwasfgtycdv", "pbogimieizuhxlqnwasfgtycdv", "pbokrmjeizihxlqnwasagtyzdv", "pbokrmjeizuoxlqnausfgtycdv", "pbokrmjeizuhxlqnwashgbjcdv", "pbokrdjeizuhxlnnwasfgoycdv", "pbokrzjtizlhxlqnwasfgtycdv", "peokrmjexzuhxlqnwasfgoycdv", "cboprmjeizuhxlqnwasfgfycdv", "pbitrmjeizjhxlqnwasfgtycdv", "pbourmjeizuhxldnwjsfgtycdv", "pboivmjeizuhxlqnwasvgtycdv", "pbokrmjeiduhxaqnqasfgtycdv", "pbokicjeiwuhxlqnwasfgtycdv", "pbokrmmeizulxlqnwasfgtyvdv", "pbokrmjeieuhxlqnaapfgtycdv", "pbokxmjeiuuhxlqnwasfgtyqdv", "pbokrmjeizuhxgqniaslgtycdv", "pbokrmjeizuuxlqnwisfgtyckv", "pbovlmjepzuhxlqnwasfgtycdv", "pbokrmjeizuhxlqdwaqfgtycdj", "pbztrvjeizuhxlqnwasfgtycdv", "pbokrmjeizuholunwasfptycdv", "pbokrmjeizudxlqnwusfgtycqv", "nbokrmjzizmhxlqnwasfgtycdv", "pbokrmjeypunxlqnwasfgtycdv", "pbokrjjxizuhxlqnwasfgtyddv", "pbokrmjeizuhilqnwiufgtycdv", "pbokrmjeizuhxtqowasfgfycdv", "qbokrgjeizuhxlqnwasfgtycdx", "pvoarmjeizuhxlqnwasfgtfcdv", "pbokrmjjizuhxlqnwasfggyczv", "pbtkrmjeizuhnlqncasfgtycdv", "pbokrmjeizuzxlqnwasfgtyjnv", "jmokrmzeizuhxlqnwasfgtycdv", "pbykrmjmizwhxlqnwasfgtycdv", "nbokrmjeizlhxlqnwasfgtecdv", "pbokrmjeizuhxlqhwasrgrycdv", "pbokrmjeiruhxlqnwasfgtnedv", "pbokrmjeizohxlznwasfgtycuv", "paokrmjdizuhxlqnwasfktycdv", "pbokrmjetzutxlqnwasfntycdv", "pboyrmjeizuhxlqnwasfgtetdv", "pbokgujeizuhxlqwwasfgtycdv", "pbokrifeizshxlqnwasfgtycdv", "sbokrmjeizfhxlqnaasfgtycdv", "pbokrmjeizuhxlqpwrsfgfycdv", "pbokxmjeikuhxlqnwasfctycdv", "fbokrmjhizuhxlqnmasfgtycdv", "pbekamjeizuhxlqnwaxfgtycdv", "pboksmpeizuhxlqnwasfgtyclv", "pbokrmjeizrhxdqnwasfgzycdv", "pbogrmxeizurxlqnwasfgtycdv", "pbokrmjeieuhxlqnwqsfgtychv", "vbokrmjeizuhxlqnwabfgtycdq", "lbokrmjeizupxlqvwasfgtycdv", "pbokrmjeizuhglqnuasfgtucdv", "hbokrmjeizuhelqnwasfgtrcdv", "pbokrmweizuhxlqnwhsfgtyvdv", "pbokrmjeizuhxrqnwasfvtccdv", "pbokrmneizuhxlwnyasfgtycdv", "ybokymjeqzuhxlqnwasfgtycdv", "pbousmjeizuhxlqswasfgtycdv", "pblkimjeizuhxlqnwacfgtycdv", "psokrmjeizuhxlqnwasfgbpcdv", "peokrwjeizghxlqnwasfgtycdv", "pbokrmjeizudxlqnwzsfrtycdv", "pbotrmjezzxhxlqnwasfgtycdv", "pkokrmjezzuhxlqnwasfgtycdh", "pbokrmleizuhxlnnwasfgtyndv", "pboxwmjeituhxlqnwasfgtycdv", "pbokrmjeizoczlqnwasfgtycdv", "pbokomjeizuhxlqnwhsfgtybdv", "pbhwrmjeizuhxlqnwasfgpycdv", "pbwkrmjeizuhxeqnwasfgtyidv", "pbokrmjeizuhxlqnjasfgmicdv", "tbokrgjeizuhxlqhwasfgtycdv", "pbolrqjeizuhxlqnhasfgtycdv", "pbogrhjeizbhxlqnwasfgtycdv", "pbokrmjeizghxlqnwashgtycdx", "pbokrmjeizuhrlqnwasfgthcrv", "pbokrmjeizuhxlqnwfsngtacdv", "pbokrmxeizuhxlqnwasfotyctv", "pbokrmjeizuhxlqnwcsfgnocdv", "pnokbmjeizuhxlqnwasfgtscdv", "pbowrmjeuzuhxlqnwasfgtycdw", "pbokrmjeiyuhxlqnwasqgtvcdv", "pbokrmjeivuhxkpnwasfgtycdv", "pbokomjeizuhxlqnwasfgtylav", "pbdkrmjeizuhxlgnwjsfgtycdv", "pbokrmjeizuaxxqnwasfytycdv", "pbokrmjerzuhxlqnwasfgtscdk", "pbokrmzerzuhxlqnwasfntycdv", "pbokrmjeizumxdqnwasfgtyckv", "pbtkrmjeizrhxlqnwasfgtjcdv", "pbmkrmjuizuhxlqnwasfgtytdv", "pbokpmjeizuhxlqnwastgtzcdv", "kbokrmjeizuhxlqnwasfgzjcdv");
    private static final List<String> TEST = Arrays.asList("jaksie", "jagsia", "haksie", "jagsie");
    
    public static void main(String[] args) {
//        printing result of PART1
        System.out.println("PART1: " + part1(INPUT_LIST));
//        printing result of PART2
        System.out.println("PART2: " + part2());
    }

    private static int part1(List<String> list) {
        int counterOfThree = 0;
        int counterOfTwo = 0;
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (Character c : list.get(i).toCharArray()) {
                Integer counter = map.get(c);
                int newCount = (counter == null ? 1 : counter + 1);
                map.put(c, newCount);
            }
            List<Integer> listOfTwo = map.entrySet().stream().map(Map.Entry::getValue).filter(s -> s == 2).collect(Collectors.toList());
            List<Integer> listOfThree = map.entrySet().stream().map(Map.Entry::getValue).filter(s -> s == 3).collect(Collectors.toList());
            if (listOfTwo.size() > 0) {
                counterOfTwo++;
            }
            if (listOfThree.size() > 0) {
                counterOfThree++;
            }
            map.clear();
        }
        return counterOfThree * counterOfTwo;
    }
    
    private static String part2() {
        String[] array = findClosestPair(TEST);
        System.out.println("FIRST : " + array[0] + array[0].length());
        System.out.println("SECOND: " + array[1] + array[1].length());
        String common = "";
        for (int i = 0; i < array[0].length(); i++) {
            if (array[0].charAt(i) == array[1].charAt(i)) {
                common += array[0].charAt(i);
            }
        }
        System.out.println("WYNIK : " + common + " " + common.length());
        return common;
    }
    
    private static int closeness(String first, String second) {
        int min = Math.min(first.length(), second.length());
        int diff = Math.abs(first.length() - second.length());
        for (int i = 0; i < min; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
    
    // There is a bug
    private static String[] findClosestPair(List<String> list) {
        int closestDiff = list.get(0).length() + 1;
        String[] closestPair = new String[2];
        for (int i = 0; i < list.size() - 1; i++) {
            String firstElement = list.get(i);
            String secondElement = list.get(i + 1);
            int diff = closeness(firstElement, secondElement);
            if (diff < closestDiff) {
                closestDiff = diff;
                closestPair[0] = firstElement;
                closestPair[1] = secondElement;
                System.out.println(diff);
                System.out.println(firstElement);
                System.out.println(secondElement);
            }
        }
        return closestPair;
    }
}
