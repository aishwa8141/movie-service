//package com.stackroute.movieservice;
//
//
//import com.stackroute.movieservice.domain.Movie;
//import com.stackroute.movieservice.repository.MovieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//@Component
//public class CommandLineRunnerBean implements ApplicationListener<ContextRefreshedEvent>,CommandLineRunner {
//@Autowired
//    private MovieRepository movieRepository;
//    @Autowired
//    public CommandLineRunnerBean(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("command line runner");
//       movieRepository.save(new Movie("m1","raazi","image/raazi",5l,2014,"good"));
//        movieRepository.save(new Movie("m2","harry","himage/harry",2l,2015,"kk"));
//    }
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        {
//
//            System.out.println("context refreshed excecution " +contextRefreshedEvent);
//            movieRepository.save(new Movie("m3","ninja","image/ninja",4l,2017,"good"));
//            //movieRepository.save(new Movie("m4","potter","image/harry",4l,2013,"good"));
//
//        }
//    }
//}
