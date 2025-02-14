package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.UserDetailDTO;
import net.renzo.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class UserDetailMapperTest {

    private final UserDetailMapper mapper = Mappers.getMapper(UserDetailMapper.class);

    @Test
    void testToUserDetailDTO() {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(1L);
        userDetail.setUsername("John Doe");
        userDetail.setEmail("john.doe@example.com");

        UserDetailDTO userDetailDTO = mapper.toUserDetailDTO(userDetail);

        assertNotNull(userDetailDTO);
        assertEquals(userDetail.getId(), userDetailDTO.getId());
        assertEquals(userDetail.getUsername(), userDetailDTO.getUsername());
        assertEquals(userDetail.getEmail(), userDetailDTO.getEmail());

    }

    @Test
    void testToUserDetail() {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(1L);
        userDetailDTO.setUsername("John Doe");
        userDetailDTO.setEmail("john.doe@example.com");

        UserDetail userDetail = mapper.toUserDetail(userDetailDTO);

        assertNotNull(userDetail);
        assertEquals(userDetailDTO.getId(), userDetail.getId());
        assertEquals(userDetailDTO.getUsername(), userDetail.getUsername());
        assertEquals(userDetailDTO.getEmail(), userDetail.getEmail());
    }

    @Test
    void testUpdateEntity() {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(1L);
        userDetailDTO.setUsername("Jane Doe");
        userDetailDTO.setEmail("jane.doe@example.com");

        UserDetail userDetail = new UserDetail();
        userDetail.setId(1L);
        userDetail.setUsername("John Doe");
        userDetail.setEmail("john.doe@example.com");

        mapper.updateEntity(userDetailDTO, userDetail);

        assertEquals(userDetailDTO.getId(), userDetail.getId());
        assertEquals(userDetailDTO.getUsername(), userDetail.getUsername());
        assertEquals(userDetailDTO.getEmail(), userDetail.getEmail());
    }
}