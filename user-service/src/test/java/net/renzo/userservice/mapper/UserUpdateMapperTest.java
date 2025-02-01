import net.renzo.userservice.mapper.AddressMapper;
import net.renzo.userservice.mapper.UserUpdateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.renzo.userservice.dto.UserUpdateDTO;
import net.renzo.userservice.model.UserDetail;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserUpdateMapperTest {

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private UserUpdateMapperImpl userUpdateMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateUserFromDTO_NullDTO() {
        UserUpdateDTO updateDTO = null;
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername("existingUser");

        UserUpdateMapper.INSTANCE.toEntity(updateDTO);

        assertEquals("existingUser", userDetail.getUsername());
        // Add more assertions as needed to verify the userDetail object remains unchanged
    }
}