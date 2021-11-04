package cinema.controller;

import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectController {
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final MovieService movieService;
    @Autowired
    private final CinemaHallService cinemaHallService;
    @Autowired
    private final MovieSessionService movieSessionService;

    public InjectController(RoleService roleService, UserService userService,
                            MovieService movieService, CinemaHallService cinemaHallService,
                            MovieSessionService movieSessionService) {
        this.roleService = roleService;
        this.userService = userService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
    }

    @PostConstruct
    public String inject() {
        if (roleService.getRoleByName("USER").isPresent()) {
            return "Nothing to inject";
        }
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        Role roleUser = new Role();
        roleUser.setName("USER");

        roleService.add(roleAdmin);
        roleService.add(roleUser);

        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin123");
        admin.setRoles(Set.of(roleAdmin));
        userService.add(admin);

        User firstUser = new User();
        firstUser.setEmail("bob@gmail.com");
        firstUser.setPassword("12345");
        firstUser.setRoles(Set.of(roleUser));
        userService.add(firstUser);

        Movie terminator = new Movie();
        terminator.setTitle("Terminator");
        terminator.setDescription("film about Terminator");
        movieService.add(terminator);

        CinemaHall blueCinemaHall = new CinemaHall();
        blueCinemaHall.setDescription("Blue cinema hall");
        blueCinemaHall.setCapacity(200);
        cinemaHallService.add(blueCinemaHall);

        MovieSession earlySession = new MovieSession();
        earlySession.setCinemaHall(blueCinemaHall);
        earlySession.setMovie(terminator);
        earlySession.setShowTime(LocalDateTime.now());
        movieSessionService.add(earlySession);

        return "Done";
    }
}
