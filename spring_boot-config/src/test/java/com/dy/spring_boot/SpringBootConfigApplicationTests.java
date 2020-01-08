package com.dy.spring_boot;


import com.dy.spring_boot.entity.Cat;
import com.dy.spring_boot.entity.Dog;
import com.dy.spring_boot.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private Dog dog;

    @Autowired
    private Cat cat;

    @Test
    public void testProp() {
        System.out.println(person);
        System.out.println(dog);
        System.out.println(cat);
    }

}
