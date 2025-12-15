package com.example.ecommerce_project.dto_DataTransferObject.Order;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderRequest {

    @NotEmpty
    @Valid
    private List<BuyItem> buyItemList;


    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
