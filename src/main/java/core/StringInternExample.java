package core;

public class StringInternExample {
    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = str1.intern(); // Interns the string

        String str3 = "Hello"; // Refers to the interned string

        System.out.println(str1 == str2); // false, different references
        System.out.println(str2 == str3); // true, same reference in string pool
    }
}