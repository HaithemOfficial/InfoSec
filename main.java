import java.util.Scanner;

class VigenereCipher {
    
    static String generateKey(String text, String key) {
        StringBuilder newKey = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++, j++) {
            if (j == key.length()) j = 0;
            newKey.append(key.charAt(j));
        }
        return newKey.toString();
    }
    
    static String encrypt(String text, String key) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int p = Character.toUpperCase(c) - 'A';
                int k = key.charAt(j) - 'A';
                char encryptedChar = (char) ((p + k) % 26 + 'A');
                cipherText.append(encryptedChar);
                j = (j + 1) % key.length();
            } else {
                cipherText.append(c);
            }
        }
        return cipherText.toString();
    }
    
    static String decrypt(String cipherText, String key) {
        StringBuilder origText = new StringBuilder();
        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                int e = c - 'A';
                int k = key.charAt(j) - 'A';
                char decryptedChar = (char) ((e - k + 26) % 26 + 'A');
                origText.append(decryptedChar);
                j = (j + 1) % key.length();
            } else {
                origText.append(c);
            }
        }
        return origText.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Plaintext: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter Key: ");
        String keyword = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        
        String key = generateKey(text.replaceAll("[^A-Za-z]", ""), keyword);
        String cipherText = encrypt(text, key);
        System.out.println("Encrypted Text: " + cipherText);
        
        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted Text: " + decryptedText);
        
        scanner.close();
    }
}
