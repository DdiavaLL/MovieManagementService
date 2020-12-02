package com.vlter.mmservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MmserviceApplicationTests {
	/*@Resource
	private MovieRepository movieRepository;

	@Resource
	private DirectorRepository directorRepository;

	@Test
	void givenMovie_whenSave_thenGetOk() {
		Movie movie = new Movie(1, "Test", 2009, new Director(1, "Vlad"), LocalTime.of(2,42,0), 8);
		movieRepository.save(movie);

		Assert.assertNotNull(movieRepository);
		Assert.assertNotNull(directorRepository);

		Movie movie2 = movieRepository.findById(1).orElse(null);
		assertEquals("Test", movie2.getTitle());
	}*/

	@Test
	void contextLoads() {
	}
}
