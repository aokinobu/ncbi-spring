package jp.bluetree.gov.ncbi;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import jp.bluetree.gov.ncbi.model.Publication;
import jp.bluetree.gov.ncbi.service.NCBIService;

/**
 *
 * 

https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?db=pubmed&id=Z19565568&retmode=json&tool=my_tool&email=my_email@example.com
{

    "header": {
        "type": "esummary",
        "version": "0.3"
    },
    "error": "Invalid uid Z19565568 at position=0",
    "result": {
        "uids": [
        ]
    }
}

https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?db=pubmed&id=9565568&retmode=json&tool=my_tool&email=my_email@example.com

{
    "header": {
        "type": "esummary",
        "version": "0.3"
    },
    "result": {
        "uids": [
            "9565568"
        ],
        "9565568": {
            "uid": "9565568",
            "pubdate": "1998 May 8",
            "epubdate": "",
            "source": "J Biol Chem",
            "authors": [
                {
                    "name": "Monteiro MA",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Chan KH",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Rasko DA",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Taylor DE",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Zheng PY",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Appelmelk BJ",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Wirth HP",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Yang M",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Blaser MJ",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Hynes SO",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Moran AP",
                    "authtype": "Author",
                    "clusterid": ""
                },
                {
                    "name": "Perry MB",
                    "authtype": "Author",
                    "clusterid": ""
                }
            ],
            "lastauthor": "Perry MB",
            "title": "Simultaneous expression of type 1 and type 2 Lewis blood group antigens by Helicobacter pylori lipopolysaccharides. Molecular mimicry between h. pylori lipopolysaccharides and human gastric epithelial cell surface glycoforms.",
            "sorttitle": "simultaneous expression of type 1 and type 2 lewis blood group antigens by helicobacter pylori lipopolysaccharides molecular mimicry between h pylori lipopolysaccharides and human gastric epithelial cell surface glycoforms",
            "volume": "273",
            "issue": "19",
            "pages": "11533-43",
            "lang": [
                "ENG"
            ],
            "nlmuniqueid": "2985121R",
            "issn": "0021-9258",
            "essn": "1083-351X",
            "pubtype": [
                "Journal Article"
            ],
            "recordstatus": "PubMed - indexed for MEDLINE",
            "pubstatus": "4",
            "articleids": [
                {
                    "idtype": "pubmed",
                    "idtypen": 1,
                    "value": "9565568"
                },
                {
                    "idtype": "rid",
                    "idtypen": 8,
                    "value": "9565568"
                },
                {
                    "idtype": "eid",
                    "idtypen": 8,
                    "value": "9565568"
                }
            ],
            "history": [
                {
                    "pubstatus": "pubmed",
                    "date": "1998/06/13 00:00"
                },
                {
                    "pubstatus": "medline",
                    "date": "1998/06/13 00:01"
                },
                {
                    "pubstatus": "entrez",
                    "date": "1998/06/13 00:00"
                }
            ],
            "references": [
            ],
            "attributes": [
                "Has Abstract"
            ],
            "pmcrefcount": 43,
            "fulljournalname": "The Journal of biological chemistry",
            "elocationid": "",
            "doctype": "citation",
            "srccontriblist": [
            ],
            "booktitle": "",
            "medium": "",
            "edition": "",
            "publisherlocation": "",
            "publishername": "",
            "srcdate": "",
            "reportnumber": "",
            "availablefromurl": "",
            "locationlabel": "",
            "doccontriblist": [
            ],
            "docdate": "",
            "bookname": "",
            "chapter": "",
            "sortpubdate": "1998/05/08 00:00",
            "sortfirstauthor": "Monteiro MA",
            "vernaculartitle": ""
        }Rest
    }
}


 *
 */

@SpringBootApplication
public class Application {

	private static final Log logger = LogFactory.getLog(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
	
	@Autowired
	NCBIService ncbiService;

	@Bean
	public CommandLineRunner run() throws Exception {
	  
		return args -> {
		  if (args.length > 0 && null != args[0]) {
	      Publication pub = ncbiService.getSummary(args[0]);
	      logger.info(pub.toString());
	      logger.info(pub.getTitle());
		  }
		};
	}

	public NCBIService antiSpringService() {
	  AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

	  return ctx.getBean(NCBIService.class);
	}
}