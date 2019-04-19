package guru.springframework.sfgpetclinic.services.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;


class OwnerMapServiceTest {

	OwnerMapService ownerMapService;
	Owner owner;
	Long ownerId = 1L;
	final String lastName = "Smith";

	@BeforeEach
	void setUp() throws Exception {
		
		owner = new Owner();
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		owner.setId(ownerId);
		owner.setLastName(lastName);
		owner = ownerMapService.save(owner);
	}

	@Test
	void testFindAll() throws Exception{	
		
		assertEquals(1,ownerMapService.findAll().size());
	}


	@Test
	void testFindByIdID() {
		assertEquals(ownerMapService.findById(ownerId), ownerMapService.findById(ownerId));
	}

	@Test
	void saveExxistingdID() {
		Long id = 2L;
		Owner owner2 = new Owner();
		
		owner2.setId(id);
		
		Owner savedOwner = ownerMapService.save(owner2);
		
		assertEquals(id, savedOwner.getId());
		
	}
	
	@Test void saveNoId() {
		Owner savedOwner = ownerMapService.save(new Owner());
		
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
		ownerMapService.delete(savedOwner);
	}

	@Test
	void testDeleteByIdID() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testDeleteT() {
		Long ownerId = 11L;
		Owner owner3 = new Owner();
		owner3.setId(ownerId);
		Owner savedOwner = ownerMapService.save(owner3);
		ownerMapService.delete(owner3);
		assertEquals(1, ownerMapService.findAll().size());
	}
	
	void findByLastName() {
		Owner smith = ownerMapService.findByLastName(lastName);
		assertNotNull(smith);
		assertEquals(ownerId, smith.getId());
	}
}
