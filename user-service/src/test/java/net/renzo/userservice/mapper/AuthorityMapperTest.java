import net.renzo.userservice.mapper.AuthorityMapper;
import net.renzo.userservice.model.Authority;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import net.renzo.userservice.dto.AuthorityDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

class AuthorityMapperTest {
    private AuthorityMapper authorityMapper = Mappers.getMapper(AuthorityMapper.class);

    // Create a test method
    @Test
    void testAuthorityToAuthorityDTO() {
        // Create an Authority object
        Authority authority = new Authority();
        authority.setName("ROLE_USER");

        // Map Authority to AuthorityDTO
        AuthorityDTO authorityDTO = authorityMapper.toDTO(authority);

        // Assert that the name is correctly mapped
        assertEquals("ROLE_USER", authorityDTO.getName());
    }
}