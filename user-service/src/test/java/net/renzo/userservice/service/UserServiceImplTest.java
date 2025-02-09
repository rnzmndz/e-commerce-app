package net.renzo.userservice.service;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.mapper.*;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.repository.AuthorityRepository;
import net.renzo.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserCreateMapper userCreateMapper;

    @Mock
    private UserListMapper userListMapper;

    @Mock
    private UserUpdateMapper userUpdateMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private ProfileMapper profileMapper;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, authorityRepository, userCreateMapper, userMapper, userListMapper, userUpdateMapper, addressMapper, profileMapper);
    }

    @Test
    void createUser_WithValidData_ShouldCreateUser() {
        // Arrange
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setUsername("testuser");
        createDTO.setEmail("test@example.com");

        UserDetail userDetail = new UserDetail();
        UserDTO expectedUserDTO = UserDTO.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .role(UserRole.CUSTOMER)
                .authorities(new HashSet<>())
                .build();

        Authority defaultAuthority = new Authority();
        defaultAuthority.setName("ROLE_USER");

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(authorityRepository.findByName("ROLE_USER")).thenReturn(Optional.of(defaultAuthority));
        lenient().when(userCreateMapper.toEntity(any(UserCreateDTO.class))).thenReturn(userDetail);
        when(userRepository.save(any(UserDetail.class))).thenReturn(userDetail);
        lenient().when(userMapper.toDTO(any(UserDetail.class))).thenReturn(expectedUserDTO);

        // Act
        UserDTO result = userService.createUser(createDTO);

        // Assert
        assertNotNull(result);
        verify(userRepository).save(any(UserDetail.class));
        verify(authorityRepository).findByName("ROLE_USER");
    }

    @Test
    void createUser_WithExistingUsername_ShouldThrowException() {
        // Arrange
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setUsername("existinguser");
        createDTO.setEmail("test@example.com");

        lenient().when(userRepository.existsByUsername(anyString())).thenReturn(true);
        lenient().when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(createDTO));
    }

    @Test
    void getById_WithExistingId_ShouldReturnUser() {
        // Arrange
        Long userId = 1L;
        UserDetail userDetail = new UserDetail();
        UserDTO expectedUserDTO = UserDTO.builder()
                .id(userId)
                .username("testuser")
                .email("test@example.com")
                .role(UserRole.CUSTOMER)
                .authorities(new HashSet<>())
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDetail));
        lenient().when(userMapper.toDTO(userDetail)).thenReturn(expectedUserDTO);

        // Act
        Optional<UserDTO> result = userService.getById(userId);

        // Assert
        assertNotNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    void getAll_ShouldReturnPageOfUsers() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        UserDetail userDetail = new UserDetail();
        Page<UserDetail> userPage = new PageImpl<>(Collections.singletonList(userDetail));
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .role(UserRole.CUSTOMER)
                .authorities(new HashSet<>())
                .build();

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        lenient().when(userMapper.toDTO(any(UserDetail.class))).thenReturn(userDTO);

        // Act
        Page<UserListDTO> result = userService.getAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(userRepository).findAll(pageable);
    }

    @Test
    void updateById_WithValidData_ShouldUpdateUser() {
        // Arrange
        Long userId = 1L;
        UserUpdateDTO updateDTO = new UserUpdateDTO();
        UserDetail existingUser = new UserDetail();
        UserDTO expectedUserDTO = UserDTO.builder()
                .id(userId)
                .username("testuser")
                .email("test@example.com")
                .role(UserRole.CUSTOMER)
                .authorities(new HashSet<>())
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserDetail.class))).thenReturn(existingUser);
        lenient().when(userMapper.toDTO(any(UserDetail.class))).thenReturn(expectedUserDTO);

        // Act
        UserDTO result = userService.updateById(userId, updateDTO);

        // Assert
        assertNotNull(result);
        verify(userRepository).save(any(UserDetail.class));
    }

    @Test
    void deleteById_WithExistingId_ShouldDeleteUser() {
        // Arrange
        Long userId = 1L;
        UserDetail userDetail = new UserDetail();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDetail));

        // Act
        userService.deleteById(userId);

        // Assert
        verify(userRepository).delete(userDetail);
    }

    @Test
    void getUserByRole_ShouldReturnUsersWithSpecificRole() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        UserRole role = UserRole.CUSTOMER;
        UserDetail userDetail = new UserDetail();
        Page<UserDetail> userPage = new PageImpl<>(Collections.singletonList(userDetail));
        UserListDTO userListDTO = new UserListDTO();

        when(userRepository.findByRole(role, pageable)).thenReturn(userPage);
        lenient().when(userListMapper.toDTO(any(UserDetail.class))).thenReturn(userListDTO);

        // Act
        Page<UserListDTO> result = userService.getUserByRole(role, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(userRepository).findByRole(role, pageable);
    }

    @Test
    void changePassword_WithValidPassword_ShouldUpdatePassword() {
        // Arrange
        Long userId = 1L;
        String newPassword = "newValidPassword123";
        UserDetail userDetail = new UserDetail();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDetail));
        when(userRepository.save(any(UserDetail.class))).thenReturn(userDetail);

        // Act
        userService.changePassword(userId, newPassword);

        // Assert
        verify(userRepository).save(userDetail);
        assertEquals(newPassword, userDetail.getPassword());
    }

    @Test
    void changePassword_WithInvalidPassword_ShouldThrowException() {
        // Arrange
        Long userId = 1L;
        String invalidPassword = "password';--";
        UserDetail userDetail = new UserDetail();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDetail));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.changePassword(userId, invalidPassword));
        verify(userRepository, never()).save(any(UserDetail.class));
    }
}