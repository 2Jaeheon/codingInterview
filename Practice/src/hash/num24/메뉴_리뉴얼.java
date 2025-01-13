package hash.num24;

import java.util.Scanner;

public class 메뉴_리뉴얼 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.next();
        Human human = new Human(temp);
        System.out.println(human.name);
        scanner.close();
    }
}

class Human {

    String name;
    int age;
    float height, weight;

    public Human(String n) {
        this.name = n;
    }
}