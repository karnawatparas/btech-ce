import java.util.Set;
import java.util.HashSet;

public class Permutations {

    Set<String> permutations;

    public Permutations() {
        permutations = new HashSet<>();
    }

    public void generatePermutations(String s, String result) {

        int length = s.length();
        if (length == 0) {
            permutations.add(result);
        } else {
            for (int i = 0; i < length; i++) {
                char initial = s.charAt(i);
                String rest = s.substring(0, i) + s.substring(i + 1);
                generatePermutations(rest, result + initial);
            }
        }
    }

    public void clear() {
        permutations.clear();
    }

    public static void main(String[] args) {

        Permutations perm = new Permutations();

        perm.clear();
        perm.generatePermutations("abcd", "");
        System.out.println("Permuations of 'abcd' (" + perm.permutations.size() + ") \n\n" + perm.permutations + "\n");

        perm.clear();
        perm.generatePermutations("aaaa", "");
        System.out.println("Permuations of 'aaaa' (" + perm.permutations.size() + ") \n\n" + perm.permutations + "\n");

        System.out.println();
    }

}