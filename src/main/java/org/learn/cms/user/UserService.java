package org.learn.cms.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO save(UserDTO user) {

        log.info("Saving user with name {}", user.getName());
        UserModel model = mapper.map(user, UserModel.class);
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        model.setProfilePic("user.png");

        UserModel savedModel = userRepository.save(model);
        return mapper.map(savedModel, UserDTO.class);
    }

    public UserDTO getById(long id) {
        UserModel model = userRepository.findById(id).orElse(new UserModel());
        return mapper.map(model, UserDTO.class);
    }
}
