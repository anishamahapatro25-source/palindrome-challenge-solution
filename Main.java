import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);

	System.out.println("Enter a string:");
	String input = sc.nextLine();
        String[] result = findTopThreePalindromes(input);

        for (String line : result) {
            System.out.println(line);
        }
    }
    public static String[] findTopThreePalindromes(String s) {
        String[] pal = {"", "", ""};  
        int[] idx = {-1, -1, -1};
        int[] len = {0, 0, 0};
        int n = s.length();
        for (int i = 0; i < n; i++) {
            checkPalindrome(s, i, i, pal, idx, len);
            checkPalindrome(s, i, i + 1, pal, idx, len);
        }

        String[] ans = new String[3];
        for (int i = 0; i < 3; i++) {
            ans[i] = "Text: " + pal[i] + ", Index: " + idx[i] + ", Length: " + len[i];
        }
        return ans;
    }
    private static void checkPalindrome(String s, int left, int right,
                                        String[] pal, int[] idx, int[] len) {

        int n = s.length();

        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {

            int length = right - left + 1;

            if (length > 1) {
                updateTopThree(pal, idx, len, s.substring(left, right + 1), left, length);
            }

            left--;
            right++;
        }
    }
    private static void updateTopThree(String[] pal, int[] idx, int[] len,
                                       String newPal, int start, int length) {
        for (String p : pal) {
            if (p.equals(newPal))
                return;
        }

        for (int i = 0; i < 3; i++) {
            if (length > len[i]) {

                for (int j = 2; j > i; j--) {
                    pal[j] = pal[j - 1];
                    idx[j] = idx[j - 1];
                    len[j] = len[j - 1];
                }

                pal[i] = newPal;
                idx[i] = start;
                len[i] = length;
                break;
            }
        }
    }
}
