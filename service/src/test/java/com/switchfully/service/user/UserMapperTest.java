package com.switchfully.service.user;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.AddressRepository;
import com.switchfully.domain.user.User;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleDto;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Collection;
import java.util.List;

import static com.switchfully.service.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    UserRoleMapper userRoleMapper = new UserRoleMapper();
    AddressMapper addressMapper = mock(AddressMapper.class);
    AddressRepository addressRepository = mock(AddressRepository.class);


    @InjectMocks
    UserMapper userMapper = new UserMapper(addressMapper, userRoleMapper, addressRepository);

    @Test
    void toNewUser_createsAndReturnsANewUserObject() {
        CreateUserDto createUserDto = testUserDtoBuilder().buildCreateUserDto();
        User user = userMapper.toNewUser(createUserDto);
        assertEquals(user.getFirstName(), createUserDto.getFirstName());
        assertEquals(user.getLastName(), createUserDto.getLastName());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertTrue(verifyHash(createUserDto.getPassword(), user.getPassword()));
    }

    @Test
    void toUserDto_createsDtoOutOfExistingUser() {
        User user = testUserDtoBuilder().buildCustomer();
        UserDto userDto = userMapper.toUserDto(user);
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        System.out.println(userDto.getRole());
        assertEquals(UserRoleDto.CUSTOMER, userDto.getRole());
    }

    @Test
    void toDtoCollection_returnsCollectionOfUserDtos() {
        User customer = testUserDtoBuilder().buildCustomer();
        User admin = testUserDtoBuilder().buildAdmin();
        UserDto customerDto = userMapper.toUserDto(customer);
        UserDto adminDto = userMapper.toUserDto(admin);
        Collection<User> userCollection = List.of(customer, admin);
        assertThat(userMapper.toDtoCollection(userCollection)).containsExactlyInAnyOrder(customerDto, adminDto);
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}