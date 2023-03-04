import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long streamUnderage = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(streamUnderage);


        List<String> streamRecruit = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(streamRecruit);

        List<Person> steamWorkers = persons.stream()
//сначала я пробовала написать вот так, но такое, естественно, отфильтрует ничего.
//                .filter(person -> person.getSex().equals(Sex.WOMAN) &&
//                        person.getAge() > 18 && person.getAge() < 60 &&
//                        person.getEducation().equals(Education.HIGHER))
//                .filter(person -> person.getSex().equals(Sex.MAN) &&
//                        person.getAge() > 18 && person.getAge() < 65 &&
//                        person.getEducation().equals(Education.HIGHER))
//Следующий участок кода не работает.
                .filter(person -> {
                         for (Person person1 : persons) {
                             if (person1.getSex().equals(Sex.WOMAN) && person1.getAge() > 18 && person1.getAge() < 60 &&
                        person1.getEducation().equals(Education.HIGHER)){
                                 return true;
                             }else if (person1.getSex().equals(Sex.MAN)&& person1.getAge() > 18 && person1.getAge() < 65 &&
                        person1.getEducation().equals(Education.HIGHER)){
                                 return true;
                             };
                         }return false;
                })
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        System.out.println(steamWorkers);



    }
}

class Person {
    private String name;
    private String family;
    private Integer age;
    private Sex sex;
    private Education education;

    public Person(String name, String family, int age, Sex sex, Education education) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }


    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", education=" + education +
                '}';
    }
}