package com.example.dabbaWalaWebsite.security;

import com.example.dabbaWalaWebsite.entity.Admin;
import com.example.dabbaWalaWebsite.entity.Customer;
import com.example.dabbaWalaWebsite.entity.RestaurantOwner;
import com.example.dabbaWalaWebsite.repository.AdminRepo;
import com.example.dabbaWalaWebsite.repository.CustomerRepo;
import com.example.dabbaWalaWebsite.repository.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AllUsersDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(customerRepo.existsByUsername(username)){
            Customer customer=customerRepo.findByUsername(username);
            if(customer==null){
                throw new UsernameNotFoundException("customer username not found");
            }
            return new CustomerDetailsImpl(customer);
        }

        else if (restaurantOwnerRepo.existsByUsername(username)) {
            RestaurantOwner restaurantOwner = restaurantOwnerRepo.findByUsername(username);
            if (restaurantOwner == null) {
                throw new UsernameNotFoundException("restaurant Owner username not found");
            }
            return new RestaurantOwnerDetailsImpl(restaurantOwner);
        } else {
            Admin admin=adminRepo.findByUsername(username);
            if (admin == null) {
                throw new UsernameNotFoundException("admin username not found");
            }
            return new AdminDetailsImpl(admin);
        }
    }
}
