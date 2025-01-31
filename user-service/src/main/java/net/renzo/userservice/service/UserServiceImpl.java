package net.renzo.userservice.service;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.mapper.UserCreateMapper;
import net.renzo.userservice.mapper.UserMapper;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final UserCreateMapper userCreateMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserCreateMapper userCreateMapper
    , UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userCreateMapper = userCreateMapper.INSTANCE;
        this.userMapper = userMapper.INSTANCE;
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        // Check if a user with the same username or email already exists
        if (userRepository.existsByUsernameOrEmail(userCreateDTO.getUsername(), userCreateDTO.getEmail())) {
            throw new IllegalArgumentException("User with the same username or email already exists");
        }

        // Create a new UserDetail entity from the UserCreateDTO using a mapper
        UserDetail userDetail = userCreateMapper.toEntity(userCreateDTO);

        // Save the new user to the repository
        userDetail = userRepository.save(userDetail);

        // Convert the saved UserDetail entity to a UserDTO using the userMapper and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public UserDTO getById(Long id) {
        // Find the user by id, throw an exception if not found
        UserDetail userDetail = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Convert the UserDetail entity to a UserDTO and return it
        return userMapper.toDTO(userDetail);
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        // Fetch all UserDetail entities with pagination
        Page<UserDetail> userDetailsPage = userRepository.findAll(pageable);

        // Convert the Page<UserDetail> to Page<UserDTO> using the userMapper
        return userDetailsPage.map(userMapper::toDTO);
    }

    @Override
    public UserDTO updateById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public UserDTO addAuthoritiesToUser(Long id, Set<Authority> authorities) {
        return null;
    }

    @Override
    public UserDTO removeAuthorityFromUser(Long id, Authority authority) {
        return null;
    }

    @Override
    public Page<UserDTO> getUserByRole(UserRole userRole) {
        return null;
    }

    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        return false;
    }

    @Override
    public void changePassword(Long id, String newPassword) {

    }
}
