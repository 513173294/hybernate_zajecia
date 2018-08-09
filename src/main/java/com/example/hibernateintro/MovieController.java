package com.example.hibernateintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MovieController {


    @PersistenceUnit
    @Autowired
    private EntityManagerFactory factory;

    @PostMapping("/addmovie")
    @ResponseBody
    public String dodaj(Movie movie) {

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        return "Dodano" + "<br/><br/><a href=\"/\">Kontynuuj dodawanie</a><br/>";
    }


    @GetMapping("/")
    public String home(Model model) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("SELECT m from Movie m", Movie.class );

        List<Movie> movies = query.getResultList();

        model.addAttribute("allMovies", movies);

        model.addAttribute("newMovie", new Movie());
        return "Home";
    }


//    @GetMapping("/d")
//    @ResponseBody
//    public String addMovie() {
//        Movie movie = new Movie();
//        movie.setTitle("Titanic");
//        LocalDate date = LocalDate.of(1997, 10, 18);
//        movie.setPremiereDate(date);
//        movie.setCategory(Category.DRAMA);
//        movie.setDescription("Leo ginie");
//
//        EntityManager entityManager = factory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(movie);
//        entityManager.getTransaction().commit();
//
//        return "dodano";
//    }

}
