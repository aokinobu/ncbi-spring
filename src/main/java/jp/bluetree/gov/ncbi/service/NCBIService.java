package jp.bluetree.gov.ncbi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.bluetree.gov.ncbi.config.EutilsConfig;
import jp.bluetree.gov.ncbi.model.Publication;

public class NCBIService {

  private static final Log logger = LogFactory.getLog(NCBIService.class);

//  @Autowired
  RestTemplate restTemplate = new RestTemplate();
  
  @Autowired
  ObjectMapper objectMapper;

  public Publication getSummary(String id) {
    JsonNode summary = restTemplate.getForObject(EutilsConfig.HOSTNAME + EutilsConfig.PATH + "&id=" + id, JsonNode.class);
    logger.debug(summary.toString());
    if (null != summary.get("error")) {
      logger.warn(summary.get("error"));
      Publication pub = new Publication();
      pub.setUid(summary.get("error").toString());
      return pub; 
    }
    String uid = summary.get("result").get("uids").get(0).toString();
    logger.debug(uid);

    uid = uid.replaceAll("\"", "");
    logger.debug(uid);
    logger.debug(summary.get("result").get(uid));
    Publication pub = objectMapper.convertValue(summary.get("result").get(uid), Publication.class);

    return pub;
  }
}
