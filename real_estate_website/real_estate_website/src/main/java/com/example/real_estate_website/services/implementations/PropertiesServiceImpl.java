package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.daos.PropertyDao;
import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.exceptions.UserNotInRoleException;
import com.example.real_estate_website.mapper.PropertyMapper;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Property;
import com.example.real_estate_website.services.PropertiesService;
import com.example.real_estate_website.services.UsersService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("propertiesService")
public class PropertiesServiceImpl implements PropertiesService {

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PropertyMapper propertyMapper;


    @Override
    public boolean addProperty(PropertyDto propertyDto, AppUser user) {
        try {
            Property property = propertyMapper.mapToProperty(propertyDto);
            property.setOwner(user);
            propertyDao.save(property);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Property> getPropertyById(Long propertyId) {
        Optional<Property> property = propertyDao.findById(propertyId);
        return property;
    }

    @Override
    public List<Property> getAllPropertiesForOwner(Long ownerId) {
        try {
            AppUser appUser = usersService.getUserById(ownerId);
            if (Boolean.FALSE.equals("OWNER".equals(appUser.getRole().getName()))) {
                throw new UserNotInRoleException("The user doesn't have any properties");
            }
            return propertyDao.getAllPropertiesByOwner(ownerId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
