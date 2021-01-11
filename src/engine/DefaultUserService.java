package engine;
        import org.springframework.beans.BeanUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataIntegrityViolationException;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.stereotype.Service;

@Service("userService")
public class DefaultUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void register(UserData userData) throws QuizException {

        //Let's check if user already registered with us
        if(checkIfUserExist(userData.getEmail())){
            throw new QuizException("User already exists for this email");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(userEntity, userData);
        encodePassword(userEntity, userData);
        userRepository.save(userEntity);
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private void encodePassword(User userEntity, UserData user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}