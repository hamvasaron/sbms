package com.aronhamvas.tests.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTest {

    private static class Student {
        private String name;
        private int age;
        private String placeOfBirth;

        public Student(String name, int age, String placeOfBirth) {
            this.name = name;
            this.age = age;
            this.placeOfBirth = placeOfBirth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPlaceOfBirth() {
            return placeOfBirth;
        }

        public void setPlaceOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
        }
    }

    @Test
    public void test1() {
        List<Student> students = new ArrayList<Student>(Arrays.asList(
                new Student("Aron", 37, "Siofok"),
                new Student("Peti", 39, "Siofok"),
                new Student("Patti", 32, "Siofok"),
                new Student("Bogi", 34, "Debrecen"),
                new Student("Milan", 12, "Debrecen"),
                new Student("Andrea", 38, "Szekszard")
        ));

        students.stream()
                .filter(s -> s.getPlaceOfBirth().equals("Siofok"))
                .forEach(s -> System.out.println(s.getName()));

        System.out.println(students.stream().mapToInt(Student::getAge).average().orElse(0));

        students.stream().collect(Collectors.groupingBy(Student::getPlaceOfBirth, Collectors.counting()))
                .forEach((pob, count) -> System.out.printf("POB: %s, Count: %d\n", pob, count));

        students.stream().sorted(Comparator.comparingInt(Student::getAge)).map(Student::getName).forEach(System.out::println);
        students.stream().sorted(Comparator.comparing(Student::getName)).map(Student::getName).forEach(System.out::println);
    }
}
