package com.example.springbootmongodb.spring.boot.mongodb.config;

import com.example.springbootmongodb.spring.boot.mongodb.domain.Post;
import com.example.springbootmongodb.spring.boot.mongodb.domain.User;
import com.example.springbootmongodb.spring.boot.mongodb.dto.AuthorDTO;
import com.example.springbootmongodb.spring.boot.mongodb.dto.CommentDTO;
import com.example.springbootmongodb.spring.boot.mongodb.repository.PostRepository;
import com.example.springbootmongodb.spring.boot.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("01/09/2020"),"Primeiro post testando", "olá tudo bem?", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("03/09/2020"), "Segundo post maria", "testando post de novo", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Testando comentário do post", sdf.parse("10/08/2020"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Parabéns!", sdf.parse("01/09/2020"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Olá, como vai?", sdf.parse("02/09/2020"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));
        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
