package jp.bluetree.gov.ncbi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import jp.bluetree.gov.ncbi.service.NCBIService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
  @Autowired
  NCBIService ncbiService;

	@Test
	public void contextLoads() {
		assertThat(ncbiService).isNotNull();
	}
}
