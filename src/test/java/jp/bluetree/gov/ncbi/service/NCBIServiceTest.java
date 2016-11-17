package jp.bluetree.gov.ncbi.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.bluetree.gov.ncbi.Application;
import jp.bluetree.gov.ncbi.model.Publication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class NCBIServiceTest {
  
  @Autowired
  NCBIService ncbiService;
  
  @Test
  public void testService() {
    Publication pub = ncbiService.getSummary("9565568");
    Assert.assertEquals("1998 May 8", pub.getPubdate());
    Assert.assertEquals("Simultaneous expression of type 1 and type 2 Lewis blood group antigens by Helicobacter pylori lipopolysaccharides. Molecular mimicry between h. pylori lipopolysaccharides and human gastric epithelial cell surface glycoforms.", pub.getTitle());
  }

  @Test
  public void testServiceInvalid() {
    Publication pub = ncbiService.getSummary("Z9565568");
    Assert.assertNull(pub.getPubdate());
    Assert.assertEquals("\"Invalid uid Z9565568 at position=0\"", pub.getUid());
  }
}