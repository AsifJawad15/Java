import java.util.Scanner;

public class HiddenMessage2 {

    public static void main(String[] args) {
        // Normal visible output
        System.out.println("This is some visible code.");

        Scanner myObj = new Scanner(System.in);

        // Ask the user whether they want to encode or decode
        System.out.println("Choose an option:\n1. Encode a message\n2. Decode a message from spaces and tabs");
        int choice = myObj.nextInt();
        myObj.nextLine();  // Consume the newline character

        if (choice == 1) {
            // Take input from user for the message to encode
            System.out.print("Enter a message to encode: ");
            String messageToEncode = myObj.nextLine();

            // Encode the hidden message using spaces and tabs
            String encodedMessage = encodeMessage(messageToEncode);

            // Now let's print the encoded message using [SPACE] for space and [TAB] for tab
            System.out.println("Encoded message (showing spaces and tabs):");
            printHiddenMessage(encodedMessage);

        } else if (choice == 2) {
            // Take input from user for the encoded message (spaces and tabs)
            System.out.println("Enter the hidden message (use spaces and tabs):");
            String hiddenMessage = myObj.nextLine();

            // Decode the hidden message
            String decodedMessage = decodeMessage(hiddenMessage);

            System.out.println("Hidden message decoded: " + decodedMessage);
        } else {
            System.out.println("Invalid option. Please restart and choose 1 or 2.");
        }

        myObj.close();  // Close the scanner
    }

    // Function to encode a message using spaces and tabs (space = 0, tab = 1)
    private static String encodeMessage(String message) {
        StringBuilder hiddenMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            // Convert each character to an 8-bit binary string
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char bit : binaryString.toCharArray()) {
                if (bit == '0') {
                    hiddenMessage.append(' '); // 0 becomes space
                } else if (bit == '1') {
                    hiddenMessage.append('\t'); // 1 becomes tab
                }
            }
        }

        return hiddenMessage.toString(); // Return the encoded message
    }

    // Function to decode spaces and tabs back into the original message
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

    // Function to print the encoded message, replacing spaces and tabs with visual markers
    private static void printHiddenMessage(String hiddenMessage) {
        for (char c : hiddenMessage.toCharArray()) {
            if (c == ' ') {
                System.out.print("[SPACE]");
            } else if (c == '\t') {
                System.out.print("[TAB]");
            }
        }
        System.out.println(); // New line after printing the hidden message
    }
}

