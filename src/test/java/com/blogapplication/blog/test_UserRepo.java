package com.blogapplication.blog;

import com.blogapplication.blog.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
public class test_UserRepo {
       @Autowired
       private UserRepo userRepo;

    @Test
    void testUserRepo(){

         String className=userRepo.getClass().getName().toString();
         String packageName=userRepo.getClass().getPackageName().toString();

         System.out.println(packageName+" -> "+className);
    }

}
