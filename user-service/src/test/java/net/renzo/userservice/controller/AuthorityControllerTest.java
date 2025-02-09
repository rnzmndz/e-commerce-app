package net.renzo.userservice.controller;

import net.renzo.userservice.dto.AuthorityDTO;
import net.renzo.userservice.service.AuthorityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorityControllerTest {

    @Mock
    private AuthorityService authorityService;

    @InjectMocks
    private AuthorityController authorityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthority() {
        AuthorityDTO authorityDTO = new AuthorityDTO();
        AuthorityDTO createdAuthority = new AuthorityDTO();
        when(authorityService.createAuthority(any(AuthorityDTO.class))).thenReturn(createdAuthority);

        ResponseEntity<AuthorityDTO> response = authorityController.createAuthority(authorityDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdAuthority, response.getBody());
    }

    @Test
    void testGetAuthority() {
        Long id = 1L;
        AuthorityDTO authorityDTO = new AuthorityDTO();
        when(authorityService.getAuthority(id)).thenReturn(authorityDTO);

        ResponseEntity<AuthorityDTO> response = authorityController.getAuthority(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorityDTO, response.getBody());
    }

    @Test
    void testGetAuthoritiesByUser() {
        Long userId = 1L;
        Set<String> authorities = new HashSet<>();
        when(authorityService.getAuthoritiesByUser(userId)).thenReturn(authorities);

        ResponseEntity<Set<String>> response = authorityController.getAuthoritiesByUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorities, response.getBody());
    }

    @Test
    void testGetAllAuthorities() {
        Set<AuthorityDTO> authorities = new HashSet<>();
        when(authorityService.getAllAuthority()).thenReturn(authorities);

        ResponseEntity<Set<AuthorityDTO>> response = authorityController.getAllAuthorities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorities, response.getBody());
    }

    @Test
    void testUpdateAuthority() {
        Long id = 1L;
        AuthorityDTO authorityDTO = new AuthorityDTO();
        AuthorityDTO updatedAuthority = new AuthorityDTO();
        when(authorityService.updateAuthority(eq(id), any(AuthorityDTO.class))).thenReturn(updatedAuthority);

        ResponseEntity<AuthorityDTO> response = authorityController.updateAuthority(id, authorityDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAuthority, response.getBody());
    }

    @Test
    void testDeleteAuthority() {
        Long id = 1L;
        doNothing().when(authorityService).deleteAuthority(id);

        ResponseEntity<Void> response = authorityController.deleteAuthority(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorityService, times(1)).deleteAuthority(id);
    }

    @Test
    void testAddAuthorities() {
        Long userId = 1L;
        Set<String> authorities = new HashSet<>();
        doNothing().when(authorityService).addAuthorities(userId, authorities);

        ResponseEntity<Void> response = authorityController.addAuthorities(userId, authorities);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorityService, times(1)).addAuthorities(userId, authorities);
    }

    @Test
    void testRemoveAuthorities() {
        Long userId = 1L;
        Set<String> authorities = new HashSet<>();
        doNothing().when(authorityService).removeAuthorities(userId, authorities);

        ResponseEntity<Void> response = authorityController.removeAuthorities(userId, authorities);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorityService, times(1)).removeAuthorities(userId, authorities);
    }
}