package _Test.JDK8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionStream {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>() {{
            add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
            add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
            add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
            add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
            add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
            add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
            add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
            add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
        }};

        // 将Student映射成String
        List<String> names = students.stream().filter(student -> "计算机科学".equals(student.getMajor())).map(Student::getName).collect(Collectors.toList());

        for (String name : names) {
            System.out.println(name);
        }

        // mapToInt 映射成Stream<Integer>
        int age = students.stream().filter(student -> "计算机科学".equals(student.getMajor())).mapToInt(Student::getAge).sum();
        System.out.println(age);

        String[] strs = {"java8", "is", "easy", "to", "use"};
        // 消除字符串数组中每个字符串的重复字母
        List<String[]> sl1 = Arrays.stream(strs).map(str -> str.split("")).distinct().collect(Collectors.toList());
        List<String> sl2 = Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        boolean allMatch = students.stream().allMatch(student -> "计算机科学".equals(student.getMajor()));
        Optional<Student> optStr = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findFirst();

        System.out.println(allMatch);
        System.out.println(optStr.get().getMajor());
        System.out.println(optStr.get().getMajor());
    }
}

class Student {

    /**
     * 学号
     */
    private long id;

    private String name;

    private int age;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

    public Student(long id, String name, int age, int grade, String major, String school) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
        this.school = school;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    // 省略getter和setter
}