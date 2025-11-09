package ua.opnu;

import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Locale UA = Locale.forLanguageTag("uk");

        Lab7.Student[] students = {
                new Lab7.Student("Поломарчук Богдан", "AI-244", 90, 55, 70), // є борг (55)
                new Lab7.Student("Шугаєв Роман", "AI-244", 81, 79, 92),
                new Lab7.Student("Блажко Олександр", "CS-101", 100, 100, 100),
                new Lab7.Student("Іванов Олег", "CS-102", 59, 60, 61),       // є борг (59)
                new Lab7.Student("Коваленко Марія", "AI-244", 88, 76, 66)
        };

        System.out.println("--- Task 1 ---");
        int[] primeCheck = { -1, 0, 1, 2, 3, 4, 5, 17, 18, 19 };
        for (int n : primeCheck) System.out.printf("%d -> %b%n", n, Lab7.IS_PRIME.test(n));

        System.out.println("\n--- Task 2 ---");
        Predicate<Lab7.Student> hasDebt = Lab7.Student::hasDebt;
        Predicate<Lab7.Student> noDebt = hasDebt.negate();
        System.out.println("Студенти з боргами:");
        Lab7.printStudents(Lab7.filterStudents(students, hasDebt));
        System.out.println("Студенти без боргів:");
        Lab7.printStudents(Lab7.filterStudents(students, noDebt));

        System.out.println("\n--- Task 3 ---");
        int[] data = Lab7.range(-6, 21);
        System.out.print("Початкові дані: "); Lab7.printArray(data);
        System.out.print("Парні І кратні 3: ");
        Lab7.printArray(Lab7.filterAnd(data, n -> n % 2 == 0, n -> n % 3 == 0));
        System.out.print(">0 І прості: ");
        Lab7.printArray(Lab7.filterAnd(data, n -> n > 0, Lab7.IS_PRIME));

        System.out.println("\n--- Task 4 ---");
        Consumer<Lab7.Student> upperFio =
                s -> System.out.println((s.getSurname() + " " + s.getName()).toUpperCase(UA));
        Lab7.forEach(students, upperFio);

        System.out.println("\n--- Task 5 ---");
        int[] d2 = { -3, -2, -1, 0, 1, 2, 3, 4, 5, 6 };
        System.out.println("Парні числа:"); Lab7.doIf(d2, n -> n % 2 == 0, System.out::println);
        System.out.println("Негативні числа з підписом:");
        Lab7.doIf(d2, n -> n < 0, n -> System.out.println(n + " — negative"));

        System.out.println("\n--- Task 6 ---");
        int[] exps = { -3, -1, 0, 1, 2, 3, 4, 5, 8, 10 };
        System.out.print("n: "); Lab7.printArray(exps);
        Function<Integer,Integer> pow2 = n -> (int) Math.pow(2, n);
        System.out.print("2^n: "); Lab7.printArray(Lab7.processIntArray(exps, pow2));

        System.out.println("\n--- Task 7 ---");
        int[] digits = Lab7.range(0, 10);
        String[] named = Lab7.stringify(digits, Lab7.digitsUa());
        System.out.println(Arrays.toString(named));

        System.out.println("\n--- Optional: SortingList (топ-3) ---");
        List<Lab7.Student> list = new ArrayList<>(Arrays.asList(students));
        list.sort(Comparator
                .comparingDouble(Lab7.Student::avg).reversed()
                .thenComparing(Lab7.Student::getSurname));
        for (int i = 0; i < Math.min(3, list.size()); i++) {
            Lab7.Student s = list.get(i);
            System.out.printf("%d) %s %s (%s), avg=%.1f%n",
                    i + 1, s.getSurname(), s.getName(), s.getGroup(), s.avg());
        }
    }
}
