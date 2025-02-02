package net.renzo.userservice.controller;

import net.renzo.userservice.dto.UserCreateDTO;
import net.renzo.userservice.dto.UserDTO;
import net.renzo.userservice.dto.UserListDTO;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.exception.UserAlreadyExistsException;
import net.renzo.userservice.exception.UserNotFoundException;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserRole;
import net.renzo.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;
    private UserCreateDTO userCreateDTO;
    private UserUpdateDTO userUpdateDTO;
    private UserListDTO userListDTO;

    @BeforeEach
    void setUp() {
        userDTO = UserDTO.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .build();

        userCreateDTO = UserCreateDTO.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password123")
                .build();

        userUpdateDTO = UserUpdateDTO.builder()
                .email("updated@example.com")
                .build();

        userListDTO = UserListDTO.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .build();
    }

    @Test
    void createUser_Success() {
        when(userService.existsByUsername(anyString())).thenReturn(false);
        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(userCreateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).createUser(userCreateDTO);
    }

    @Test
    void createUser_ThrowsUserAlreadyExistsException() {
        when(userService.existsByUsername(anyString())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () ->
                userController.createUser(userCreateDTO)
        );

        verify(userService, never()).createUser(any(UserCreateDTO.class));
    }

    @Test
    void getUserById_Success() {
        when(userService.getById(anyLong())).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).getById(1L);
    }

    @Test
    void getUserById_ThrowsUserNotFoundException() {
        when(userService.getById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userController.getUserById(1L)
        );
    }

    @Test
    void getAllUsers_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserListDTO> userPage = new PageImpl<>(List.of(userListDTO));
        when(userService.getAll(any(Pageable.class))).thenReturn(userPage);

        ResponseEntity<Page<UserListDTO>> response = userController.getAllUsers(pageable);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userPage);
        verify(userService).getAll(pageable);
    }

    @Test
    void updateUser_Success() {
        when(userService.getById(anyLong())).thenReturn(Optional.of(userDTO));
        when(userService.updateById(anyLong(), any(UserUpdateDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, userUpdateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).updateById(1L, userUpdateDTO);
    }

    @Test
    void deleteUser_Success() {
        when(userService.getById(anyLong())).thenReturn(Optional.of(userDTO));
        doNothing().when(userService).deleteById(anyLong());

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(userService).deleteById(1L);
    }

    @Test
    void addAuthorities_Success() {
        when(userService.getById(anyLong())).thenReturn(Optional.of(userDTO));
        Authority defaultAuthority = new Authority();
        defaultAuthority.setName("ROLE_USER");
        Set<Authority> authorities = Set.of(defaultAuthority);

        ResponseEntity<UserDTO> response = userController.addAuthorities(1L, authorities);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService).addAuthoritiesToUser(1L, authorities);
    }

    @Test
    void removeAuthority_Success() {
        when(userService.getById(anyLong())).thenReturn(Optional.of(userDTO));
        Authority defaultAuthority = new Authority();
        defaultAuthority.setName("ROLE_USER");

        ResponseEntity<UserDTO> response = userController.removeAuthority(1L, defaultAuthority);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService).removeAuthorityFromUser(1L, defaultAuthority);
    }

    @Test
    void getUsersByRole_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserListDTO> userPage = new PageImpl<>(List.of(userListDTO));
        when(userService.getUserByRole(any(UserRole.class), any(Pageable.class))).thenReturn(userPage);

        ResponseEntity<Page<UserListDTO>> response = userController.getUsersByRole(UserRole.CUSTOMER, pageable);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userPage);
        verify(userService).getUserByRole(UserRole.CUSTOMER, pageable);
    }

    @Test
    void checkUserExists_ReturnsTrue() {
        when(userService.existsByUsername(anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = userController.checkUserExists("testuser");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
        verify(userService).existsByUsername("testuser");
    }

    @Test
    void changePassword_Success() {
        when(userService.getById(anyLong())).thenReturn(Optional.of(userDTO));
        doNothing().when(userService).changePassword(anyLong(), anyString());

        ResponseEntity<Void> response = userController.changePassword(1L, "newPassword");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(userService).changePassword(1L, "newPassword");
    }
}