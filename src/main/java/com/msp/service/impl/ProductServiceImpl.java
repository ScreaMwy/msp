package com.msp.service.impl;

import com.msp.dao.ProductItemDao;
import com.msp.dao.ProductStockDao;
import com.msp.error.BussinesError;
import com.msp.error.BussinessException;
import com.msp.pojo.ProductItemDO;
import com.msp.pojo.ProductStockDO;
import com.msp.service.ProductService;
import com.msp.service.model.ProductModel;
import com.msp.validation.ValidationResult;
import com.msp.validation.ValidatorImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;

@Service("productService")
@Scope(scopeName = "singleton")
public class ProductServiceImpl implements ProductService {
    @Resource(name = "productItemDao", type = ProductItemDao.class)
    private ProductItemDao productItemDao;

    @Resource(name = "productStockDao", type = ProductStockDao.class)
    private ProductStockDao productStockDao;

    @Resource(name = "validator", type = ValidatorImpl.class)
    private ValidatorImpl validator;

    private ValidationResult result;

    private ProductModel productModel;

    private ProductItemDO productItemDO;

    private ProductStockDO productStockDO;

    private ProductServiceImpl() {}

    @Override
    public ProductModel createProduct(ProductModel productModel) throws BussinessException {
        if (null == productModel) {
            return null;
        }

        //入參校驗
        result = validator.validate(productModel);
        if (result.isError()) {
            throw new BussinessException(BussinesError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //轉換模型(Model->DataObject)
        productItemDO = this.convertProductItemToDataObject(productModel);
        productStockDO = this.convertProductStockToDataObject(productModel);
        productStockDO.setItemId(productItemDao.findIdByName(productItemDO.getName()).getId());

        //寫入DataBase
        productItemDao.add(productItemDO);
        productStockDao.add(productStockDO);

        //返回創建後的Model
        this.productModel = this.convertFromDataObject(productItemDO, productStockDO);
        return this.productModel;
    }

    @Override
    public List<ProductModel> findListItem() {
        return null;
    }

    @Override
    public ProductModel findItemById(Integer id) {
        return null;
    }

    //Model -> ProductItemDO
    private ProductItemDO convertProductItemToDataObject (ProductModel productModel) {
        if (null == productModel) {
            return null;
        }

        productItemDO = new ProductItemDO();
        productItemDO.setName(productModel.getName());
        productItemDO.setPrice(productModel.getPrice());
        productItemDO.setDescription(productModel.getDescription());
        productItemDO.setSales(productModel.getSales());
        productItemDO.setImgUrl(productModel.getImgUrl());
        return productItemDO;
    }

    //Model -> ProductStockDO
    private ProductStockDO convertProductStockToDataObject (ProductModel productModel) {
        if (null == productModel) {
            return null;
        }

        productStockDO = new ProductStockDO();
        productStockDO.setStock(productModel.getStock());
        return productStockDO;
    }

    //
    private ProductModel convertFromDataObject(ProductItemDO productItemDO, ProductStockDO productStockDO) {
        if (null == productItemDO || null == productStockDO) {
            return null;
        }

        productModel = new ProductModel();
        productModel.setId(productItemDO.getId());
        productModel.setName(productItemDO.getName());
        productModel.setPrice(productItemDO.getPrice());
        productModel.setSales(productItemDO.getSales());
        productModel.setDescription(productItemDO.getDescription());
        productModel.setImgUrl(productItemDO.getImgUrl());
        productModel.setStock(productStockDO.getStock());
        return productModel;
    }
}
