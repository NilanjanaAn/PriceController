package com.blueyonder;
import com.blueyonder.logging.InfoLogger;
import com.blueyonder.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blueyonder.service.ProductService;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyScheduler {

	@Autowired
	private ProductService productService;
	
    @Scheduled(initialDelay = 3000000, fixedDelay = 3000000)
    public void myMethod() throws IOException, InvalidFormatException {
        final String getProductsUri = "http://localhost:8080/api/product/get";
        RestTemplate restTemplate = new RestTemplate();
        List<Product> productList= List.of(restTemplate.getForObject(getProductsUri, Product[].class));
        List<Integer> lastSoldQty=new ArrayList<>();
        for(int i=0;i<productList.size();i++)
        {
          lastSoldQty.add(productList.get(i).getSold_qty());
        }
        productService.updateProductData();
        productList= List.of(restTemplate.getForObject(getProductsUri, Product[].class));
        InfoLogger.logDataToExcel(productList, lastSoldQty);
        System.out.println("Executed!......");
    }
}

