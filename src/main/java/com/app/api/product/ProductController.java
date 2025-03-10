package com.app.api.product;

import io.swagger.annotations.*;
//import springfox.documentation.annotations.*;
import javax.servlet.http.*;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

//import static org.springframework.http.MediaType.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.*;
import java.lang.*;
import com.app.api.*;
import com.app.batch.JobCompletionNotificationListener;
import com.app.model.product.*;
import com.app.repo.*;
import com.app.model.response.*;
import com.app.model.user.Login;
import com.app.model.user.ProductBase;

import static com.app.model.response.OperationResponse.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Products"})
public class ProductController {
    
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private ProductRepo productRepo;

    @ApiOperation(value = "List of products", response = ProductResponse.class)
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    public ProductResponse getProductsByPage(
        @ApiParam(value = ""    )               @RequestParam(value = "page"  ,  defaultValue="0"   ,  required = false) Integer page,
        @ApiParam(value = "between 1 to 1000" ) @RequestParam(value = "size"  ,  defaultValue="20"  ,  required = false) Integer size,
        @RequestParam(value = "productid"  , required = false) Integer productId,
        @RequestParam(value = "category"   , required = false) String  category,
        Pageable pageable
    ) {
        ProductResponse resp = new ProductResponse();
        Product qry = new Product();
        if (productId != null)  { qry.setId(productId); }
        if (category  != null)  { qry.setCategory(category); }

        Page<Product> productPage = productRepo.findAll(org.springframework.data.domain.Example.of(qry), pageable);
        resp.setPageStats(productPage, true);
        resp.setItems(productPage.getContent());
        return resp;
    }

    @ApiOperation(value = "Add new product", response = OperationResponse.class)
    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addNewProduct(@RequestBody Product product, HttpServletRequest req) {

        OperationResponse resp = new OperationResponse();

        if (this.productRepo.exists(product.getId()) ){
            resp.setOperationStatus(ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Unable to add Product - Product allready exist ");
        }
        else{
            //Product addedProduct = this.productRepo.save(product);
            this.productRepo.save(product);
            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Product Added");
        }
        return resp;
    }

    @ApiOperation(value = "Delete a product", response = OperationResponse.class)
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE, produces = {"application/json"})
    public OperationResponse deleteProduct(@PathVariable("productId") Integer productId, HttpServletRequest req) {
        OperationResponse resp = new OperationResponse();
        
        
        if (this.productRepo.exists(productId) ){
            this.productRepo.delete(productId);
            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Product Deleted");
        }
        else{
            resp.setOperationStatus(ResponseStatusEnum.ERROR);
            resp.setOperationMessage("No Product Exist");
        }
        return resp;
    }
    
    
    @ApiOperation(value = "get a product", response = OperationResponse.class)
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = {"application/json"})
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    public OperationResponse getProduct(@PathVariable("productId") Integer productId, HttpServletRequest req) {
        ProductResponse resp = new ProductResponse();
       log.info("*************product code is ***********  :"+productId);
        if (this.productRepo.exists(productId) ){
        	Optional<Product> loadedProduct=this.productRepo.findOneById(productId);
        	
        	List<Product> listProd=new ArrayList<Product>();
        	listProd.add(loadedProduct.get());
        	
            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Product loaded");
            resp.setItems(listProd);
        }
        else{
            resp.setOperationStatus(ResponseStatusEnum.ERROR);
            resp.setOperationMessage("No Product Exist");
        }
        return resp;
    }
    
    
    
    @ApiOperation(value = "update  an existing product ", response = OperationResponse.class)
    @RequestMapping(value = "/products/update", method = RequestMethod.POST, produces = {"application/json"})
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    public OperationResponse updateProduct(@RequestBody ProductBase product, HttpServletRequest req,HttpServletResponse res) {
         
    	
    	System.out.println("update product :"+product.getId());
        OperationResponse resp = new OperationResponse();

        if (this.productRepo.exists(product.getId())){
        	Product productEntity= productRepo.findOne(product.getId());
        	productEntity.setDiscontinued(product.getEtat());
            this.productRepo.save(productEntity);
            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Product updated");
        }
        else{
        	 resp.setOperationStatus(ResponseStatusEnum.ERROR);
             resp.setOperationMessage("Unable to update Product - Product does not  exist ");
        }
        return resp;
    }
    



}
