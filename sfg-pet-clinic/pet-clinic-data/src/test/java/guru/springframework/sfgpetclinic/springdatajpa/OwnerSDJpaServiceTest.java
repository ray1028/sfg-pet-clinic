package guru.springframework.sfgpetclinic.springdatajpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	
	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypeRepostory;
	
	@InjectMocks
	OwnerSDJpaService service; 
	
	Owner returnOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		
		Owner returnOwner = new Owner();
		returnOwner.setLastName(LAST_NAME);
		
	}
	
	static final String LAST_NAME = "Smith";

	@Test
	void findByLastName() {
		
		
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		
		Owner smith = service.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, smith.getLastName());
		
		verify(ownerRepository).findByLastName(any());
		
	}
	
	@Test
	void testFindAll() {
		
		Set<Owner> returnOwnersSet = new HashSet<>();
		Owner owner = new Owner();
		Owner owner2 = new Owner();
		owner.setId(1l);
		owner2.setId(2l);
		
		returnOwnersSet.add(owner);
		returnOwnersSet.add(owner2);
		
		when(ownerRepository.findAll()).thenReturn(returnOwnersSet);	
		
		Set<Owner> owners = service.findAll();
		
		assertNotNull(owners);
		assertEquals(2,owners.size());
	}

	@Test
	void testFindIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = service.findById(1L);
		
		assertNotNull(owner);
	}

	@Test
	void testSave() {
		
		Owner ownerToSave = new Owner();
		ownerToSave.setId(1L);
		
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		
		Owner savedOwner = service.save(ownerToSave);
		
		assertNotNull(savedOwner);
		
		verify(ownerRepository).save(any());
		
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);
		
		//default is 1 times
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(ownerRepository, times(1)).deleteById(anyLong());
	}
}
