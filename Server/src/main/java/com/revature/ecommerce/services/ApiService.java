package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Api;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.repositories.ApiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ApiService {

    private ApiRepository apiRepository;
    private CustomerService customerService;

    @Autowired
    public ApiService(ApiRepository apiRepository, CustomerService customerService){
        this.apiRepository = apiRepository;
        this.customerService = customerService;
    }

    public Api findApiByApiId(UUID apiId){
        return apiRepository.findByapiId(apiId);
    }

    public Set<Api> findAllCustomerApi(String email){
        Customer customer = customerService.findByEmail(email);
        Set<Api> apis = customer.getApis();
        return new HashSet<>(apis);
    }

    public Api deleteApi(String email, UUID apiId) throws UnableToDeleteItemException{
        Api apiToDelete = findApiByApiId(apiId);
        Set <Api> customerApis = findAllCustomerApi(email);
        if(!customerApis.contains(apiToDelete)){
            throw new UnableToDeleteItemException("UUID not in api list");
        }
        apiRepository.delete(findApiByApiId(apiId));
        return apiToDelete;
    }
}
