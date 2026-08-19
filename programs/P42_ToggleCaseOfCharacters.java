package programs;

/**
 * ============================================================
 * PROGRAM 42: Toggle Case of Each Character in String
 * ============================================================
 * Problem: WAP to toggle the case of every character in a string.
 *   - Lowercase -> Uppercase
 *   - Uppercase -> Lowercase
 *   - Input : "JaVa 21 AnTiGrAvItY"
 *   - Output: "jAvA 21 aNtIgRaViTy"
 * ============================================================
 */
public class P42_ToggleCaseOfCharacters {

    public static String toggleCase(String str) {
        if (str == null) return null;

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String original = "JaVa 21 AnTiGrAvItY!";
        System.out.println("Original: " + original);
        System.out.println("Toggled : " + toggleCase(original));
    }
}
