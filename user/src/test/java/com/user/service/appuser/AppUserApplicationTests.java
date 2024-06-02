package com.user.service.appuser;

import com.user.service.appuser.dto.AppUserDto;
import com.user.service.appuser.entity.AppUser;
import com.user.service.appuser.mapperImpl.AppUserEntityToAppUserDTOImpl;
import com.user.service.appuser.repository.AppUserRepository;
import com.user.service.appuser.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class AppUserApplicationTests {
	@Mock
	private AppUserRepository appUserRepository;

	@Mock
	private AppUserEntityToAppUserDTOImpl appUserEntityToAppUserDTO;

	@InjectMocks
	private AppUserService appUserService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddUser() {
		AppUser user = new AppUser();
		when(appUserRepository.save(user)).thenReturn(user);

		AppUser savedUser = appUserService.addUser(user);

		assertEquals(user, savedUser);
		verify(appUserRepository, times(1)).save(user);
	}

	@Test
	void testRetrieveUserInfos_UserNotExists() {
		when(appUserRepository.findByUsername("username")).thenReturn(Optional.empty());

		AppUserDto retrievedUserDto = appUserService.retrieveUserInfos("username");

		assertEquals(null, retrievedUserDto);
		verify(appUserRepository, times(1)).findByUsername("username");
		verify(appUserEntityToAppUserDTO, times(0)).appUserEntityToAppUserDTO(any(AppUser.class));
	}

	@Test
	void testFindUserByUserName() {
		AppUser user = new AppUser();
		when(appUserRepository.findByUsername("username")).thenReturn(Optional.of(user));

		Optional<AppUser> foundUser = appUserService.findUserByUserName("username");

		assertTrue(foundUser.isPresent());
		assertEquals(user, foundUser.get());
		verify(appUserRepository, times(1)).findByUsername("username");
	}

}
