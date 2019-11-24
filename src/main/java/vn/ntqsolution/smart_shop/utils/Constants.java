package vn.ntqsolution.smart_shop.utils;

public interface Constants {

  interface Security {
    String HEADER_PREFIX = "Bearer ";
    String HEADER_AUTHORIZATION = "Authorization";
  }

  interface Role {
    String CUSTOMER = "CUSTOMER";
    String SALE = "SALE";
    String MARKETING = "MARKETING";
    String MANAGER = "MANAGER";
  }

}
