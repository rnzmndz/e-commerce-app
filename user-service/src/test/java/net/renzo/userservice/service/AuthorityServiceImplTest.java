package net.renzo.userservice.service;

import net.renzo.userservice.dto.AuthorityDTO;
import net.renzo.userservice.mapper.AuthorityMapper;
import net.renzo.userservice.model.Authority;
import net.renzo.userservice.model.UserDetail;
import net.renzo.userservice.repository.AuthorityRepository;
import net.renzo.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceImplTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityMapper authorityMapper;

    @InjectMocks
    private AuthorityServiceImpl authorityService;

    private Authority authority;
    private AuthorityDTO authorityDTO;
    private UserDetail user;

    @BeforeEach
    void setUp() {
        authority = new Authority();
        authority.setId(1L);
        authority.setName("ROLE_USER");

        authorityDTO = new AuthorityDTO();
        authorityDTO.setName("ROLE_USER");

        user = new UserDetail();
        user.setId(1L);
    }

    @Test
    void testCreateAuthority() {
        when(authorityMapper.toEntity(any(AuthorityDTO.class))).thenReturn(authority);
        when(authorityRepository.save(any(Authority.class))).thenReturn(authority);
        when(authorityMapper.toDTO(any(Authority.class))).thenReturn(authorityDTO);

        AuthorityDTO createdAuthority = authorityService.createAuthority(authorityDTO);

        assertNotNull(createdAuthority);
        assertEquals("ROLE_USER", createdAuthority.getName());
        verify(authorityRepository, times(1)).save(any(Authority.class));
    }

    @Test
    void testGetAuthority() {
        when(authorityRepository.findById(anyLong())).thenReturn(Optional.of(authority));
        when(authorityMapper.toDTO(any(Authority.class))).thenReturn(authorityDTO);

        AuthorityDTO foundAuthority = authorityService.getAuthority(1L);

        assertNotNull(foundAuthority);
        assertEquals("ROLE_USER", foundAuthority.getName());
        verify(authorityRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetAuthoritiesByUser() {
        when(authorityRepository.findAllByUsers_Id(anyLong())).thenReturn(List.of(authority));

        Set<String> authorities = authorityService.getAuthoritiesByUser(1L);

        assertNotNull(authorities);
        assertTrue(authorities.contains("ROLE_USER"));
        verify(authorityRepository, times(1)).findAllByUsers_Id(anyLong());
    }

    @Test
    void testGetAllAuthority() {
        when(authorityRepository.findAll()).thenReturn(List.of(authority));
        when(authorityMapper.toDTO(any(Authority.class))).thenReturn(authorityDTO);

        Set<AuthorityDTO> authorities = authorityService.getAllAuthority();

        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        verify(authorityRepository, times(1)).findAll();
    }

    @Test
    void testUpdateAuthority() {
        when(authorityRepository.findById(anyLong())).thenReturn(Optional.of(authority));
        doNothing().when(authorityMapper).updateFromDTO(any(AuthorityDTO.class), any(Authority.class));
        when(authorityRepository.save(any(Authority.class))).thenReturn(authority);
        when(authorityMapper.toDTO(any(Authority.class))).thenReturn(authorityDTO);

        AuthorityDTO updatedAuthority = authorityService.updateAuthority(1L, authorityDTO);

        assertNotNull(updatedAuthority);
        assertEquals("ROLE_USER", updatedAuthority.getName());
        verify(authorityRepository, times(1)).findById(anyLong());
        verify(authorityRepository, times(1)).save(any(Authority.class));
    }

    @Test
    void testDeleteAuthority() {
        when(authorityRepository.findById(anyLong())).thenReturn(Optional.of(authority));
        doNothing().when(authorityRepository).delete(any(Authority.class));

        authorityService.deleteAuthority(1L);

        verify(authorityRepository, times(1)).findById(anyLong());
        verify(authorityRepository, times(1)).delete(any(Authority.class));
    }

    @Test
    void testAddAuthorities() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(authorityRepository.findByName(anyString())).thenReturn(Optional.of(authority));
        lenient().when(authorityRepository.saveAll(anyList())).thenReturn(List.of(authority));

        authorityService.addAuthorities(1L, Set.of("ROLE_USER"));

        verify(userRepository, times(1)).findById(anyLong());
        verify(authorityRepository, times(1)).findByName(anyString());
        verify(authorityRepository, times(1)).saveAll(anySet());
    }

    @Test
    void testRemoveAuthorities() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(authorityRepository.findByName(anyString())).thenReturn(Optional.of(authority));
        lenient().when(authorityRepository.saveAll(anyList())).thenReturn(List.of(authority));

        authorityService.removeAuthorities(1L, Set.of("ROLE_USER"));

        verify(userRepository, times(1)).findById(anyLong());
        verify(authorityRepository, times(1)).findByName(anyString());
        verify(authorityRepository, times(1)).saveAll(anySet());
    }
}