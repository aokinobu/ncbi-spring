package jp.bluetree.gov.ncbi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import jp.bluetree.gov.ncbi.service.NCBIService;

@Configuration
public class EutilsConfig {
  
  
  @Bean
  public NCBIService getService() {
    return new NCBIService();
  }
  
//  @Bean
//  public RestTemplate restTemplate() {
//    return new RestTemplate();
//  }

//  @Bean
//  public RestTemplate restTemplate(RestTemplateBuilder builder) {
//    return builder.build();
//  }
  
//  "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?db=pubmed&id=Z19565568&retmode=json&tool=ncbiSpring&email=ncbi@bluetree.jp"
  public static final String HOSTNAME = "https://eutils.ncbi.nlm.nih.gov";
  
  public static final String PATH = "/entrez/eutils/esummary.fcgi?db=pubmed&retmode=json&tool=ncbiSpring&email=ncbi@bluetree.jp";
}
