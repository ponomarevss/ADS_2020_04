package lesson3;

import lesson3.stack.Stack;
import lesson3.stack.StackImpl;

public class FlipTheString {
    public static void main(String[] args) {
        String stringToFlip = "qwerty asdfgh";
        System.out.println(stringToFlip);
        char[] chars = stringToFlip.toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < characters.length; i++) {
            characters[i] = chars[i];
        }
        Stack<Character> characterStack = new StackImpl(characters);
        while (!characterStack.isEmpty()) System.out.print(characterStack.pop());
    }
}
