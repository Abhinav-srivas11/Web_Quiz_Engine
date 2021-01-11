package engine;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
//@EnableWebSecurity//to enable security feature in spring app
public class Controller {
//    private static final String NOT_FOUND_MESSAGE = "No such quiz";
//    private static final String FORBIDDEN_MESSAGE = "You're not allowed";
//    private final QuizRepository quizRepository;
//
//    private static final String BAD_REQUEST_MESSAGE = "This email is already taken";
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public Controller(QuizRepository quizRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
//        this.quizRepository = quizRepository;
//        this.userRepository = userRepository;
//        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @Autowired
//    private ControllerInterface controllerInterface;
//
//    @JsonFilter("myFilter")
//    List<QuizFormat> quizzes = new ArrayList<>();
//
//
//    @PostMapping(path = "/api/quizzes")
//    public QuizFormat createQuiz (@Valid @RequestBody QuizFormat newQuiz) {
//        System.err.println("CREATING NEW QUIZ");
//        return controllerInterface.save(newQuiz);
//    }
//
//    @GetMapping(path = "/api/quizzes/{id}")
//    public ResponseEntity<QuizFormat> getQuiz(@PathVariable(value = "id") Long id) throws JsonProcessingException, IOException {
//        QuizFormat quiz = controllerInterface.findById(id).orElseThrow(() -> new QuizException("Employee not found " +
//                "for this id : " + id));
//
//        return ResponseEntity.ok().body(quiz);
//    }
//
//    @GetMapping(path = "/api/quizzes")
//    public ResponseEntity getAllQuizzes(){
//        return new ResponseEntity(controllerInterface.findAll(), HttpStatus.OK) ;
//    }
//
//    @PostMapping(path = "/logout")
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login";
//    }
//
//    @PostMapping(path = "/api/quizzes/{id}/solve")
//    public Answer verifyAnswer (@PathVariable int id, @RequestBody(required = false) Map<String, List<Integer>> answer) throws JsonProcessingException {
//        QuizFormat quiz = controllerInterface.findById(id);
//
//        int[] answerArray = quiz.getAnswer();
//        List<Integer> correctAnswer = new ArrayList<>();
//
//        if (answerArray == null) {
//            correctAnswer = new ArrayList<>();
//        } else {
//            for (int j : answerArray) {
//                correctAnswer.add(j);
//            }
//        }
//        List<Integer> userAnswer;
//
//        if (answer == null) {
//            userAnswer = new ArrayList<>();
//        } else {
//            userAnswer = answer.get("answer") == null ?
//                    new ArrayList<>() : answer.get("answer");
//        }
//
//        if (userAnswer.equals(correctAnswer)) {
//            return new Answer(true, "Congrats! right answer!");
//        } else {
//            return new Answer(false, "Sorry wrong answer!");
//        }
//    }
//
//    //----------------------------------------------------------------------------------
//    //REGISTRATION CONTROLLER
//    @Autowired
//    private DefaultUserService userService;
//
//    @PostMapping(path = "/register")
//    public void register(@Valid @RequestBody User user) {
//
//        if (userRepository.findByEmail(user.getEmail()) != null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE);
//        } else {
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//
//            user.setPassword(encodedPassword);
//            user.setActive(true);
//            userRepository.save(user);
//
//            UserRole userRole = new UserRole();
//            userRole.setEmail(user.getEmail());
//            userRole.setRole("USER");
//            userRoleRepository.save(userRole);
//        }
//    }
//
//    @PostMapping("/register")
//    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model) {
//        if(bindingResult.hasErrors()) {
//            model.addAttribute("registrationForm", userData);
//            return "account/register";
//        }
//        try {
//            userService.register(userData);
//        } catch (QuizException e) {
//            bindingResult.rejectValue("email", "userData.email", "An account already exists for this email");
//            model.addAttribute("registrationForm", userData);
//            return "account/register";
//        }
//
//        return "/api/quizzes";
//    }



    private static final String NOT_FOUND_MESSAGE = "No such quiz";
    private static final String FORBIDDEN_MESSAGE = "You're not allowed";
    private final QuizRepository quizRepository;

    private static final String BAD_REQUEST_MESSAGE = "This email is already taken";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;


    public Controller(QuizRepository quizRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/quizzes")
    public QuizFormat addQuiz(@Valid @RequestBody QuizFormat quiz, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName());
        quiz.setUser(user);
        quizRepository.save(quiz);
        return quiz;
    }

    @GetMapping(path = "/quizzes/{id}")
    public QuizFormat getQuiz(@PathVariable long id) {
        if (quizRepository.findById(id).isPresent()) {
            return quizRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    NOT_FOUND_MESSAGE);
        }
    }

    @GetMapping(path = "/quizzes")
    public List<QuizFormat> getQuizzes() {
        return quizRepository.findAll();
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public Answer solveQuiz(@PathVariable long id,
                            @RequestBody Answer answer) {
        QuizFormat quiz = getQuiz(id);
        return Arrays.equals(answer.getAnswer(), quiz.getAnswer()) ?
                new Answer(true, "Congratulations, you're right!") :
                new Answer(false, "Wrong answer! Please, try again.");
    }

    @DeleteMapping(path = "/quizzes/{id}")
    public void deleteQuiz(@PathVariable long id, Authentication authentication) {
        QuizFormat quiz = getQuiz(id);
        User user = userRepository.findByEmail(authentication.getName());
        if (quiz.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    FORBIDDEN_MESSAGE);
        }
        quizRepository.delete(quiz);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/quizzes/{id}")
    public QuizFormat updateQuiz(@PathVariable long id, @Valid @RequestBody UpdateQuiz quizDetails, Authentication authentication) {
        QuizFormat storedQuiz = getQuiz(id);
        User user = userRepository.findByEmail(authentication.getName());
        if (storedQuiz.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    FORBIDDEN_MESSAGE);
        }

        storedQuiz.setText(quizDetails.getText());
        storedQuiz.setTitle(quizDetails.getTitle());
        storedQuiz.setOptions(quizDetails.getOptions());
        storedQuiz.setAnswer(quizDetails.getAnswer());

        return quizRepository.save(storedQuiz);
    }

    @PostMapping(path = "/register")
    public void register(@Valid @RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE);
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());

            user.setPassword(encodedPassword);
            user.setActive(true);
            userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setEmail(user.getEmail());
            userRole.setRole("USER");
            userRoleRepository.save(userRole);
        }
    }
}
