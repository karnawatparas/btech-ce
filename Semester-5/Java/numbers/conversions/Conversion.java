import java.util.Arrays;

public class Conversion {

    public static char convertIntToCharacter(int number) {
        if(number < 10) {
            return (char)(number + 48);
        } else {
            number = number % 10;
            return (char)(number + 97);
        }
    }

    public static String toBinary(int decimal) {
        char binary[] = new char[17];
        Arrays.fill(binary, '0');

        int count = binary.length - 1;
        binary[8] = ' ';

        while(decimal > 0) {
            if(binary[count] == ' ') {
                count--;
            }
            binary[count--] = convertIntToCharacter(decimal % 2);
            decimal = decimal / 2;
        }

        String bin = String.valueOf(binary);
        return bin;
    }

    public static String toOctal(int decimal) {
        char octal[] = new char[15];
        Arrays.fill(octal, '0');

        int count = octal.length - 1;
        octal[3] = ' ';
        octal[7] = ' ';
        octal[11] = ' ';

        while(decimal > 0) {
            if(octal[count] == ' ') {
                count--;
            }
            octal[count--] = convertIntToCharacter(decimal % 8);
            decimal = decimal / 8;
        }

        String oct = String.valueOf(octal);
        return oct;
    }

    public static String toHexadecimal(int decimal) {
        char hexadecimal[] = new char[9];
        Arrays.fill(hexadecimal, '0');

        int count = hexadecimal.length - 1;
        hexadecimal[4] = ' ';

        while(decimal > 0) {
            if(hexadecimal[count] == ' ') {
                count--;
            }
            hexadecimal[count--] = convertIntToCharacter(decimal % 16);
            decimal = decimal / 16;
        }

        String hex = String.valueOf(hexadecimal);
        return hex;
    }

    public static void main(String[] args) {
        System.out.println("\nDecimal number: 255");
        System.out.println("Binary: " + toBinary(255));
        System.out.println("Octal: " + toOctal(255));
        System.out.println("Hexadecimal: " + toHexadecimal(255));

        System.out.println("\nDecimal number: 0");
        System.out.println("Binary: " + toBinary(0));
        System.out.println("Octal: " + toOctal(0));
        System.out.println("Hexadecimal: " + toHexadecimal(0));

        System.out.println("\nDecimal number: 120");
        System.out.println("Binary: " + toBinary(120));
        System.out.println("Octal: " + toOctal(120));
        System.out.println("Hexadecimal: " + toHexadecimal(120));

        System.out.println();
    }

}