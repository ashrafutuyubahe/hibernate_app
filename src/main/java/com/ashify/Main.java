package com.ashify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main{

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
@GetMapping("/")
    public GetYourNameClass getYourName(){

    return  new GetYourNameClass(
            "hackim",
            List.of("java","kotlin","javascript"),
            new Person("walahi")

    );

}

  record Person(String personName){};
record GetYourNameClass(
        String greet,
        List<String> typeOfLanguages,
        Person person


        ){}
//
// class GetYourNameClass {
//
//        public final  String name;
//
//         public GetYourNameClass(String name) {
//             this.name = name;
//         }
//
//
//         public String getName() {
//             return name;
//         }
//     }




    @Override
    public String toString() {
        return "Main{}";
    }





}
