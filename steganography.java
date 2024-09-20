public class steganography {
    public static void main(String[] args) {
        // Normal visible output
        System.out.println("This is some visible code.");

        // Encode the hidden message "Hello World!" using spaces and tabs
        String encodedMessage = encodeMessage("Hello World!");

        // Now let's decode it back from the hidden message (encoded as spaces and tabs)
        String decodedMessage = decodeMessage(encodedMessage);

        System.out.println("Hidden message decoded: " + decodedMessage);
    }

    // Function to encode a message using spaces and tabs (space = 0, tab = 1)
    private static String encodeMessage(String message) {
        StringBuilder hiddenMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char bit : binaryString.toCharArray()) {
                if (bit == '0') {
                    hiddenMessage.append(' '); // 0 becomes space
                } else if (bit == '1') {
                    hiddenMessage.append('\t'); // 1 becomes tab
                }
            }
        }

        return hiddenMessage.toString();
    }

    // Function to decode spaces and tabs into the original message
    private static String decodeMessage(String hiddenMessage) {
        StringBuilder binary = new StringBuilder();
        
        // Convert spaces and tabs into binary (space = 0, tab = 1)
        for (char c : hiddenMessage.toCharArray()) {
            if (c == ' ') {
                binary.append('0');
            } else if (c == '\t') {
                binary.append('1');
            }
        }

        // Split the binary string into 8-bit chunks and convert to characters
        StringBuilder decodedMessage = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteStr = binary.substring(i, i + 8);
            char character = (char) Integer.parseInt(byteStr, 2); // Convert 8-bit binary to character
            decodedMessage.append(character);
        }

        return decodedMessage.toString();
    }
}
