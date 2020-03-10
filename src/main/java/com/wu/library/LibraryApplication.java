package com.wu.library;

import com.wu.library.models.*;

import com.wu.library.repositories.admin.*;
import com.wu.library.services.MemberService;
import com.wu.library.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

//import javax.xml.ws.spi.http.HttpHandler;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class LibraryApplication implements ApplicationRunner {

    @Autowired
    MemberService memberService;
//    private MemberRepository memberRepository;
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//    private UserDetailsServiceImpl userDetailsService;
//
//    private CateRepository cateRepository;
//
//    public LibraryApplication(MemberRepository memberRepository, UserRepository userRepository, RoleRepository roleRepository, UserDetailsServiceImpl userDetailsService) {
//        this.memberRepository = memberRepository;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Autowired
//    public LibraryApplication(MemberRepository memberRepository, UserRepository userRepository, com.wu.library.repositories.admin.RoleRepository roleRepository, ) {
//        this.memberRepository = memberRepository;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }



//
//    public LibraryApplication(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public static PasswordEncoder getEncoder() {
        return encoder;
    }
    private static PasswordEncoder encoder;

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(LibraryApplication.class, args);
        encoder = context.getBean("passwordEncoder",PasswordEncoder.class);
//       PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
//       String encodedPassword = passwordEncoder.encode("admin");
//       System.out.println(encodedPassword);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {



//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(" http://localhost/json.php", HttpMethod.GET, entity, String.class);
//        if (responseEntity.getStatusCode() == HttpStatus.OK){
//            System.out.println(responseEntity);
//        }
//        else{
//            System.out.println("error");
//        }

        //  Member member = new Member("A009","query","M","English",4,"Evenning","012345627");
    //    memberService.save(member);

  //  memberRepository.save(member);
       // System.out.println("meber"+memberRepository.findById("A006"));

       // jdbcRepository.saveMember(member);
//        Employee employee = new Employee(103,"Amit",35000);
//        int status = jdbcRepository.saveEmployee(employee);

//        Role role = new Role("001","admin");
//        int status = roleRepository.saveRole(role);
//    User user = userRepository.loadUserByUsername("admin");
//        user.setRoles(userRepository.getRoleByUserId(user.getId()));
//        System.out.println(user);
//        List<User> userList = userRepository.getAllUser();
//        System.out.println(userList);


//     Category category = new Category();
//     category.setCate_id("10");
//     category.setName("hello");
//
//        System.out.println("vaule: "+cateRepository.insert(category));

//        try {
//            int a = this.cateRepository.delete("cat");
//        }catch (DataIntegrityViolationException e){
//            System.out.println("pls de");
//        }


//        System.out.println("delete"+this.bookRepository.delete("454"));

          //  Member member = new Member("2","fd","df","fdf",123,"123","dfdd","df","fdsf",true);
           // memberService.save(member);

    }


}
